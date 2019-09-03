package com.example.chatheads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    public final static int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                     Uri.parse("package:" + getPackageName()));

            startActivityForResult(intent, PERMISSION_REQUEST_CODE);
        }

        else {
            showChatHead(1);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                showChatHead(1);
                Log.i("ㄴㅇㄴ", "ㄴㅇㄴㅇ");

            }
        }
    }

    public void showChatHead(int which) {
        Intent intent = new Intent(MainActivity.this, ChatHead.class);
        Log.i("www", Integer.toString(which) + " 를 전달함");
        intent.putExtra("which", which);
        startService(intent);
    }

}
