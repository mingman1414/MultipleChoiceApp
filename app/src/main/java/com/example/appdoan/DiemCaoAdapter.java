package com.example.appdoan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DiemCaoAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Diem> dienList;

    public DiemCaoAdapter(Context context, int layout, List<Diem> dienList) {
        this.context = context;
        this.layout = layout;
        this.dienList = dienList;
    }

    @Override
    public int getCount() {
        return dienList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }



    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView txtId, txtTen, txtDiem;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtId    = (TextView) view.findViewById(R.id.textViewSTT);
            holder.txtTen   = (TextView) view.findViewById(R.id.textViewTen);
            holder.txtDiem  = (TextView) view.findViewById(R.id.textViewDiem);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Diem diem = dienList.get(i);
        holder.txtId.setText(diem.getId()+"");
        holder.txtTen.setText(diem.getTen());
        holder.txtDiem.setText(diem.getDiem()+"");

        return view;
    }
}
