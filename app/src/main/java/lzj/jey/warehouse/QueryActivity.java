package lzj.jey.warehouse;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import lzj.jey.warehouse.adapters.ComInfoAdapter;
import lzj.jey.warehouse.bean.ComInfo;
import lzj.jey.warehouse.db.DbCallBack;
import lzj.jey.warehouse.recyclerview.BaseLinearDecoration;

public class QueryActivity extends AppCompatActivity {


    private RecyclerView q_info;
    private BaseLinearDecoration Verticaldecoration;
    private EditText add_no;
    private WeakReference<Context> weakReference;
    private TextView query_info;
    ComInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        initView();
    }

    private void initView() {
        q_info = (RecyclerView) findViewById(R.id.q_info);
        add_no = (EditText) findViewById(R.id.add_no);
        weakReference = new WeakReference<Context>(this);

        Verticaldecoration = new BaseLinearDecoration(this, LinearLayoutManager.VERTICAL);
        Verticaldecoration.setmDivider(R.drawable.itme_divider);
        query_info = (TextView) findViewById(R.id.query_info);
        adapter = new ComInfoAdapter(new WeakReference<Context>(this));
        q_info.setAdapter(adapter);
        q_info.addItemDecoration(Verticaldecoration);
        q_info.setLayoutManager(new LinearLayoutManager(weakReference.get(), LinearLayoutManager.VERTICAL, false));

    }

    public void onQuery(View view) {
        adapter.getDate().clear();
        adapter.notifyDataSetChanged();
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

                adapter.setDate(result);
                q_info.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(Throwable error) {
                adapter.getDate().clear();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void finish() {

            }
        });
    }
}
