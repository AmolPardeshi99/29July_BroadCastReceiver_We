package com.example.broadcastreceiver_we;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mBtnSend;
    private TextView mTvData;
    private LocalBroadcastManager broadcastManager;
    private LocalReceiver localReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        broadcastManager = LocalBroadcastManager.getInstance(this);
        mBtnSend = findViewById(R.id.btnSend);
        mTvData = findViewById(R.id.tvMsai);
        registerLocal();
        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.and03");
                intent.putExtra("key", "Hello Masai School");
                broadcastManager.sendBroadcast(intent);

            }
        });
    }

    private void registerLocal() {
        localReceiver = new LocalReceiver();
        IntentFilter intentFilter = new IntentFilter("com.and03");
        broadcastManager.registerReceiver(localReceiver, intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        broadcastManager.unregisterReceiver(localReceiver);
    }

    private class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String data = intent.getStringExtra("key");
                mTvData.setText(data);
            }
        }
    }
}