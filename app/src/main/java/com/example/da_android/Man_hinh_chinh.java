package com.example.da_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Man_hinh_chinh extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);
        Start();
        playSound(R.raw.vexento);
    }
    public void Play(View view) {
        Intent intent = new Intent(Man_hinh_chinh.this, Chon_linh_vuc.class);
        startActivity(intent);
    }
    public void Start(){
        Intent intent=this.getIntent();
        TextView tenTK=findViewById(R.id.txtTenTK);
        String name= intent.getStringExtra("TenTK");
        tenTK.setText(name);
    }
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
}
