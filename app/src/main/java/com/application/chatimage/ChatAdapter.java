package com.application.chatimage;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.FrameLayout;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private List<ChatMessage> messages;

    public ChatAdapter(List<ChatMessage> messages) {
        this.messages = messages;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_item, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        ChatMessage message = messages.get(position);

        // Load image
        Glide.with(holder.messageImage.getContext())
                .load(message.imageUri)
                .into(holder.messageImage);

        // Format and set the message date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm a", Locale.getDefault());
        String formattedDateTime = dateFormat.format(new Date(message.timestamp));  // Assume timestamp is in milliseconds
        holder.messageTime.setText(formattedDateTime);

        // Set gravity and layout parameters based on whether the message is sent or received
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) holder.messageContainer.getLayoutParams();
        params.gravity = message.isSent ? Gravity.START : Gravity.END;
        holder.messageContainer.setLayoutParams(params);
    }


    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        ImageView messageImage;
        TextView messageTime;
        CardView messageContainer;

        ChatViewHolder(View itemView) {
            super(itemView);
            messageImage = itemView.findViewById(R.id.messageImage);
            messageTime = itemView.findViewById(R.id.messageTime);
            messageContainer = itemView.findViewById(R.id.messageContainer);
        }
    }
}
