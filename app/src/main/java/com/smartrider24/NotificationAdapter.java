package com.smartrider24;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.smartrider24.model.notification.NotificationDetail;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    private List<NotificationDetail> couponsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, descriptionless, descriptionMore, datee, more, less;
        ImageView imageView;
        CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.nImage);
            title = (TextView) view.findViewById(R.id.nProdName);
            descriptionless = (TextView) view.findViewById(R.id.nPInfoLess);
            descriptionMore = (TextView) view.findViewById(R.id.nPInfoMore);
            datee = (TextView) view.findViewById(R.id.nTime);
            cardView = view.findViewById(R.id.cardViewN);
            more = view.findViewById(R.id.nMore);
            less = view.findViewById(R.id.nless);
        }
    }


    public NotificationAdapter(List<NotificationDetail> couponsList) {
        this.couponsList = couponsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_list_notice, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final NotificationDetail cc = couponsList.get(position);
        if (cc.getNotificationDate() != null) {
            holder.datee.setText(cc.getNotificationDate());
        }
        if (cc.getNotificationTopicHeading() != null) {
            holder.title.setText(cc.getNotificationTopicHeading());
        }
        if (cc.getNotificationDetails() != null) {
            holder.descriptionless.setText(cc.getNotificationDetails());
        }


        //  Picasso.get().load(cc.getImage()).into(holder.imageView);
//        holder.more.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                holder.more.setVisibility(View.GONE);
//                holder.less.setVisibility(View.VISIBLE);
//                holder.descriptionless.setVisibility(View.GONE);
//                holder.descriptionMore.setVisibility(View.VISIBLE);
//            }
//        });
//
//        holder.less.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                holder.less.setVisibility(View.GONE);
//                holder.more.setVisibility(View.VISIBLE);
//                holder.descriptionless.setVisibility(View.VISIBLE);
//                holder.descriptionMore.setVisibility(View.GONE);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if (couponsList != null && couponsList.size() > 0)
            return couponsList.size();
        return 0;
    }
}
