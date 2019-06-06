package com.wiser.chatframe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ChatAdapter adapter;

    private RecyclerView rlvChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rlvChat = findViewById(R.id.rlv_chat);

        adapter = new ChatAdapter(this);

        rlvChat.setLayoutManager(new LinearLayoutManager(this));

        rlvChat.setAdapter(adapter);

        addData();
    }

    private void addData() {
        List<ChatModel> chatModels = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ChatModel chatModel = new ChatModel();
            if (i % 2 == 0) {
                chatModel.messageType = "left";
                chatModel.name = "小王";
                chatModel.content = "你好啊" + i;
            } else {
                chatModel.messageType = "right";
                chatModel.name = "小李";
                chatModel.content = "我很好" + i;
            }
            chatModels.add(chatModel);
        }

        if (adapter != null) {
            adapter.setItems(chatModels);
        }
    }
}
