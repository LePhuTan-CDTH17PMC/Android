package com.example.da_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;


public class Tra_loi extends AppCompatActivity{
    private MediaPlayer mediaPlayer;
    private TextView textView;
    RingProgressBar progressBar;
    Handler myHandler;
    int progress=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tra_loi);
        playSound(R.raw.vexento);
        progressBar=findViewById(R.id.progress_bar_1);
        ringProgress();
        Countdown();
    }
    //Khai báo hàm cho progress dạng ring
    public void ringProgress(){
        progressBar.setOnProgressListener(new RingProgressBar.OnProgressListener() {
            @Override
            public void progressToComplete() {
                Toast.makeText(Tra_loi.this,"Hết thời gian",Toast.LENGTH_LONG).show();
            }
        });
        myHandler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==0){
                    if (progress<100){
                        progress++;
                        progressBar.setProgress(progress);
                    }
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100;i++)
                try {
                    Thread.sleep(100);
                    myHandler.sendEmptyMessage(0);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    //Hàm đếm ngược
    public void Countdown(){
        textView=findViewById(R.id.txtTime);
        CountDownTimer w =new CountDownTimer(10000, 1000) {
            public void onTick(long mil) {
                textView.setText( mil/1000+" s");
            }
            public void onFinish() {
                textView.setText("0 s");
            }
        }.start();
    }
    //Play nhạc
    public void playSound(int type){
        mediaPlayer= MediaPlayer.create(this,type);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    // dừng âm thanh khi activity pause
    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        playSound(R.raw.vexento);
    }

    // dừng âm thanh khi activity destroy
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }
    //Set sự kiện onClick


}
