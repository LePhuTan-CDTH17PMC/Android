package com.example.da_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Dialog dialog;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playSound(R.raw.vexento);

    }
    //Set sự kiện cho nút back
    @Override
    public void onBackPressed() {
        showDialog();
    }


    /*btnLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });*/


    //Set sự kiện đăng kí cho button đăng kí
    public void dangki(View view){
        Intent intent= new Intent(MainActivity.this,Dang_ki.class);
        startActivity(intent);
    }
    //Set sự kiện đăng nhập cho button đăng nhập
    public void Dangnhap(View view){
        EditText txt= findViewById(R.id.edtTendangnhap);
        String tenTK= txt.getText().toString();
        Intent intent= new Intent(MainActivity.this,Man_hinh_chinh.class);
        intent.putExtra("TenTK",tenTK);
        startActivity(intent);
    }
    //Set sự kiện đăng quên mật khẩu cho button quên mật khẩu
    public void QuenMatKhau(View view){
        Intent intent= new Intent(MainActivity.this,Quen_mat_khau.class);
        startActivity(intent);
    }
    //Hàm phát nhạc cho game
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
    //Hiển thị hộp thoại đăng xuất
    public void showDialog(){
        AlertDialog.Builder dialog =new AlertDialog.Builder(this);
        dialog.setTitle("Bạn có muốn đăng xuất không?");
        dialog.setCancelable(false);
        dialog.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(MainActivity.this,"Không đăng xuất",Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog= dialog.create();
        alertDialog.show();
    }
}
