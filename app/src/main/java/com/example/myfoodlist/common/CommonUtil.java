package com.example.myfoodlist.common;

import android.os.Environment;
import com.google.android.gms.common.internal.service.Common;

import java.io.File;

import static com.gun0912.tedpermission.provider.TedPermissionProvider.context;

public class CommonUtil {

    private CommonUtil(){};
    public static String getImgPath(){
        File dir = Environment.getExternalStorageDirectory();
        String abPath = dir.getAbsolutePath();
        String packageName = context.getPackageName();
        return abPath + "/android/data/" + packageName + "/files/Pictures/";
    }

    public static String getPostCodeUrl(){
        return "http://172.30.1.26:8080/daumPostCode";
    }
}
