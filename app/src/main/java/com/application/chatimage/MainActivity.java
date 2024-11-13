package com.application.chatimage;

import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ChatViewModel chatViewModel;
    private RecyclerView chatRecyclerView;
    private ChatAdapter chatAdapter;
    private ImageButton btnNamaste, btnSurprise, btnAngry, btnGoodJob, btnGoodbye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize ViewModel
        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);

        // Initialize views
        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        btnNamaste = findViewById(R.id.btnNamaste);
        btnSurprise = findViewById(R.id.btnSurprise);
        btnAngry = findViewById(R.id.btnAngry);
        btnGoodJob = findViewById(R.id.btnGoodJob);
        btnGoodbye = findViewById(R.id.btnGoodbye);

        // Setup RecyclerView
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter = new ChatAdapter(new ArrayList<>());
        chatRecyclerView.setAdapter(chatAdapter);

        // Observe messages
        chatViewModel.getAllMessages().observe(this, messages -> {
            chatAdapter = new ChatAdapter(messages);
            chatRecyclerView.setAdapter(chatAdapter);
            chatRecyclerView.scrollToPosition(messages.size() - 1);
        });

        // Set click listeners
        setupButtonClickListeners();
    }

    private void setupButtonClickListeners() {
        btnNamaste.setOnClickListener(v -> sendMessage(R.drawable.namaste));
        btnSurprise.setOnClickListener(v -> sendMessage(R.drawable.surprise));
        btnAngry.setOnClickListener(v -> sendMessage(R.drawable.angry));
        btnGoodJob.setOnClickListener(v -> sendMessage(R.drawable.good_job));
        btnGoodbye.setOnClickListener(v -> sendMessage(R.drawable.goodbye));
    }

    private void sendMessage(int imageResource) {
        // Create and insert user message
        ChatMessage userMessage = new ChatMessage(
                imageResource,
                System.currentTimeMillis(),
                true
        );
        chatViewModel.insert(userMessage);

        // Send auto response after delay
        chatRecyclerView.postDelayed(() -> sendAutoResponse(), 1000);
    }

    private void sendAutoResponse() {
        int responseType = (int) (Math.random() * 5) + 1;
        int responseImageResource;

        switch(responseType) {
            case 1:
                responseImageResource = R.drawable.namaste;
                break;
            case 2:
                responseImageResource = R.drawable.surprise;
                break;
            case 3:
                responseImageResource = R.drawable.angry;
                break;
            case 4:
                responseImageResource = R.drawable.good_job;
                break;
            default:
                responseImageResource = R.drawable.goodbye;
                break;
        }

        ChatMessage autoResponse = new ChatMessage(
                responseImageResource,
                System.currentTimeMillis(),
                false
        );
        chatViewModel.insert(autoResponse);
    }
}