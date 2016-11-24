package com.geowind.hunong.global.adapter;

import android.content.Context;

import com.geowind.hunong.R;
import com.geowind.hunong.entity.LibSearch;
import com.geowind.hunong.entity.SearchBean;
import com.geowind.hunong.utils.CommonAdapter;
import com.geowind.hunong.utils.ViewHolder;

import java.util.List;

/**
 * Created by zhangwen on 2016/11/16.
 */

public class SearchAdapter extends CommonAdapter<LibSearch> {

    public SearchAdapter(Context context, List<LibSearch> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, int position) {
        holder.setText(R.id.item_search_tv_title,mData.get(position).getTitle());
    }


}
