package com.geowind.hunong.global.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.geowind.hunong.R;

import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
<<<<<<< HEAD:HuNong/hunong/src/main/java/com/geowind/hunong/global/adapter/ListViewAdapter.java
public class ListViewAdapter extends ArrayAdapter<Object> {
=======
public class  TestListViewAdapter extends ArrayAdapter<Object> {
>>>>>>> 54b583ff3a2abf407a623d33603764e71371ffdd:HuNong/hunong/src/main/java/com/geowind/hunong/global/TestListViewAdapter.java

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    public ListViewAdapter(Context context, List<Object> objects) {
        super(context, 0, objects);
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_CELL;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            switch (getItemViewType(position)) {
                case TYPE_HEADER: {
                    convertView = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.tools_list_item_card_big, parent, false);
                }
                break;
                case TYPE_CELL: {
                    convertView = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.tools_list_item_card_small, parent, false);
                }
                break;
            }
        }
        return convertView;
    }
}