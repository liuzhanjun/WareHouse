package lzj.jey.warehouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import lzj.jey.warehouse.bean.ComInfo;
import lzj.jey.warehouse.db.DbCallBack;

public class CheckActivity extends AppCompatActivity {

    String TAG = "CheckActivity";
    private TextView content;
    private EditText con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        con = (EditText) findViewById(R.id.con);
        content = (TextView) findViewById(R.id.content);
    }

    public void onQuery(View view) {
        content.setText("");
        String conStr = con.getText().toString().trim();
        if (!isNotEmpty(conStr)) {
            DbUtils.ShowMsg(this, "条件不能为空");
            return;
        }

        DbUtils.queryByCon(conStr, new DbCallBack<List<ComInfo>>() {
            @Override
            public void before() {
                Log.i(TAG, "before: ");
            }

            @Override
            public void success(List<ComInfo> result) {
                Log.i(TAG, "success: " + result);
                if (result != null) {
                    if (result.size() > 0) {
                        StringBuffer resultBf = new StringBuffer();
                        for (ComInfo comInfo : result) {
                            resultBf.append(comInfo.toString() + "\n");
                        }
                        Log.i(TAG, "success: " + resultBf.toString());
                        content.setText(resultBf.toString());
                    }
                }

            }

            @Override
            public void failure(Throwable error) {
                Log.i(TAG, "failure: ");
            }

            @Override
            public void finish() {

            }
        });

    }

    private boolean isNotEmpty(String loc1) {
        if (!loc1.equals("") || !loc1.isEmpty()) {
            return true;
        }
        return false;
    }
}
