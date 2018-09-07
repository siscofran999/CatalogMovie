package com.example.tsmpc47.catalogmovie.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.tsmpc47.catalogmovie.R;
import com.example.tsmpc47.catalogmovie.databinding.ActivityHomeBinding;
import com.example.tsmpc47.catalogmovie.ui.base.BaseActivity;
import com.example.tsmpc47.catalogmovie.BR;
import com.example.tsmpc47.catalogmovie.ui.home.nowplaying.NowPlayingFragment;
import com.example.tsmpc47.catalogmovie.ui.home.upcoming.UpComingFragment;
import com.example.tsmpc47.catalogmovie.ui.search.SearchActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel> implements HasSupportFragmentInjector {

    @Inject
    HomeViewModel mHomeViewModel;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    ActivityHomeBinding mActivityHomeBinding;
    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityHomeBinding = getViewDataBinding();
        setBottomFragment(NowPlayingFragment.newInstance());
        bottomNavigationView();
    }

    private void setBottomFragment(NowPlayingFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_controller,fragment).commit();
    }

    private void setBottomFragment(UpComingFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_controller,fragment).commit();
    }

    public void bottomNavigationView() {
        mActivityHomeBinding.btmNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_now :
                        setBottomFragment(NowPlayingFragment.newInstance());
                        break;
                    case R.id.action_upcoming :
                        setBottomFragment(UpComingFragment.newInstance());
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu1){
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }else if(item.getItemId() == R.id.search){
            startActivity(SearchActivity.gotoSearchActivity(this));
        }
        return super.onOptionsItemSelected(item);
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

    @Override
    public AndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
