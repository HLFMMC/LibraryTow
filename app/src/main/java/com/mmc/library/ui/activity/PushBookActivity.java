package com.mmc.library.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.mmc.library.R;
import com.mmc.library.base.BaseActivity;
import com.mmc.library.bean.Book;
import com.mmc.library.bean.Pic;
import com.mmc.library.ui.presenters.PushBookPresenters;
import com.mmc.library.ui.presenters.base.Message;
import com.mmc.library.ui.view.LoadView;
import com.mmc.library.utils.Constant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by HM on 2017/2/28.
 */
public class PushBookActivity extends BaseActivity<PushBookPresenters> implements LoadView {


    @BindView(R.id.edt_bookName)
    EditText edt_bookName;
    @BindView(R.id.bookcategorySpinner)
    Spinner bookcategorySpinner;
    @BindView(R.id.edt_bookDesc)
    EditText edt_bookDesc;
    @BindView(R.id.btn_File)
    Button btn_File;

    @OnClick(R.id.btn_File)
    void choseimg() {
        showPopueWindow();

    }

    @BindView(R.id.edt_bookPrice)
    EditText edt_bookPrice;
    @BindView(R.id.btn_Push)
    Button btn_Push;

    @OnClick(R.id.btn_Push)
    void pushbook() {
        Book book = new Book();
        book.setName(edt_bookName.getText().toString());
        book.setDesc(edt_bookDesc.getText().toString());
        book.setPic(edt_bookPic.getText().toString());
        book.setMoney(Float.parseFloat(edt_bookPrice.getText().toString()));
        mPresenter.pushbook(Message.obtain(this, user.getToken(), book));
    }

    @BindView(R.id.edt_bookPic)
    EditText edt_bookPic;


    @Override
    protected int getContentView() {
        return R.layout.activity_pushbook;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected PushBookPresenters getPresenter() {
        return new PushBookPresenters();
    }

    /**
     * 选择文件
     */
    private void checkFile() {
        /**
         * 选择文件
         */

        uploadPic();
    }

    /**
     * 上传文件的方法
     */
    private void uploadPic() {
//        上传图片

    }

    private void bookPush() {
//        String bookName  = edt_bookName .getText().toString().trim();
//        String bookDesc  = edt_bookDesc .getText().toString().trim();
//        String bookPic   = edt_bookPic  .getText().toString().trim();
//        String bookPrice = edt_bookPrice.getText().toString().trim();

//        if(saveBookInfo(bookName,bookDesc,bookPic,bookPrice)) {
//
//        }
    }


    /**
     * 保存信息方法
     *
     * @param bookName
     * @param bookDesc
     * @param bookPic
     * @param bookPrice
     * @return
     */
    private boolean saveBookInfo(String bookName, String bookDesc, String bookPic, String bookPrice) {
        boolean flag = false;
        return true;
    }

    private final static int RESULT_LOAD_IMAGE = 1;
    private final static int RESULT_CAMERA_IMAGE = 2;

    private void showPopueWindow() {
        View popView = View.inflate(this, R.layout.popupwindow_camera_need, null);
        Button bt_album = (Button) popView.findViewById(R.id.btn_pop_album);
        Button bt_camera = (Button) popView.findViewById(R.id.btn_pop_camera);
        Button bt_cancle = (Button) popView.findViewById(R.id.btn_pop_cancel);
        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels * 1 / 3;

        final PopupWindow popupWindow = new PopupWindow(popView, weight, height);
        // popupWindow.setAnimationStyle(R.style.anim_popup_dir);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);

        bt_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
                popupWindow.dismiss();

            }
        });
        bt_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeCamera(RESULT_CAMERA_IMAGE);
                popupWindow.dismiss();

            }
        });
        bt_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });
        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM, 0, 50);

    }

    private void takeCamera(int num) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(PushBookActivity.this.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            photoFile = createImageFile();
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
            }
        }

        startActivityForResult(takePictureIntent, num);//跳转界面传回拍照所得数据
    }

    public void go_crop_pic(Uri uri) {
        //裁剪
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == RESULT_LOAD_IMAGE && null != data) {
                go_crop_pic(data.getData());
            } else if (requestCode == RESULT_CAMERA_IMAGE) {

                SimpleTarget target = new SimpleTarget<Bitmap>() {

                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Log.d("onResourceReady--->", "");
//                        upload(saveMyBitmap(resource).getAbsolutePath());
                    }

                    @Override
                    public void onLoadStarted(Drawable placeholder) {
                        super.onLoadStarted(placeholder);

                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);

                    }
                };

//                Glide.with(RegisterUIActivity.this).load(mCurrentPhotoPath)
//                        .asBitmap()
//                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                        .override(1080, 1920)//图片压缩
//                        .centerCrop()
//                        .dontAnimate()
//                        .into(target);
            } else if (requestCode == 10) {
                Bitmap bb = data.getExtras().getParcelable("data");
                String endName = String.valueOf(new Date().getTime()) + ".png";
                saveBitmap(getExternalCacheDir().getPath(), endName, bb, 100, true);
                upload(getExternalCacheDir().getPath() + "/" + endName);
            }
        }
    }

    public static void saveBitmap(String dirpath, String filename, Bitmap bitmap, int display, boolean isDelete) {
        File dir = new File(dirpath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dirpath, filename);
        // 若存在即删除-默认只保留一张
        if (isDelete) {
            if (file.exists()) {
                file.delete();
            }
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("create" + e);
                e.printStackTrace();
            }
        }

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.PNG, display, out)) {
                out.flush();
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("" + e);
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    System.out.println("tiw" + e);
                    e.printStackTrace();
                }
            }
        }
    }


    //将bitmap转化为png格式
    public File saveMyBitmap(Bitmap mBitmap) {
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File file = null;
        try {
            file = File.createTempFile(
                    generateFileName(),  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );

            FileOutputStream out = new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 20, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("saveMyBitmap--->", file.getAbsolutePath());
        return file;
    }

    private String mCurrentPhotoPath;

    private File createImageFile() {
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File image = null;
        try {
            image = File.createTempFile(
                    generateFileName(),  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public static String generateFileName() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        return imageFileName;
    }

    ProgressDialog pdg = null;

    @Override
    public void LoadFailed() {

    }

    @Override
    public void LoadSuccuse(String str) {

    }

    @Override
    public void LoadSuccuse(Message msg) {

    }

    @Override
    public void LoadFinish() {

    }

    @Override
    public void dismissDialog() {
        if (pdg != null)
            pdg.dismiss();
    }

    @Override
    public void showMessage(String message) {
        showToast(message);
    }

    @Override
    public void handleMessage(Message message) {
        switch (message.what) {
            case Constant.DISMIIS_DIALOG:
                dismissDialog();
                Pic pic = new Pic();
                pic = (Pic) message.obj;
                edt_bookPic.setText(pic.getSrc());
                break;
            case Constant.REGISTER_FAILD_CODE:
                showMessage("failed");
                break;
            case Constant.REGISTER_SUCCUSE_CODE:
                showMessage("ok");
                break;
        }
    }

    private void upload(String picturePath) {
        pdg = ProgressDialog.show(this, "", "正在上传图片。。。");
        mPresenter.postFile(Message.obtain(this, user.getToken(), picturePath));
    }


}
