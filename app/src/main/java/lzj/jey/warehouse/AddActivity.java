package lzj.jey.warehouse;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.suke.widget.SwitchButton;

import java.util.List;

import lzj.jey.warehouse.bean.ComInfo;
import lzj.jey.warehouse.db.DbCallBack;
import lzj.jey.warehouse.ui.SaveOrderDialg;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    EditText add_no;//商品编号
    EditText add_loc_1, add_loc_2, add_loc_3, add_loc_4, add_loc_5;//位置
    EditText price1, price2, price3;
    SwitchButton loc_state_1, loc_state_2, loc_state_3, loc_state_4, loc_state_5;//位置状态
    SwitchButton store1,store2;
    Button btn_save;
    TextView query_info;
    private SaveOrderDialg dialg;
    private ComInfo comInfo;
    Context context;
    private String TAG="add";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        initView();
        initData();

    }

    private void initData() {


    }

    private void initView() {
        add_no = (EditText) findViewById(R.id.add_no);
        add_loc_1 = (EditText) findViewById(R.id.add_loc_1);
        add_loc_2 = (EditText) findViewById(R.id.add_loc_2);
        add_loc_3 = (EditText) findViewById(R.id.add_loc_3);
        add_loc_4 = (EditText) findViewById(R.id.add_loc_4);
        add_loc_5 = (EditText) findViewById(R.id.add_loc_5);

        price1 = (EditText) findViewById(R.id.price1);
        price2 = (EditText) findViewById(R.id.price2);
        price3 = (EditText) findViewById(R.id.price3);

        loc_state_1 = (SwitchButton) findViewById(R.id.loc_state_1);
        loc_state_2 = (SwitchButton) findViewById(R.id.loc_state_2);
        loc_state_3 = (SwitchButton) findViewById(R.id.loc_state_3);
        loc_state_4 = (SwitchButton) findViewById(R.id.loc_state_4);
        loc_state_5 = (SwitchButton) findViewById(R.id.loc_state_5);

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        store1= (SwitchButton) findViewById(R.id.store1);
        store2= (SwitchButton) findViewById(R.id.store2);

        query_info = (TextView) findViewById(R.id.query_info);
    }

    @Override
    public void onClick(View v) {
        int clickId = v.getId();

        switch (clickId) {
            case R.id.btn_save:

                //保存信息
                //获得商品编号
                String add_name = add_no.getText().toString();
                if (add_name.isEmpty() || add_name.equals("")) {
                    DbUtils.ShowMsg(this, "商品编号不能为空");
                    return;
                }
                String loc1 = add_loc_1.getText().toString() + "";
                String loc2 = add_loc_2.getText().toString() + "";
                String loc3 = add_loc_3.getText().toString() + "";
                String loc4 = add_loc_4.getText().toString() + "";
                String loc5 = add_loc_5.getText().toString() + "";
                boolean is_loc_state1 = loc_state_1.isChecked();
                boolean is_loc_state2 = loc_state_2.isChecked();
                boolean is_loc_state3 = loc_state_3.isChecked();
                boolean is_loc_state4 = loc_state_4.isChecked();
                boolean is_loc_state5 = loc_state_5.isChecked();

                boolean store_1=store1.isChecked();
                boolean store_2=store2.isChecked();

                comInfo = new ComInfo();
                comInfo.setComInfoNO(add_name);


                if (isNotEmpty(loc1)) {
                    comInfo.setLoc1(loc1);
                } else {
                    comInfo.setLoc1("无");
                }
                if (isNotEmpty(loc2)) {
                    comInfo.setLoc2(loc2);
                } else {
                    comInfo.setLoc2("无");
                }
                if (isNotEmpty(loc3)) {
                    comInfo.setLoc3(loc3);
                } else {
                    comInfo.setLoc3("无");
                }
                if (isNotEmpty(loc4)) {
                    comInfo.setLoc4(loc4);
                } else {
                    comInfo.setLoc4("无");
                }
                if (isNotEmpty(loc5)) {
                    comInfo.setLoc5(loc5);
                } else {
                    comInfo.setLoc5("无");
                }

                Float price_1 = aFloat(price1.getText().toString());
                if (price_1!=null) {
                    comInfo.setPrice1(price_1);
                } else {
                    comInfo.setPrice1(0f);
                }
                Float price_2 = aFloat(price2.getText().toString());
                if (price_2!=null) {
                    comInfo.setPrice2(price_2);
                } else {
                    comInfo.setPrice2(0f);
                }
                Float price_3 = aFloat(price3.getText().toString());
                if (price_3!=null) {
                    comInfo.setPrice3(price_3);
                } else {
                    comInfo.setPrice3(0f);
                }


                comInfo.setLocState1(checkState(is_loc_state1));
                comInfo.setLocState2(checkState(is_loc_state2));
                comInfo.setLocState3(checkState(is_loc_state3));
                comInfo.setLocState4(checkState(is_loc_state4));
                comInfo.setLocState5(checkState(is_loc_state5));

                comInfo.setStore1(checkState(store_1));
                comInfo.setStore2(checkState(store_2));

                SaveOrderDialg.OnCloseListener listener = new SaveOrderDialg.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            DbUtils.saveInfo(comInfo, context);
                        }
                        dialg.dismiss();
                    }
                };
                dialg = new SaveOrderDialg(this, "是否保存内容?");
                dialg.setListener(listener);
                dialg.show();

                break;
        }
    }

    private boolean isNotEmpty(String loc1) {
        if (!loc1.equals("") || !loc1.isEmpty()) {
            return true;
        }
        return false;
    }

    private Float aFloat(String nub){
        if (nub.equals("null")||nub.isEmpty()){
            return 0.0f;
        }else {
            Float is=Float.parseFloat(nub);
            return is;
        }

    }

    public Integer checkState(boolean state) {

        if (state) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 查询信息
     *
     * @param view
     */
    public void onQuery(View view) {
        query_info.setText("");
        add_loc_1.setText("");
        add_loc_2.setText("");
        add_loc_3.setText("");
        add_loc_4.setText("");
        add_loc_5.setText("");
        price1 .setText("");
        price2.setText("");
        price3.setText("");
        String add_name = add_no.getText().toString().trim();
        if (add_name.isEmpty() || add_name.equals("")) {
            DbUtils.ShowMsg(this, "商品编号不能为空");
            return;
        }

        DbUtils.queryInfo(add_name, new DbCallBack<List<ComInfo>>() {
            @Override
            public void before() {

            }

            @Override
            public void success(List<ComInfo> result) {

                if (result != null) {
                    if (result.size() > 0) {
                        ComInfo info = result.get(0);
                        add_loc_1.setText(info.getLoc1());
                        add_loc_2.setText(info.getLoc2());
                        add_loc_3.setText(info.getLoc3());
                        add_loc_4.setText(info.getLoc4());
                        add_loc_5.setText(info.getLoc5());
                        loc_state_1.setChecked(check(info.getLocState1()));
                        loc_state_2.setChecked(check(info.getLocState2()));
                        loc_state_3.setChecked(check(info.getLocState3()));
                        loc_state_4.setChecked(check(info.getLocState4()));
                        loc_state_5.setChecked(check(info.getLocState5()));
                        store1.setChecked(check(info.getStore1()));
                        store2.setChecked(check(info.getStore2()));
                        price1.setText(String.valueOf(info.getPrice1()));
                        price2.setText(String.valueOf(info.getPrice2()));
                        price3.setText(String.valueOf(info.getPrice3()));
                        query_info.setText(info.toString());
                    }else {
                        loc_state_1.setChecked(false);
                        loc_state_2.setChecked(false);
                        loc_state_3.setChecked(false);
                        loc_state_4.setChecked(false);
                        loc_state_5.setChecked(false);
                        store1.setChecked(false);
                        store2.setChecked(false);
                    }
                }else{
                            loc_state_1.setChecked(false);
                            loc_state_2.setChecked(false);
                            loc_state_3.setChecked(false);
                            loc_state_4.setChecked(false);
                            loc_state_5.setChecked(false);
                    store1.setChecked(false);
                    store2.setChecked(false);
                }
            }

            @Override
            public void failure(Throwable error) {

                Log.i(TAG, "failure: ");
                loc_state_1.setChecked(false);
                loc_state_2.setChecked(false);
                loc_state_3.setChecked(false);
                loc_state_4.setChecked(false);
                loc_state_5.setChecked(false);
                store1.setChecked(false);
                store2.setChecked(false);
            }

            @Override
            public void finish() {

            }
        });



    }

    public boolean check(Integer state) {
        if (state==null){
            return false;
        }else {
            if (state == 1) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void clean(View view) {
        add_no.setText("");
    }
}
