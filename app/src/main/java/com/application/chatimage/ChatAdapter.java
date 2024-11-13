package com.application.chatimage;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.FrameLayout;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

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

        // Get the FrameLayout.LayoutParams
        FrameLayout.LayoutParams params =
                (FrameLayout.LayoutParams) holder.messageContainer.getLayoutParams();

        if (message.isSent) {
            // Align to right
            params.gravity = Gravity.START;
            holder.messageContainer.setLayoutParams(params);
        } else {
            // Align to left
            params.gravity = android.view.Gravity.END;
            holder.messageContainer.setLayoutParams(params);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        ImageView messageImage;
        CardView messageContainer;

        ChatViewHolder(View itemView) {
            super(itemView);
            messageImage = itemView.findViewById(R.id.messageImage);
            messageContainer = itemView.findViewById(R.id.messageContainer);
        }
    }
}