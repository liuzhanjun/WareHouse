package lzj.jey.warehouse;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import java.io.File;

import lzj.jey.warehouse.db.DbManager;

public class MainActivity extends AppCompatActivity {

    CardView card_1;
    CardView card_2;
    String path;
    private String path1;
    String dbName = "Ware.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initdb();

        card_1 = (CardView) findViewById(R.id.card_1);
        card_2 = (CardView) findViewById(R.id.card_2);

    }

    private void initdb() {

        path1 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/jey/";
        path = path1 + dbName;
        if (Build.VERSION.SDK_INT >= 23) {
            //检查权限
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CALENDAR}, 1);

            } else {
                DbManager.dbManager.getInstans(getApplicationContext()).OpenDb(path);
            }
        } else {
            //创建数据库
            //先判断这个文件夹是否存在
            File file = new File(path1);
            if (!file.exists()) {
                file.mkdirs();
            }
            path = path1 + dbName;
            //创建并打开数据库 一般情况下 getReadableDatabase 和getWritableDatabase是一样的，只有
            //在磁盘和数据库权限下打开对应的读写数据库
            DbManager.dbManager.getInstans(getApplicationContext())
                    .OpenDb(path);
            //创建表
            DbUtils.CreateTable(this);

            //设置自动更新表结构
            DbUtils.AutoAlterTable();
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //创建数据库
                //先判断这个文件夹是否存在

                File file = new File(path1);
                if (!file.exists()) {
                    file.mkdirs();
                }
                path = path1 + dbName;
                //创建并打开数据库 一般情况下 getReadableDatabase 和getWritableDatabase是一样的，只有
                //在磁盘和数据库权限下打开对应的读写数据库
                DbManager.dbManager.getInstans(getApplicationContext())
                        .OpenDb(path);
                //创建表
                DbUtils.CreateTable(this);

                //设置自动更新表结构
                DbUtils.AutoAlterTable();
            }
        }
    }

    public void to_add(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    public void to_query(View view) {

        Intent intent = new Intent(this, QueryActivity.class);
        startActivity(intent);
    }

    public void to_delete(View view) {
        Intent intent = new Intent(this, DeleteActivity.class);
        startActivity(intent);
    }
}
