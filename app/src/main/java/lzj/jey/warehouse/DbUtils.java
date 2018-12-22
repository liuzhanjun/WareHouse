package lzj.jey.warehouse;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.List;

import lzj.jey.warehouse.bean.ComInfo;
import lzj.jey.warehouse.bean.DbDataSize;
import lzj.jey.warehouse.db.DbCallBack;
import lzj.jey.warehouse.db.DbCallType;
import lzj.jey.warehouse.db.DbManager;

/**
 * Created by lenovo on 2018-12-05.
 */
public class DbUtils {

    public static String TAG = "DBUtils";

    public static void ShowMsg(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }

    /**
     * 创建数据表
     */
    public static void CreateTable(final Context context) {
        DbManager.dbManager.createTable(ComInfo.class, new DbCallBack<Boolean>() {
            @Override
            public void before() {

            }

            @Override
            public void success(Boolean result) {
                ShowMsg(context, "创建成功");
            }

            @Override
            public void failure(Throwable error) {
            }

            @Override
            public void finish() {

            }
        });


    }


    public static void AutoAlterTable() {
        DbManager.dbManager.AlterTable(ComInfo.class, new DbCallBack<Boolean>() {
            @Override
            public void before() {

            }

            @Override
            public void success(Boolean result) {

            }

            @Override
            public void failure(Throwable error) {

            }

            @Override
            public void finish() {

            }
        });

    }


    public static void UpdateInfo(ComInfo comInfo, final Context context) {
        DbManager.dbManager.update(comInfo, "ComInfoNO=?", new String[]{comInfo.getComInfoNO()}, new DbCallBack<Integer>() {
            @Override
            public void before() {

            }

            @Override
            public void success(Integer result) {
                ShowMsg(context, "保存成功");
            }

            @Override
            public void failure(Throwable error) {
                ShowMsg(context, "保存失败");
            }

            @Override
            public void finish() {

            }
        });

    }

    public static void insertInfo(ComInfo comInfo, final Context context) {
        DbManager.dbManager.insert(comInfo, new DbCallBack<Long>() {
            @Override
            public void before() {

            }

            @Override
            public void success(Long result) {
                ShowMsg(context, "保存成功");
            }

            @Override
            public void failure(Throwable error) {
                ShowMsg(context, "保存失败");
            }

            @Override
            public void finish() {

            }
        });
    }


    public static void queryInfo2(String no, DbCallBack<List<ComInfo>> callback) {
        ComInfo info = new ComInfo(1);
        info.setComInfoNO(no);
        DbManager.dbManager.query(info, "ComInfoNO LIKE ?", new String[]{"%" + no + "%"}, callback);
    }

    public static void queryInfo(String no, DbCallBack<List<ComInfo>> callback) {
        ComInfo info = new ComInfo(1);
        info.setComInfoNO(no);
        DbManager.dbManager.query(info, "ComInfoNO = ?", new String[]{no}, callback);
    }

    public static List<ComInfo> queryInfoSyn(String no, DbCallBack<List<ComInfo>> callback) {
        ComInfo info = new ComInfo(1);
        info.setComInfoNO(no);
        return DbManager.dbManager.querySyn(info, "ComInfoNO = ?", new String[]{no}, new DbCallType<List<ComInfo>>() {
            @Override
            public Type getmType() {
                return super.getmType();
            }
        });
    }

    public static void queryByCon(String con,DbCallBack<List<ComInfo>> callBack) {
        //select * from Cominfo where loc1 like '%C1%' or loc2 like '%C1%' or loc3 like '%C1%' or loc4 like '%C1%' or loc5 like '%C1%'
        DbManager.dbManager.query("select * from Cominfo where loc1 like ? or loc2 like ? or loc3 like ? or loc4 like ? or loc5 like ?", new String[]{"%" + con + "%", "%" + con + "%", "%" + con + "%","%" + con + "%","%" + con + "%"}, callBack);
    }

    /**
     * 保存信息
     *
     * @param comInfo
     */
    public static void saveInfo(final ComInfo comInfo, final Context context) {

        //先查询是否有这个编号

        String sql = "select count(_id) as _size from ComInfo where ComInfoNO=?";
        DbManager.dbManager.query(sql, new String[]{comInfo.getComInfoNO()}, new DbCallBack<List<DbDataSize>>() {
            @Override
            public void before() {

            }

            @Override
            public void success(List<DbDataSize> result) {
                int size = result.get(0).get_size();
                if (size > 0) {
                    //更新信息
                    UpdateInfo(comInfo, context);
                } else {
                    //插入信息
                    insertInfo(comInfo, context);
                }
            }

            @Override
            public void failure(Throwable error) {

            }

            @Override
            public void finish() {

            }
        });

    }

    public static void delete(String add_name, DbCallBack<Integer> dbCallBack) {
        DbManager.dbManager.delete(ComInfo.class, "ComInfoNO=?", new String[]{add_name}, dbCallBack);
    }
}
