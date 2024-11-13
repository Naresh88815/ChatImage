package com.application.chatimage;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatRepository {
    private final AppDatabase database;
    private final ExecutorService executorService;

    public ChatRepository(Application application) {
        this.database = AppDatabase.getInstance(application);
        this.executorService = Executors.newSingleThreadExecutor();
    }

    // DAO methods
    public LiveData<List<ChatMessage>> getAllMessages() {
        return database.chatMessageDao().getAllMessages();
    }

    public void insert(ChatMessage message) {
        executorService.execute(() -> {
            database.chatMessageDao().insert(message);
        });
    }
}
