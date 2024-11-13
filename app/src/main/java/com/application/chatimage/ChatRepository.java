package com.application.chatimage;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatRepository {
    private ChatMessageDao messageDao;
    private LiveData<List<ChatMessage>> allMessages;
    private ExecutorService executorService;

    public ChatRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        messageDao = db.chatMessageDao();
        allMessages = messageDao.getAllMessages();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<ChatMessage>> getAllMessages() {
        return allMessages;
    }

    public void insert(ChatMessage message) {
        executorService.execute(() -> {
            messageDao.insert(message);
        });
    }
}
