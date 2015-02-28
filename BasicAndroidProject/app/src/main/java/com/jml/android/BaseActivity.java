package com.jml.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.ButterKnife;

/**
 * Created by jose on 28/02/15.
 *
 * @version 0.1.0
 * @since 1
 */
public abstract class BaseActivity extends ActionBarActivity {

    protected final int LAYOUT_CONTAINER = R.layout.activity_container;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResource());
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        ButterKnife.inject(this);
    }

    protected void setFragmentIntoDefaultContainer(Bundle savedInstanceState, Fragment fragment) {

        if (savedInstanceState == null && getLayoutResource() == LAYOUT_CONTAINER) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.base_container, fragment)
                    .commit();
        }
    }



    protected abstract int getLayoutResource();

    protected void setHomeUpEnabled() {

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void addFragmentToBackStack(Fragment fragment, String tagFragment){

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                        android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.base_container, fragment)
                .addToBackStack(tagFragment)
                .commit();
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            return;
        }

        if(getSupportFragmentManager().getBackStackEntryCount() == 0){
            getSupportActionBar().setDisplayHomeAsUpEnabled(Boolean.FALSE);
        }

        super.onBackPressed();
    }

}
