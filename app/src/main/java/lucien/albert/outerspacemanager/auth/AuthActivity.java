package lucien.albert.outerspacemanager.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import lucien.albert.outerspacemanager.R;
import lucien.albert.outerspacemanager.auth.user.AuthModel;
import lucien.albert.outerspacemanager.auth.user.AuthUserModel;
import lucien.albert.outerspacemanager.auth.user.UserModel;
import lucien.albert.outerspacemanager.main.MainActivity;

public class AuthActivity extends FragmentActivity implements AuthViewInterface {

    private ViewPager authPager;
    private PagerAdapter authPagerAdapter;
    private TabLayout authTabs;
    private AuthPresenter authPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_auth);

        this.authPresenter = new AuthPresenter(this);
        if (this.authPresenter.checkUserHasToken(this.getApplicationContext())) {
            Intent mainIntent = new Intent(this.getApplicationContext(), MainActivity.class);
            this.startActivity(mainIntent);
        }

        this.authPager = this.findViewById(R.id.authPager);
        this.authTabs = this.findViewById(R.id.authTabs);
        this.authPagerAdapter = new AuthPagerAdapter(this.getApplicationContext(), this.getSupportFragmentManager());
        this.authPager.setAdapter(this.authPagerAdapter);
        this.authTabs.setupWithViewPager(this.authPager);
    }

    @Override
    public void onBackPressed()
    {
        if (this.authPager.getCurrentItem() == 0) {
            super.onBackPressed();
        }
        else {
            this.authPager.setCurrentItem(this.authPager.getCurrentItem() - 1);
        }
    }

}
