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
        holder.messageImage.setImageResource(message.imageResource);

        // Format the timestamp to a readable time string
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String formattedTime = dateFormat.format(new Date(message.timestamp));
        holder.messageTime.setText(formattedTime);

        // Align the message based on isSent status
        FrameLayout.LayoutParams params =
                (FrameLayout.LayoutParams) holder.messageContainer.getLayoutParams();

        if (message.isSent) {
            params.gravity = Gravity.START;
        } else {
            params.gravity = Gravity.END;
        }
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
