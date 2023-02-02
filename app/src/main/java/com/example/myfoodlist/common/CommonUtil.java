package com.example.myfoodlist.common;

import android.os.Environment;

import java.io.File;

import static com.gun0912.tedpermission.provider.TedPermissionProvider.context;

public class CommonUtil {

    public String img_path;

    public String getImgPath(){
        File dir = Environment.getExternalStorageDirectory();
        String abPath = dir.getAbsolutePath();
        String packageName = context.getPackageName();
        this.img_path = abPath + "/android/data/" + packageName + "/files/Pictures/";
        return this.img_path;
    }
}
