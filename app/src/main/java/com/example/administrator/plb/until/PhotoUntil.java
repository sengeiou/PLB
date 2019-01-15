package com.example.administrator.plb.until;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class PhotoUntil {
    private Activity activity;
    private File mTmpFile;
    private File mCropImageFile;
    String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    public static int CAMERA = 1;
    public static int GALLERY = 2;
    public static int CROP = 3;

    public PhotoUntil(Activity activity) {
        this.activity = activity;
        selectimgheadalert();
    }

    public void selectimgheadalert() {
        String[] items = {"拍照", "相册"};
        android.app.AlertDialog.Builder listDialog = new android.app.AlertDialog.Builder(activity);
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    ActivityCompat.requestPermissions(activity, permissions, 1);
                } else if (i == 1) {
                    ActivityCompat.requestPermissions(activity, permissions, 2);
                }
            }
        });
        listDialog.show();
    }


    //打开相册
    public void gallery() {
        Intent intent = new Intent(Intent.ACTION_PICK,null);
        intent.setType("image/*");
        activity.startActivityForResult(intent, GALLERY);
    }

    private File file;
    private Uri uri;

    /**
     * 打开相机
     */
    public void camera() {
        getFileUri();
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        activity.startActivityForResult(intent,CAMERA);
    }

    private void getFileUri() {
        file=new File(activity.getExternalCacheDir().getAbsolutePath()+"/"+System.currentTimeMillis()+".jpg");
        if(file.exists()){
            try {
                file.delete();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(Build.VERSION.SDK_INT>=24){
            uri= FileProvider.getUriForFile(activity,activity.getPackageName()+".fileprovider",file);
        }else{
            uri= Uri.fromFile(file);
        }
    }

    //裁剪
    public File crop() {
        Uri uri= getImageContentUri(file);
        File file=new File(activity.getExternalCacheDir().getAbsolutePath()+"/"+System.currentTimeMillis()+".jpg");
        Uri imageUri=Uri.fromFile(file);
        Intent intent=new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        intent.putExtra("CROP","true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri );
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("return-data", false);//data不需要返回,避免图片太大异常
        intent.putExtra("noFaceDetection", true); // no face detection
        activity.startActivityForResult(intent, 3);
        return file;
    }
    public File crop(File file) {
        Uri uri= getImageContentUri(file);
        File outFile=new File(activity.getExternalCacheDir().getAbsolutePath()+"/"+System.currentTimeMillis()+".jpg");
        Intent intent=new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        intent.putExtra("CROP","true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(outFile));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("return-data", false);//data不需要返回,避免图片太大异常
        intent.putExtra("noFaceDetection", true); // no face detection
        activity.startActivityForResult(intent, 3);
        return outFile;
    }
    public Uri getImageContentUri(File imageFile){
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor =activity.getContentResolver().query(
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
                return activity.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    public String handleImage(Intent data) {
        Uri uri = data.getData();
        String imagePath = null;
        if (Build.VERSION.SDK_INT >= 19) {
            if (DocumentsContract.isDocumentUri(activity, uri)) {
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
        File file=new File(imagePath);
        Log.e("123",file.exists()+"");
        return imagePath;
    }
    private String getImagePath(Uri uri, String seletion) {
        String path = null;
        Cursor cursor = activity.getContentResolver().query(uri, null, seletion, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
}