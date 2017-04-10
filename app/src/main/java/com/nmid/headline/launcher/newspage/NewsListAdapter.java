package com.nmid.headline.launcher.newspage;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nmid.headline.R;
import com.nmid.headline.data.bean.Image;
import com.nmid.headline.data.bean.New;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xwysu on 2017/4/10.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {
    private LayoutInflater inflater;
    public Context context;
    public List<New> news;

    public NewsListAdapter(Context c, List<New> list) {
        news = list;
        context = c;
        inflater = LayoutInflater.from(context);
    }

    public void notifyAll(List<New> list) {
        news = list;
        notifyDataSetChanged();
    }

    public void notifyAdd(List<New> add) {
        news.addAll(add);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_collegenews, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        New temp = news.get(holder.getAdapterPosition());
        List<Image> tempImages=temp.getImage();
        switch (tempImages.size()){
            case 0:
                holder.image1.setVisibility(View.GONE);
                holder.image2.setVisibility(View.GONE);
                holder.image3.setVisibility(View.GONE);
                break;
            case 1:
                Picasso.with(holder.image1.getContext()).load(tempImages.get(0).getUrl()).resize(70,60).centerCrop().into(holder.image1);
                holder.image2.setVisibility(View.GONE);
                holder.image3.setVisibility(View.GONE);
                break;
            case 2:
                Picasso.with(holder.image1.getContext()).load(tempImages.get(0).getUrl()).resize(70,60).centerCrop().into(holder.image1);
                Picasso.with(holder.image1.getContext()).load(tempImages.get(0).getUrl()).resize(70,60).centerCrop().into(holder.image2);
                holder.image1.setVisibility(View.GONE);
                break;
            case 3:
                Picasso.with(holder.image1.getContext()).load(tempImages.get(0).getUrl()).resize(70,60).centerCrop().into(holder.image1);
                Picasso.with(holder.image1.getContext()).load(tempImages.get(0).getUrl()).resize(70,60).centerCrop().into(holder.image2);
                Picasso.with(holder.image1.getContext()).load(tempImages.get(0).getUrl()).resize(70,60).centerCrop().into(holder.image3);
                break;
        }
        holder.title.setText(temp.getTitle());
        holder.info.setText(temp.getTime());
        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=holder.getLayoutPosition();
                    onItemClickListener.OnItemClick(holder.itemView,pos);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return news == null ? 0 : news.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.info)
        TextView info;
        @BindView(R.id.image1)
        ImageView image1;
        @BindView(R.id.image2)
        ImageView image2;
        @BindView(R.id.image3)
        ImageView image3;
        @BindView(R.id.item_news)
        CardView itemNews;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
