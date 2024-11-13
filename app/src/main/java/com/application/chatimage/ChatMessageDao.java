package com.application.chatimage;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.lifecycle.LiveData;
import java.util.List;

@Dao
public interface ChatMessageDao {
    @Query("SELECT * FROM messages ORDER BY timestamp ASC")
    LiveData<List<ChatMessage>> getAllMessages();

    @Insert
    void insert(ChatMessage message);

    @Query("DELETE FROM messages")
    void deleteAllMessages();

    @Query("SELECT COUNT(*) FROM messages")
    int getMessageCount();
}
