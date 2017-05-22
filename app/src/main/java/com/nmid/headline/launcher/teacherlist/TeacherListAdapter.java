package com.nmid.headline.launcher.teacherlist;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nmid.headline.R;
import com.nmid.headline.data.bean.Teacher;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xwysu on 2017/5/20.
 */

public class TeacherListAdapter extends RecyclerView.Adapter<TeacherListAdapter.ViewHolder> {
    private LayoutInflater inflater;
    public Context context;
    public List<Teacher> teachers;

    public TeacherListAdapter(Context c, List<Teacher> list) {
        teachers = list;
        context = c;
        inflater = LayoutInflater.from(context);
    }

    public void notifyAll(List<Teacher> list) {
        teachers = list;
        notifyDataSetChanged();
    }

    public void notifyAdd(List<Teacher> add) {
        teachers.addAll(add);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int position, Teacher teacher);
    }

    private TeacherListAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(TeacherListAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_teacher, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Teacher temp=teachers.get(holder.getAdapterPosition());
        holder.teacherName.setText(temp.getName());
        Picasso.with(holder.teacherIcon.getContext()).load(temp.getImgUrl()).resize(90,130).centerInside().into(holder.teacherIcon);
        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=holder.getLayoutPosition();
                    onItemClickListener.OnItemClick(holder.itemView,pos,teachers.get(pos));
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return teachers == null ? 0 : teachers.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.teacherIcon)
        ImageView teacherIcon;
        @BindView(R.id.teacherName)
        TextView teacherName;
        @BindView(R.id.item_teacher)
        CardView itemTeacher;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
