package com.drz.user;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.launcher.ARouter;
import com.drz.user.databinding.ActivityRegistryBinding;

public class RegistryActivity extends AppCompatActivity {


    private ActivityRegistryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registry);
//        initView();
//        initData();
    }

}
