package com.necis.chatfirebase.adapter;

/**
 * Created by Jarcode on 2016-03-27.
 */

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.necis.chatfirebase.R;
import com.necis.chatfirebase.item.Chat_Item;

import java.io.File;
import java.util.List;

/**
 * Created by Jarod on 2015-11-16.
 */
public class Chat_Adapter extends RecyclerView.Adapter<Chat_Adapter.Chat_Holder> {
    List<Chat_Item> feedItemList;
    private Activity mContext;

    public Chat_Adapter(Activity context, List<Chat_Item> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }


    @Override
    public Chat_Holder onCreateViewHolder(final ViewGroup viewGroup, final int i) {
        View header = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat, viewGroup, false);
        final Chat_Holder mh = new Chat_Holder(header);
        return mh;
    }

    @Override
    public void onBindViewHolder(final Chat_Holder feedListRowHolder, final int i) {
        String data = feedItemList.get(i).getName();
        if (data.equals("chrom")) {
            feedListRowHolder.cardView.setVisibility(View.VISIBLE);
            feedListRowHolder.txtname.setText(feedItemList.get(i).getName());
            feedListRowHolder.txtmessage.setText(feedItemList.get(i).getMessage());
        } else {
            feedListRowHolder.cardView2.setVisibility(View.VISIBLE);
            feedListRowHolder.txtname2.setText(feedItemList.get(i).getName());
            feedListRowHolder.txtmessage2.setText(feedItemList.get(i).getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void removeAt(int position) {
        feedItemList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, feedItemList.size());
    }

    public class Chat_Holder extends RecyclerView.ViewHolder {
        public TextView txtname, txtmessage;
        public CardView cardView;

        public TextView txtname2, txtmessage2;
        public CardView cardView2;

        public Chat_Holder(View itemView) {
            super(itemView);
            txtmessage = (TextView) itemView.findViewById(R.id.txtmessage);
            txtname = (TextView) itemView.findViewById(R.id.txtname);
            cardView = (CardView) itemView.findViewById(R.id.cardview);


            txtmessage2 = (TextView) itemView.findViewById(R.id.txtmessage2);
            txtname2 = (TextView) itemView.findViewById(R.id.txtname2);
            cardView2 = (CardView) itemView.findViewById(R.id.cardview2);
        }
    }
}
