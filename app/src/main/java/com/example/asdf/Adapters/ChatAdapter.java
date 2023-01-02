package com.example.asdf.Adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asdf.Model.ChatMessage;
import com.example.asdf.databinding.ItemContainerReceiverBackgroundBinding;
import com.example.asdf.databinding.ItemContainerSentMessageBinding;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private final List<ChatMessage> chatMessages;
    private Bitmap receiverProfileImage;
    private final String senderId;

    public void setReceiverProfileImage(Bitmap bitmap){
        receiverProfileImage = bitmap;
    }

    public ChatAdapter(List<ChatMessage> chatMessages, Bitmap receiverProfileImage, String senderId) {
        this.chatMessages = chatMessages;
        this.receiverProfileImage = receiverProfileImage;
        this.senderId = senderId;
    }

    public static final int VIEW_TYPE_SENT = 1;
    public static final int VIEW_TYPE_RECEIVER = 2;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType== VIEW_TYPE_SENT){
            return new sentMessageViewHolder(ItemContainerSentMessageBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
        }else {
            return new receiverMessageViewHolder(ItemContainerReceiverBackgroundBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == VIEW_TYPE_SENT){
            ((sentMessageViewHolder)holder).setData(chatMessages.get(position));
        }
        else {
            ((receiverMessageViewHolder)holder).setData(chatMessages.get(position),receiverProfileImage);
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }


    @Override
    public int getItemViewType(int position) {

        if (chatMessages.get(position).senderId.equals(senderId)){
            return VIEW_TYPE_SENT;
        }else {
            return VIEW_TYPE_RECEIVER;
        }


    }

    static class sentMessageViewHolder extends RecyclerView.ViewHolder{

        private final ItemContainerSentMessageBinding binding;
        sentMessageViewHolder(ItemContainerSentMessageBinding itemContainerSentMessageBinding){
            super(itemContainerSentMessageBinding.getRoot());
            binding = itemContainerSentMessageBinding;
        }
        void setData(ChatMessage chatMessage){
            binding.textMessage.setText(chatMessage.message);
            binding.textDateTime.setText(chatMessage.dateTime);
        }
    }

    static class receiverMessageViewHolder extends RecyclerView.ViewHolder{
        private final ItemContainerReceiverBackgroundBinding binding;
        receiverMessageViewHolder(ItemContainerReceiverBackgroundBinding itemContainerReceiverBackgroundBinding){
            super(itemContainerReceiverBackgroundBinding.getRoot());
            binding = itemContainerReceiverBackgroundBinding;
        }
        void setData(ChatMessage chatMessage, Bitmap receiverProfileImage){
            binding.textMessage.setText(chatMessage.message);
            binding.textDateTime.setText(chatMessage.dateTime);
            if (receiverProfileImage !=null){
                binding.ImageProfile.setImageBitmap(receiverProfileImage);
            }
        }
    }
}
