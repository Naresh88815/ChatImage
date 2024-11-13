package com.application.chatimage;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "messages")
public class ChatMessage {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "image_resource")
    public int imageResource;

    @ColumnInfo(name = "timestamp")
    public long timestamp;

    @ColumnInfo(name = "is_sent")
    public boolean isSent;

    public ChatMessage(int imageResource, long timestamp, boolean isSent) {
        this.imageResource = imageResource;
        this.timestamp = timestamp;
        this.isSent = isSent;
    }
}

