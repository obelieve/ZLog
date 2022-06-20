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
                LogUtil.v(new Random().nextInt(100000)+"");
                LogUtil.d(new Random().nextInt(100000)+"");
                LogUtil.i(new Random().nextInt(100000)+"");
                LogUtil.w(new Random().nextInt(100000)+"");
                LogUtil.e(new Random().nextInt(100000)+"");
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



