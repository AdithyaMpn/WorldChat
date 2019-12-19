package com.adithya.worldchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mrecyclerView;
    private EditText message;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("/chat/");

    private List<ChatModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
        actionBar.setTitle("Happy Chatting");

        mrecyclerView = findViewById(R.id.recycler_view);
        message = findViewById(R.id.message);
        ImageView sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
        getmessages();
    }

    private void sendMessage(){

        if(message.getText().toString().isEmpty()){
            Toast.makeText(this,"Enter the Message",Toast.LENGTH_SHORT).show();
        }
        else {
            myRef.push().setValue(new ChatModel("Anonymous", message.getText().toString()));
            message.setText("");
        }
    }

    private void getmessages(){

        final ChatMessageAdapter chatMessageAdapter = new ChatMessageAdapter(list);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list.clear();
                for(DataSnapshot message : dataSnapshot.getChildren()){
                    ChatModel obj = message.getValue(ChatModel.class);
                    list.add(obj);
                }

                chatMessageAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mrecyclerView.setAdapter(chatMessageAdapter);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
