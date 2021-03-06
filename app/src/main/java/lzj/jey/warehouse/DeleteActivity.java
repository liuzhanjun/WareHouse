package lzj.jey.warehouse;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import lzj.jey.warehouse.bean.ComInfo;
import lzj.jey.warehouse.db.DbCallBack;
import lzj.jey.warehouse.ui.SaveOrderDialg;

public class DeleteActivity extends AppCompatActivity {

    EditText add_no;
    TextView query_info;
    Context context;
    private SaveOrderDialg dialg;
    private String add_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_delete);
        add_no = (EditText) findViewById(R.id.add_no);
        query_info = (TextView) findViewById(R.id.query_info);
    }

    public void onQuery(View view) {
        query_info.setText("");
        String add_name = add_no.getText().toString();
        if (add_name.isEmpty() || add_name.equals("")) {
            DbUtils.ShowMsg(this, "商品编号不能为空");
            return;
        }
        DbUtils.queryInfo2(add_name, new DbCallBack<List<ComInfo>>() {
            @Override
            public void before() {

            }

            @Override
            public void success(List<ComInfo> result) {
                StringBuffer buffer = new StringBuffer();
                for (ComInfo comInfo : result) {
                    buffer.append(comInfo.toString() + "\n");
                }
                query_info.setText(buffer.toString());
            }

            @Override
            public void failure(Throwable error) {

            }

            @Override
            public void finish() {

            }
        });
    }

    public void onDelete(View view) {
        add_name = add_no.getText().toString();
        if (add_name.isEmpty() || add_name.equals("")) {
            DbUtils.ShowMsg(this, "商品编号不能为空");
            return;
        }

        SaveOrderDialg.OnCloseListener listener = new SaveOrderDialg.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if (confirm) {
                    DbUtils.delete(add_name, new DbCallBack<Integer>() {

                        @Override
                        public void before() {

                        }

                        @Override
                        public void success(Integer result) {
                            DbUtils.ShowMsg(context, "删除成功");
                        }

                        @Override
                        public void failure(Throwable error) {
                            DbUtils.ShowMsg(context, "3");
                        }

                        @Override
                        public void finish() {

                        }
                    });
                }
                dialg.dismiss();
            }
        };
        dialg = new SaveOrderDialg(this, "是否删除此商品?");
        dialg.setListener(listener);
        dialg.show();

    }
}
