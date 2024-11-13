package com.application.chatimage;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ChatViewModel extends AndroidViewModel {
    private ChatRepository repository;
    private LiveData<List<ChatMessage>> allMessages;

    public ChatViewModel(Application application) {
        super(application);
        repository = new ChatRepository(application);
        allMessages = repository.getAllMessages();
    }

    public LiveData<List<ChatMessage>> getAllMessages() {
        return allMessages;
    }

    public void insert(ChatMessage message) {
        repository.insert(message);
    }
}