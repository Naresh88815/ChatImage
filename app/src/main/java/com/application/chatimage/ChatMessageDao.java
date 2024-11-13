package com.application.chatimage;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.lifecycle.LiveData;
import java.util.List;

@Dao
public interface ChatMessageDao {

    // Query to get all chat messages ordered by timestamp
    @Query("SELECT * FROM messages ORDER BY timestamp ASC")
    LiveData<List<ChatMessage>> getAllMessages();

    // Method to insert a chat message
    @Insert
    void insert(ChatMessage message);

    // Query to delete all chat messages
    @Query("DELETE FROM messages")
    void deleteAllMessages();

    // Query to get the count of all messages, returning LiveData for UI updates
    @Query("SELECT COUNT(*) FROM messages")
    LiveData<Integer> getMessageCount();
}
