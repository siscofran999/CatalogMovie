package com.example.tsmpc47.catalogmovie.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tsmpc47.catalogmovie.R;
import com.example.tsmpc47.catalogmovie.databinding.ActivityHomeBinding;
import com.example.tsmpc47.catalogmovie.ui.base.BaseActivity;
import com.example.tsmpc47.catalogmovie.BR;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel> implements HomeNavigator {

    @Inject
    HomeViewModel mHomeViewModel;

    ActivityHomeBinding mActivityHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityHomeBinding = getViewDataBinding();
        bottomNavigationView();
    }

    private void bottomNavigationView() {
        mActivityHomeBinding.btmNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_now :
                        Toast.makeText(HomeActivity.this, "now", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_upcoming :
                        Toast.makeText(HomeActivity.this, "upcoming", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public HomeViewModel getViewModel() {
        return mHomeViewModel;
    }
}
