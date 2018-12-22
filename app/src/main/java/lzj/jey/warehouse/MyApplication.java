package lzj.jey.warehouse;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.io.File;

import lzj.jey.warehouse.db.DbManager;

/**
 * Created by lenovo on 2018-12-05.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//设置自动更新表结构
        DbUtils.AutoAlterTable();


    }
}
