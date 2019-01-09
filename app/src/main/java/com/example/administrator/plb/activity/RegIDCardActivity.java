package com.example.administrator.plb.activity;

import android.Manifest;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.plb.BuildConfig;
import com.example.administrator.plb.R;
import com.example.administrator.plb.until.Base64Util;
import com.example.administrator.plb.until.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.Permission;
import java.security.Permissions;

public class RegIDCardActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView sfz,sfzbm,scsfz,yyzz;
    private static final int     REQUEST_CAMERA= 100;
    private static final int     REQUEST_GALLERY= 101;
    public static  final int CROP_PHOTO=2;// 裁剪
    private File mTmpFile;
    private File mCropImageFile;
    private Uri imageUri;
    private Bitmap bitmap;
    private static final int     REQUEST_CROP= 102;
    public static  final int CHOICE_PHOTO=3;//从相册中选择
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public static File tempFile;
    private String strSfz,strSfzbm,strscsfz,stryyzz;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_id_card);
        sp=getSharedPreferences("data", Context.MODE_PRIVATE);

        editor=sp.edit();
        editor.putBoolean("isselect",false);
        editor.putBoolean("isselect1",false);
        editor.putBoolean("isselect2",false);
        editor.putBoolean("isselect3",false);
        editor.commit();
        init();
    }

    private void init() {
        sfz=findViewById(R.id.sfz);
        sfzbm=findViewById(R.id.sfzbm);
        scsfz=findViewById(R.id.scsfz);
        yyzz=findViewById(R.id.yyzz);
        sfz.setOnClickListener(this);
        sfzbm.setOnClickListener(this);
        scsfz.setOnClickListener(this);
        yyzz.setOnClickListener(this);
        button=findViewById(R.id.reg);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sfz:
                selectimgheadalert();
                editor=sp.edit();
                editor.putBoolean("isselect",true);
                editor.commit();

                break;
            case R.id.sfzbm:
                selectimgheadalert();
                editor=sp.edit();
                editor.putBoolean("isselect1",true);
                editor.commit();
                break;
            case R.id.scsfz:
                selectimgheadalert();
                editor=sp.edit();
                editor.putBoolean("isselect2",true);
                editor.commit();
                break;
            case R.id.yyzz:
                selectimgheadalert();
                editor=sp.edit();
                editor.putBoolean("isselect3",true);
                editor.commit();
                break;
            case R.id.reg:
                Toast.makeText(this, strSfz+""+strSfzbm+""+strscsfz+""+stryyzz , Toast.LENGTH_SHORT).show();
                break;
        }
    }
    String[]permissions=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};
    private void selectimgheadalert(){
        final String[] items = {"拍照", "相册"};
        android.app.AlertDialog.Builder listDialog = new android.app.AlertDialog.Builder(RegIDCardActivity.this);
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0){
                    ActivityCompat.requestPermissions(RegIDCardActivity.this,permissions,1);
                   // camera();
                }else if (i == 1){
                    ActivityCompat.requestPermissions(RegIDCardActivity.this,permissions,2);
                   // gallery();
                }
            }
        });
        listDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED || grantResults[1]==PackageManager.PERMISSION_GRANTED){
                camera();
            }else{
                Toast.makeText(this, "部分权限未授予，相机无法使用", Toast.LENGTH_SHORT).show();
            }
        }
        if(requestCode==2){
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED || grantResults[1]==PackageManager.PERMISSION_GRANTED){
                gallery();
            }else{
                Toast.makeText(this, "部分权限未授予，功相册无法打开", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //打开相册
    private void gallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_GALLERY);
    }



    private void camera(){

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {

            mTmpFile = new File(FileUtils.createRootPath(getBaseContext()) + "/" + System.currentTimeMillis() + ".jpg");
            FileUtils.createFile(mTmpFile);
            Log.e(""+mTmpFile,FileUtils.createRootPath(getBaseContext()) + "/" + System.currentTimeMillis() + ".jpg");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                Log.e("123",(mTmpFile==null)+"");
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                FileProvider.getUriForFile(this,BuildConfig.APPLICATION_ID + ".fileprovider", mTmpFile));
            }else {
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTmpFile));
            }
            startActivityForResult(cameraIntent, REQUEST_CAMERA);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_CAMERA:

                if (resultCode == RESULT_OK){
                    crop(mTmpFile.getAbsolutePath());
                }else {
                    Toast.makeText(this, "拍照失败", Toast.LENGTH_SHORT).show();
                    gallery();
                }
                break;
            case REQUEST_CROP:
                if(resultCode==RESULT_OK)
                {
                    Boolean isselect=sp.getBoolean("isselect",false);
                    Boolean isselect1=sp.getBoolean("isselect1",false);
                    Boolean isselect2=sp.getBoolean("isselect2",false);
                    Boolean isselect3=sp.getBoolean("isselect3",false);
                    if(isselect==true){
                    //sfz.setImageBitmap(bitmap);
                       sfz.setImageURI(Uri.fromFile(mCropImageFile));
                        try {
                            InputStream in=new FileInputStream(mCropImageFile);
                            Bitmap bitmap=BitmapFactory.decodeStream(in);
                            //sfz.setImageBitmap(bitmap);
                            strSfz = Base64Util.bitmapToBase64(bitmap);
                            Log.e("strSfz",strSfz);

                          /*  Bitmap bitmap1 = Base64Util.base64ToBitmap(strSfz);
                            sfz.setImageBitmap(bitmap1);*/

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        editor=sp.edit();
                        editor.putBoolean("isselect",false);
                        editor.commit();
                    }else if(isselect1==true){
                        //sfzbm.setImageBitmap(bitmap);
                        sfzbm.setImageURI(Uri.fromFile(mCropImageFile));
                        try {
                            InputStream in=new FileInputStream(mCropImageFile);
                            Bitmap bitmap=BitmapFactory.decodeStream(in);
                            //sfz.setImageBitmap(bitmap);
                            strSfzbm = Base64Util.bitmapToBase64(bitmap);
                            Log.e("strSfzbm",strSfzbm);

                          /*  Bitmap bitmap1 = Base64Util.base64ToBitmap(strSfz);
                            sfz.setImageBitmap(bitmap1);*/

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        editor=sp.edit();
                        editor.putBoolean("isselect1",false);
                        editor.commit();
                    }else if(isselect2==true){
                        //scsfz.setImageBitmap(bitmap);
                        scsfz.setImageURI(Uri.fromFile(mCropImageFile));
                        try {
                            InputStream in=new FileInputStream(mCropImageFile);
                            Bitmap bitmap=BitmapFactory.decodeStream(in);
                            //sfz.setImageBitmap(bitmap);
                            strscsfz = Base64Util.bitmapToBase64(bitmap);
                            Log.e("strscsfz",strscsfz);

                          /*  Bitmap bitmap1 = Base64Util.base64ToBitmap(strSfz);
                            sfz.setImageBitmap(bitmap1);*/

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        editor=sp.edit();
                        editor.putBoolean("isselect2",false);
                        editor.commit();
                    }else if(isselect3==true){
                        //yyzz.setImageBitmap(bitmap);
                        yyzz.setImageURI(Uri.fromFile(mCropImageFile));
                        try {
                            InputStream in=new FileInputStream(mCropImageFile);
                            Bitmap bitmap=BitmapFactory.decodeStream(in);
                            //sfz.setImageBitmap(bitmap);
                            stryyzz = Base64Util.bitmapToBase64(bitmap);
                            Log.e("stryyzz",stryyzz);

                          /*  Bitmap bitmap1 = Base64Util.base64ToBitmap(strSfz);
                            sfz.setImageBitmap(bitmap1);*/

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        editor=sp.edit();
                        editor.putBoolean("isselect3",false);
                        editor.commit();
                    }

                }
                break;
            case REQUEST_GALLERY:
                if (resultCode == RESULT_OK && data != null) {
                    String imagePath = handleImage(data);
                    crop(imagePath);
                }
                    break;

            default:
                super.onActivityResult(requestCode,resultCode,data);
                break;
        }
    }

    private void crop(String imagePath){
        //mCropImageFile = FileUtils.createTmpFile(getBaseContext());
        mCropImageFile = getmCropImageFile();
        Intent intent = new Intent("com.android.camera.action.CROP");
        Log.e("StringURl",imagePath);
        intent.setDataAndType(getImageContentUri(new File(imagePath)), "image/*");
        intent.putExtra("crop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 500);
        intent.putExtra("outputY", 500);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCropImageFile));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, REQUEST_CROP);
    }

    public Uri getImageContentUri(File imageFile){
        String filePath = imageFile.getAbsolutePath();
        Log.e("filePath",filePath);
        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }
    //获取裁剪的图片保存地址
    private File getmCropImageFile(){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),"temp.jpg");
            File file = new File(getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
            return file;
        }
        return null;
    }
    private String handleImage(Intent data) {
        Uri uri = data.getData();
        String imagePath = null;
        if (Build.VERSION.SDK_INT >= 19) {
            if (DocumentsContract.isDocumentUri(this, uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                    String id = docId.split(":")[1];
                    String selection = MediaStore.Images.Media._ID + "=" + id;
                    imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                    Uri contentUri = ContentUris.withAppendedId(Uri.parse("" +
                            "content://downloads/public_downloads"), Long.valueOf(docId));
                    imagePath = getImagePath(contentUri, null);
                }
            } else if ("content".equals(uri.getScheme())) {
                imagePath = getImagePath(uri, null);
            }
        } else {
            imagePath = getImagePath(uri, null);
        }
        return imagePath;
    }

    private String getImagePath(Uri uri, String seletion) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, seletion, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

}
