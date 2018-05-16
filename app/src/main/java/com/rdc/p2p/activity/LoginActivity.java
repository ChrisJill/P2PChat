package com.rdc.p2p.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.rdc.p2p.bean.UserBean;
import com.rdc.p2p.eventBean.IpDeviceEventBean;
import com.rdc.p2p.fragment.ProgressBarFragment;
import com.rdc.p2p.fragment.SelectImageFragment;
import com.rdc.p2p.R;
import com.rdc.p2p.base.BaseActivity;
import com.rdc.p2p.base.BasePresenter;
import com.rdc.p2p.bean.ImageBean;
import com.rdc.p2p.util.ImageUtil;
import com.rdc.p2p.util.ScanDeviceUtil;
import com.rdc.p2p.util.UserUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends BaseActivity {

    public static final String TAG = "Login";

    @BindView(R.id.civ_user_image_act_login)
    CircleImageView mCivUserImage;
    @BindView(R.id.et_nickname_act_login)
    EditText mEtNickname;
    @BindView(R.id.btn_login_act_login)
    Button mBtnLogin;

    private List<ImageBean> mImageList;
    private int mSelectedImageId;
    @Override
    public BasePresenter getInstance() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        mSelectedImageId = 0;
        mImageList = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            ImageBean imageBean = new ImageBean();
            imageBean.setImageId(i);
            mImageList.add(imageBean);
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        mCivUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectImageFragment selectImageFragment = new SelectImageFragment();
                selectImageFragment.show(getSupportFragmentManager(),"DialogFragment");
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(mEtNickname.getText())){
                    UserBean userBean = new UserBean();
                    userBean.setNickName(getString(mEtNickname));
                    userBean.setUserImageId(mSelectedImageId);
                    UserUtil.saveUser(userBean);
                    ProgressBarFragment progressBarFragment = new ProgressBarFragment();
                    progressBarFragment.setCancelable(false);
                    progressBarFragment.show(getSupportFragmentManager(),"progressFragment");
                }else {
                    showToast("昵称不能为空！");
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ScanDeviceFinished(IpDeviceEventBean deviceEventBean){
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }

    public void setImageId(int imageId){
        mSelectedImageId = imageId;
        Glide.with(this).load(ImageUtil.getImageResId(imageId)).into(mCivUserImage);
    }
}
