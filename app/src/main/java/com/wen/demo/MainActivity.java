package com.wen.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.wen.imagepicker.ImagePicker;
import com.wen.imagepicker.ui.ImageGridActivity;


/**
 *    gitHub   账号密码登录，报404，只能采取token方式登录了。
 *        token: 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
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        findViewById(R.id.tv_hd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,MainActivity2.class));
                ImagePicker.getInstance().setMultiMode(false);
                Intent intent = new Intent(getApplicationContext(), ImageGridActivity.class);
                startActivityForResult(intent, 12);
            }
        });

        findViewById(R.id.tv_my).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,GlActivity.class));
            }
        });
    }
}
