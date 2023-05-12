package com.app.shopdodientu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.model.ItemMenuModel;

import java.util.List;

public class MenuCategoryAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<ItemMenuModel> itemlist;

    public MenuCategoryAdapter(Context context, int layout, List<ItemMenuModel> itemlist) {
        this.context = context;
        this.layout = layout;
        this.itemlist = itemlist;
    }

    @Override
    public int getCount() {
        return itemlist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView nameCate;
        ImageView icon;
    }
    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(layout,null);

        ImageView imgCate = (ImageView) view.findViewById(R.id.imgCate);
        TextView nameCate = (TextView) view.findViewById(R.id.nameCate);

        ItemMenuModel monHoc = itemlist.get(i);
        nameCate.setText(monHoc.getNameCate());
        imgCate.setImageResource(monHoc.getIcon());
        return view;
    }
}
