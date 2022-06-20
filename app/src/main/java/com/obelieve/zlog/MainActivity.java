package com.obelieve.zlog;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.obelieve.zlog.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends Activity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleLogWindowManager.getInstance().register(this);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        binding.btnAddLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZLog.v(new Random().nextInt(100000)+"");
                ZLog.d(new Random().nextInt(100000)+"");
                ZLog.i(new Random().nextInt(100000)+"");
                ZLog.w(new Random().nextInt(100000)+"");
                ZLog.e(new Random().nextInt(100000)+"");
            }
        });
        binding.btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleLogWindowManager.getInstance().switchLogShowOrHide();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SimpleLogWindowManager.getInstance().unregister(this);
    }
}



