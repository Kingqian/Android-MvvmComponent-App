package com.drz.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.drz.common.router.RouterActivityPath;
import com.drz.common.services.ILoginService;
import com.drz.common.services.config.ServicesConfig;
import com.drz.user.databinding.UserActivityLoginBinding;

import static com.zhouyou.http.EasyHttp.getContext;

/**
 * @author darryrzhoong
 */
@Route(path = RouterActivityPath.User.PAGER_LOGIN)
public class ChooseActivity extends AppCompatActivity {

    private UserActivityLoginBinding binding;
    private AnimatorSet animatorSet;

    @Autowired(name = ServicesConfig.User.LONGING_SERVICE)
    ILoginService iLoginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        binding = DataBindingUtil.setContentView(this,R.layout.user_activity_login);
        initView();
        initData();
    }

    private void initData() {
        //模拟登录
        iLoginService.saveStatus(false);
    }
    private void initView() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(binding.loginBgImage1, "alpha", 1.0f, 0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(binding.loginBgImage2, "alpha", 0f, 1.0f);
        ObjectAnimator animatorScale1 = ObjectAnimator.ofFloat(binding.loginBgImage1, "scaleX", 1.0f, 1.3f);
        ObjectAnimator animatorScale2 = ObjectAnimator.ofFloat(binding.loginBgImage1, "scaleY", 1.0f, 1.3f);
       AnimatorSet  animatorSet1 = new AnimatorSet();
        animatorSet1.setDuration(5000);
        animatorSet1.play(animator1).with(animator2).with(animatorScale1).with(animatorScale2);
        animatorSet1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // 放大的View复位
                binding.loginBgImage1.setScaleX(1.0f);
                binding.loginBgImage1.setScaleY(1.0f);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(binding.loginBgImage2, "alpha", 1.0f, 0f);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(binding.loginBgImage1, "alpha", 0f, 1.0f);
        ObjectAnimator animatorScale3 = ObjectAnimator.ofFloat(binding.loginBgImage2, "scaleX", 1.0f, 1.3f);
        ObjectAnimator animatorScale4 = ObjectAnimator.ofFloat(binding.loginBgImage2, "scaleY", 1.0f, 1.3f);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.setDuration(5000);
        animatorSet2.play(animator3).with(animator4).with(animatorScale3).with(animatorScale4);
        animatorSet2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // 放大的View复位
                binding.loginBgImage2.setScaleX(1.0f);
                binding.loginBgImage2.setScaleY(1.0f);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animatorSet1, animatorSet2);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // 循环播放
                animation.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
       animatorSet.start();

        binding.btLogin.setOnClickListener(vv -> { startActivity(new Intent(getContext(),LoginActivity.class));});
        binding.btRegist.setOnClickListener(v -> { startActivity(new Intent(getContext(),RegistryActivity.class)); });


    }



    @Override
    protected void onPause() {
        super.onPause();
        animatorSet.cancel();
    }
}
