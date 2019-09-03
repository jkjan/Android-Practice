package com.example.chatheads;

import android.app.Service;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import static android.view.WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

public class ChatHead extends Service {

    int exp = 0;

    private WindowManager windowManager;
    private View chatheadView;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        exp = (int) intent.getExtras().get("which");
        Log.i("받았음", Integer.toString(exp) + "를 받아요");
        TextView whatthefuck = chatheadView.findViewById(R.id.kakao);
        whatthefuck.setText(Integer.toString(exp));
        return exp;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        chatheadView = LayoutInflater.from(this).inflate(R.layout.bubble, null);
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                //WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
                );

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;

        Button close = chatheadView.findViewById(R.id.bt_finish);
        Log.i("www", Integer.toString(exp));
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopSelf();
            }
        });

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        windowManager.addView(chatheadView, params);

        RelativeLayout chatheadBubble = chatheadView.findViewById(R.id.chathead_bubble);
        chatheadBubble.setOnTouchListener(
                new View.OnTouchListener() {
                    private int initialX;
                    private int initialY;
                    private float touchX;
                    private float touchY;
                    private int lastAction;

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            initialX = params.x;
                            initialY = params.y;

                            touchX = event.getRawX();
                            touchY = event.getRawY();

                            lastAction = event.getAction();

                            return true;
                        }

                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            if (lastAction == MotionEvent.ACTION_DOWN) {
                                Button button = new Button(ChatHead.this);
                                button.setText("Close");

                                RelativeLayout layout = chatheadView.findViewById(R.id.chathead_bubble);
                                layout.addView(button);

                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        stopSelf();
                                    }
                                });
                            }

                            lastAction = event.getAction();
                            return true;
                        }

                        if (event.getAction() == MotionEvent.ACTION_MOVE) {
                            params.x = initialX + (int) (event.getRawX() - touchX);
                            params.y = initialY + (int) (event.getRawY() - touchY);

                            windowManager.updateViewLayout(chatheadView, params);
                            lastAction = event.getAction();
                            return true;
                        }

                        return false;
                    }
                }
        );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (chatheadView != null) {
            windowManager.removeView(chatheadView);
        }
    }
}