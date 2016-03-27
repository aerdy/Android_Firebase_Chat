package com.necis.chatfirebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.necis.chatfirebase.adapter.Chat_Adapter;
import com.necis.chatfirebase.item.Chat_Item;
import com.necis.chatfirebase.service.NotificationChat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Chat_Item> feedItemList;
    Chat_Adapter adapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText editChat = (EditText) findViewById(R.id.editChat);
        ImageButton btnSend = (ImageButton) findViewById(R.id.btnSend);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        feedItemList = new ArrayList<>();
        adapterList = new Chat_Adapter(this, feedItemList);
        recyclerView.setAdapter(adapterList);

        //process
        final Firebase conn = new Firebase("https://chatnecis.firebaseio.com/chat");
        conn.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Chat_Item item = dataSnapshot.getValue(Chat_Item.class);
                Log.e("response add", item.getMessage());
                feedItemList.add(item);
                adapterList.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(feedItemList.size()-1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Chat_Item item = dataSnapshot.getValue(Chat_Item.class);
                Log.e("response change", item.getMessage());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Chat_Item item = dataSnapshot.getValue(Chat_Item.class);
                Log.e("response move", item.getMessage());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HHmmss");
                String datetime = sdf.format(new Date());
                String chatmessage = editChat.getText().toString();
                Firebase fr = conn.child("pak_chrom+" + datetime);
                Map<String, String> data = new HashMap<String, String>();
                data.put("id", "1");
                data.put("name", "chrom");
                data.put("message", chatmessage);
                data.put("date", datetime);

                fr.setValue(data, new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        if (firebaseError != null) {
                            Log.e("error", firebaseError.getMessage());
                        } else {
                            Toast.makeText(getApplicationContext(), "Chat Success", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                InputMethodManager inputMethodManager = (InputMethodManager)  getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });

        Intent intent = new Intent(this, NotificationChat.class);
        startService(intent);
    }
}
