package lucien.albert.outerspacemanager.auth;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import lucien.albert.outerspacemanager.R;
import lucien.albert.outerspacemanager.auth.login.LoginFragment;
import lucien.albert.outerspacemanager.auth.signin.SignUpFragment;

/**
 * Created by lalbert on 26/03/2018.
 */

public class AuthPagerAdapter extends FragmentStatePagerAdapter {

    static int NUMBER_OF_PAGES = 2;

    private Context context;

    public AuthPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 : {
                return new LoginFragment();
            }
            case 1 : {
                return new SignUpFragment();
            }
            default: {
                return new LoginFragment();
            }
        }
    }

    @Override
    public int getCount() {
        return AuthPagerAdapter.NUMBER_OF_PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: {
                return this.context.getString(R.string.title_login);
            }
            case 1: {
                return this.context.getString(R.string.title_signin);
            }
            default: {
                return this.context.getString(R.string.title_login);
            }
        }
    }

}
