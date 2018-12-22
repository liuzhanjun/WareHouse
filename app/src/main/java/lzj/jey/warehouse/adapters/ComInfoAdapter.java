package lzj.jey.warehouse.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import lzj.jey.warehouse.R;
import lzj.jey.warehouse.bean.ComInfo;
import lzj.jey.warehouse.recyclerview.BaseRecyclerAdapter;
import lzj.jey.warehouse.recyclerview.BaseViewHolder;

/**
 * Created by lenovo on 2018-12-07.
 */
public class ComInfoAdapter extends BaseRecyclerAdapter {

    List<ComInfo> date = new ArrayList<ComInfo>();
    private WeakReference<Context> weakContext;


    public ComInfoAdapter(WeakReference<Context> weakContext) {
        this.weakContext = weakContext;
    }

    public void setDate(List<ComInfo> date) {
        this.date = date;
    }

    public List<ComInfo> getDate() {
        return date;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(weakContext.get()).inflate(R.layout.list_item_content, parent, false);
        return new BaseViewHolder(view, onRecyclerItemClick, weakContext.get());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView add_no = (TextView) holder.itemView.findViewById(R.id.add_no);
        TextView add_loc_1 = (TextView) holder.itemView.findViewById(R.id.add_loc_1);
        TextView add_loc_2 = (TextView) holder.itemView.findViewById(R.id.add_loc_2);
        TextView add_loc_3 = (TextView) holder.itemView.findViewById(R.id.add_loc_3);
        TextView add_loc_4 = (TextView) holder.itemView.findViewById(R.id.add_loc_4);
        TextView add_loc_5 = (TextView) holder.itemView.findViewById(R.id.add_loc_5);
        TextView loc_state_1 = (TextView) holder.itemView.findViewById(R.id.loc_state_1);
        TextView loc_state_2 = (TextView) holder.itemView.findViewById(R.id.loc_state_2);
        TextView loc_state_3 = (TextView) holder.itemView.findViewById(R.id.loc_state_3);
        TextView loc_state_4 = (TextView) holder.itemView.findViewById(R.id.loc_state_4);
        TextView loc_state_5 = (TextView) holder.itemView.findViewById(R.id.loc_state_5);

        TextView price1 = (TextView) holder.itemView.findViewById(R.id.price1);
        TextView price2 = (TextView) holder.itemView.findViewById(R.id.price2);
        TextView price3 = (TextView) holder.itemView.findViewById(R.id.price3);

        ComInfo info = date.get(position);
        add_no.setText(info.getComInfoNO());
        add_loc_1.setText(info.getLoc1());
        add_loc_2.setText(info.getLoc2());
        add_loc_3.setText(info.getLoc3());
        add_loc_4.setText(info.getLoc4());
        add_loc_5.setText(info.getLoc5());
        loc_state_1.setText(info.getLocState1() + "");
        loc_state_2.setText(info.getLocState2() + "");
        loc_state_3.setText(info.getLocState3() + "");
        loc_state_4.setText(info.getLocState4() + "");
        loc_state_5.setText(info.getLocState5() + "");

        Float price_1 = info.getPrice1();
        Float price_2 = info.getPrice2();
        Float price_3 = info.getPrice3();
        Log.i("22222", "onBindViewHolder: " + price1);
        if (price_1 != null) {
            price1.setText(String.valueOf(price_1));
        } else {
            price1.setText(String.valueOf(0f));
        }

        if (price_2 != null) {
            price2.setText(String.valueOf(price_2));
        } else {
            price2.setText(String.valueOf(0f));
        }
        if (price_3 != null) {
            price3.setText(String.valueOf(price_3));
        } else {
            price3.setText(String.valueOf(0f));
        }
    }


    private boolean isNotEmpty(String loc1) {
        if (!loc1.equals("") || !loc1.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public int getItemCount() {
        return date.size();
    }
}
