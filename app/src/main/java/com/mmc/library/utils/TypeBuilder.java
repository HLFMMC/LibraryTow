package com.mmc.library.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyj on 2017/3/4.
 */

public class TypeBuilder {

    private TypeBuilder parent;
    private Class row;
    private List<Type> args = new ArrayList<>();

    public TypeBuilder(Class row,TypeBuilder parent){
        assert row != null;
        this.row = row;
        this.parent = parent;
    }

    public static TypeBuilder newInstance(Class row){
        return new TypeBuilder(row,null);
    }

    public static TypeBuilder newInstance(Class row,TypeBuilder parent){
        return new TypeBuilder(row,parent);
    }

    public TypeBuilder beginSubType(Class clz){
        return new TypeBuilder(clz,this);
    }

    public TypeBuilder endSubType(){
        if(parent == null)
            throw new RuntimeException("expect beginType before endSubType");
        parent.addTypeParam(getType());
        return parent;
    }

    public TypeBuilder addTypeParam(Class clz){
        return addTypeParam((Type)clz);
    }

    public TypeBuilder addTypeParam(Type type){
        if(type == null)
            throw new NullPointerException("addTypeParam not a null type");
        args.add(type);
        return this;
    }

    public TypeBuilder addTypeParamExtends(Class...classs){
        if(classs == null)
            throw new RuntimeException("");
        WildcardTypeImpl wildcardType = new WildcardTypeImpl(null,classs);
        return addTypeParam(wildcardType);
    }

    public TypeBuilder addTypeParamSuper(Class...classes){
        if(classes == null)
            throw new RuntimeException("");
        WildcardTypeImpl wildcardType = new WildcardTypeImpl(classes,null);
        return addTypeParam(wildcardType);
    }

    public Type build(){
        if(parent != null)
            throw new RuntimeException("except endSubType before build");
        return getType();
    }

    private Type getType() {
        if (args.isEmpty()) {
            return row;
        }
        return new ParameterizedTypeImpl(row,args.toArray(new Type[args.size()]),null);
    }
}
