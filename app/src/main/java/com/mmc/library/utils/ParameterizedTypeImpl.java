package com.mmc.library.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * Created by hyj on 2017/3/4.
 */

public class ParameterizedTypeImpl implements ParameterizedType{

    private final Class row;
    private final Type[] args;
    private final Type owner;

    public ParameterizedTypeImpl(Class row,Type[] args,Type owner) {
        this.row = row;
        this.args = args != null ?args:new Type[0];
        this.owner = owner;
        checkArgs();
    }

    private void checkArgs(){
        if(row == null)
            throw new NullPointerException("row can not is null");
        TypeVariable[] typeParameters = row.getTypeParameters();
        if(args.length != 0 && typeParameters.length != args.length){
            throw new NullPointerException(row.getName() + "except" + typeParameters.length + "arg(s),got" + args.length);
        }
    }

    @Override
    public Type[] getActualTypeArguments() {
        return args;
    }

    @Override
    public Type getRawType() {
        return row;
    }

    @Override
    public Type getOwnerType() {
        return owner;
    }
}
