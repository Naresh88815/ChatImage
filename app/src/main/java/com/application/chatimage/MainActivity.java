package com.application.chatimage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Button;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ChatViewModel chatViewModel;
    private RecyclerView chatRecyclerView;
    private ChatAdapter chatAdapter;
    private Button btnUploadImage;

    // Initialize ActivityResultLauncher for image picking
    private final ActivityResultLauncher<Intent> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri selectedImageUri = result.getData().getData();
                    sendMessage(selectedImageUri.toString()); // Send the image URI
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize ViewModel
        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);

        // Initialize views
        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        btnUploadImage = findViewById(R.id.btnUploadImage); // Button for uploading image

        // Setup RecyclerView
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter = new ChatAdapter(new ArrayList<>());
        chatRecyclerView.setAdapter(chatAdapter);

        // Observe messages from ViewModel
        chatViewModel.getAllMessages().observe(this, messages -> {
            chatAdapter = new ChatAdapter(messages);
            chatRecyclerView.setAdapter(chatAdapter);
            chatRecyclerView.scrollToPosition(messages.size() - 1);
        });

        // Set click listeners
        setupButtonClickListeners();
    }

    private void setupButtonClickListeners() {
        // Set the click listener for the image upload button
        btnUploadImage.setOnClickListener(v -> openImagePicker());
    }

    // Opens the image picker dialog
    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        pickImageLauncher.launch(intent);
    }

    // Send message with the selected image URI
    private void sendMessage(String imageUri) {
        ChatMessage userMessage = new ChatMessage(imageUri, System.currentTimeMillis(), true);
        chatViewModel.insert(userMessage);

        // Send an auto response after a short delay
        chatRecyclerView.postDelayed(this::sendAutoResponse, 1000);
    }

    // Auto-response to the user's message
    private void sendAutoResponse() {
        int responseType = (int) (Math.random() * 5) + 1;
        String responseImageUri;

        switch (responseType) {
            case 1:
                responseImageUri = getDrawableUri(R.drawable.namaste);
                break;
            case 2:
                responseImageUri = getDrawableUri(R.drawable.surprise);
                break;
            case 3:
                responseImageUri = getDrawableUri(R.drawable.angry);
                break;
            case 4:
                responseImageUri = getDrawableUri(R.drawable.good_job);
                break;
            default:
                responseImageUri = getDrawableUri(R.drawable.goodbye);
                break;
        }

        ChatMessage autoResponse = new ChatMessage(responseImageUri, System.currentTimeMillis(), false);
        chatViewModel.insert(autoResponse);
    }

    private String getDrawableUri(int resourceId) {
        // Convert the drawable resource ID to a URI string
        return "android.resource://" + getPackageName() + "/" + resourceId;
    }
}
