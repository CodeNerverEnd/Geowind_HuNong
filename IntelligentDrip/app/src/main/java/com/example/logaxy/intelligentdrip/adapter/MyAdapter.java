package com.example.logaxy.intelligentdrip.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.logaxy.intelligentdrip.R;
import com.example.logaxy.intelligentdrip.entity.InfusionData;

import java.util.List;

/**
 * Created by logaxy on 2017/3/11.
 */

public class MyAdapter extends ArrayAdapter<InfusionData> {
    private int resourceId;

    public MyAdapter(Context context, int id, List<InfusionData> obj) {
        super(context, id, obj);
        resourceId = id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InfusionData item = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.building_num = (TextView) view.findViewById(R.id.tv_buildingNum);
            viewHolder.room_num = (TextView) view.findViewById(R.id.tv_roomNum);
            viewHolder.bed_num = (TextView) view.findViewById(R.id.tv_bedNum);
            viewHolder.tatol_soup = (TextView) view.findViewById(R.id.tv_totalSoup);
            viewHolder.residue_soup = (TextView) view.findViewById(R.id.tv_residueSoup);

            view.setTag(viewHolder);


        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();

        }


        viewHolder.building_num.setText(item.getBuilding_num()+"");
        viewHolder.room_num.setText(item.getRoom_num()+"");
        viewHolder.bed_num.setText(item.getBed_num()+"");
        viewHolder.tatol_soup.setText(item.getTotal_soup()+"ml");
        viewHolder.residue_soup.setText(item.getResidue_soup()+"ml");



        return view;
    }

    class ViewHolder {

        TextView building_num;
        TextView room_num;
        TextView bed_num;
        TextView tatol_soup;
        TextView residue_soup;

    }
}
