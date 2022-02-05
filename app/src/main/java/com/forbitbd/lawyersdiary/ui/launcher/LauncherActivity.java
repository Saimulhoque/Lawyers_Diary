package com.forbitbd.lawyersdiary.ui.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.ui.login.LoginActivity;
import com.forbitbd.lawyersdiary.ui.main.MainActivity;

public class LauncherActivity extends AppCompatActivity implements LauncherContract.View {

    Handler handler = new Handler();
    Runnable runnable;
    int delay = 1000;
    private LauncherPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        mPresenter = new LauncherPresenter(this);

    }

    @Override
    protected void onResume() {
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(runnable,delay);
                mPresenter.checkdealer();
            }
        },delay);
        super.onResume();
    }

    @Override
    protected void onPause() {
        handler.removeCallbacks(runnable);
        super.onPause();
    }

    @Override
    public void Startlogin() {
        startActivity(new Intent(this,LoginActivity.class));
    }

    @Override
    public void StartMain() {
        startActivity(new Intent(this, MainActivity.class));
    }
}