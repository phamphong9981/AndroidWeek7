package com.example.android_week7;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_week7.Mail;

import java.util.ArrayList;

public class MailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    ArrayList<Mail> arrayList, displayList;
    boolean isChecked;
    private ViewHolder viewHolder;

    public MailAdapter(ArrayList<Mail> arrayList) {
        this.arrayList = arrayList;
        displayList =new ArrayList<>();
        displayList.addAll(arrayList);
    }

    public void search(String key){
        displayList.clear();
        for (Mail item:arrayList
             ) {
            if(item.getSubject().contains(key)){
                displayList.add(item);
            }
        }
        notifyDataSetChanged();
    }

    public void seeAll(){
        displayList.clear();
        displayList.addAll(arrayList);
        notifyDataSetChanged();
    }

    public void seeFavorite(){
        displayList.clear();
        for (Mail item:arrayList
             ) {
            if(item.isCheckbox()==true) displayList.add(item);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.mail,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        viewHolder=(ViewHolder)holder;
        Drawable background=viewHolder.icon.getBackground();
        ((GradientDrawable)background).setColor(Color.parseColor(displayList.get(position).getColor()));
        viewHolder.subject.setText(displayList.get(position).getSubject());
        viewHolder.content1.setText(displayList.get(position).getContent1());
        viewHolder.content2.setText(displayList.get(position).getContent2());
        viewHolder.time.setText(displayList.get(position).getTime());
        viewHolder.icon.setText(displayList.get(position).getIcon());
        isChecked=displayList.get(position).isCheckbox();
        if(isChecked==true){
            viewHolder.checkbox.setImageResource(R.drawable.ic_star_black_24dp);
        }else
            viewHolder.checkbox.setImageResource(R.drawable.ic_star_border_black_24dp);
        viewHolder.setOnClickListenner(new ItemOnClick() {
            @Override
            public void onClick(View v, int i) {
                displayList.get(i).setCheckbox(!displayList.get(i).isCheckbox());
                if(arrayList.get(i).isCheckbox()==true){
                    ((ImageView)v.findViewById(R.id.checkbox)).setImageResource(R.drawable.ic_star_black_24dp);
                }else
                    ((ImageView)v.findViewById(R.id.checkbox)).setImageResource(R.drawable.ic_star_border_black_24dp);
            }
        });
    }

    @Override
    public int getItemCount() {
        return displayList.size();
    }


    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView icon;
        TextView subject;
        TextView content1;
        TextView content2;
        TextView time;
        ImageView checkbox;
        ItemOnClick itemOnClick;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.icon);
            subject=itemView.findViewById(R.id.subject);
            content1=itemView.findViewById(R.id.content1);
            content2=itemView.findViewById(R.id.content2);
            time=itemView.findViewById(R.id.time);
            checkbox=itemView.findViewById(R.id.checkbox);
            itemView.setOnClickListener(this);
        }

        public void setOnClickListenner(ItemOnClick itemOnClick){
            this.itemOnClick=itemOnClick;
        }

        @Override
        public void onClick(View v) {
            itemOnClick.onClick(v,getAdapterPosition());
        }
    }

}
