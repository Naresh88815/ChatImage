package com.application.chatimage;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "messages")
public class ChatMessage {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "image_uri")
    public String imageUri;

    @ColumnInfo(name = "timestamp")
    public long timestamp;

    @ColumnInfo(name = "is_sent")
    public boolean isSent;

    // Constructor for string URI
    public ChatMessage(String imageUri, long timestamp, boolean isSent) {
        this.imageUri = imageUri;
        this.timestamp = timestamp;
        this.isSent = isSent;
    }

    // Constructor for resource ID (if you change the class to accept it)
    public ChatMessage(int imageResource, long timestamp, boolean isSent) {
        this.imageUri = String.valueOf(imageResource);
        this.timestamp = timestamp;
        this.isSent = isSent;
    }
}



