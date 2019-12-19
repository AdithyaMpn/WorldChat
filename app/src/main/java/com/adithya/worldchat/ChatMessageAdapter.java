package com.adithya.worldchat;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class ChatMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<ChatModel> chatModelList;

    ChatMessageAdapter(List<ChatModel> chatModelList){
        this.chatModelList = chatModelList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_message, parent, false);
        return new VIHolder(itemLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(chatModelList!=null){
            ((VIHolder) holder).name.setText(chatModelList.get(position).getName());
            ((VIHolder) holder).message.setText(chatModelList.get(position).getMessage());

        }

    }

    @Override
    public int getItemCount() {
        return chatModelList.size();


    }

    private class VIHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView message;
        LinearLayout chat_layout;
        LinearLayout chat_text_layout;

        public VIHolder(final View itemLayoutView) {
            super(itemLayoutView);
            name = (TextView) itemLayoutView.findViewById(R.id.name);
            message = (TextView) itemLayoutView.findViewById(R.id.message);
            chat_layout = (LinearLayout)itemLayoutView.findViewById(R.id.chat_layout);
            chat_text_layout = (LinearLayout)itemLayoutView.findViewById(R.id.chat_text_layout);

        }
    }
}
