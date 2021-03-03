package com.britishbroadcast.activityconversationapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView conversationTextView;
    private EditText messageToSendEditText;
    private Button sendButton;
    public static final String  BUNDLE_KEY = "CONVERSATION_KEY";
    private String conversationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conversationTextView = findViewById(R.id.conversation_textView);
        messageToSendEditText = findViewById(R.id.messageToSend_editText);
        sendButton = findViewById(R.id.send_button);

        //if(getIntent().getStringExtra(BUNDLE_KEY) == null) {
            conversationTextView.setText(getString(R.string.conversation_text));
            conversationList= "";
//        }else {
//            conversationList = getIntent().getStringExtra(BUNDLE_KEY);
//            conversationTextView.setText(conversationList);
//        }

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder message = new StringBuilder();
                message.append(conversationList).append("\n").append(getString(R.string.user_1_text)).append(": ").append(messageToSendEditText.getText());
                conversationList = message.toString();
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra(BUNDLE_KEY, conversationList);
                messageToSendEditText.setText("");
                startActivityForResult(intent, 707);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        conversationList = data.getStringExtra(BUNDLE_KEY);
        conversationTextView.setText(conversationList);
    }

}