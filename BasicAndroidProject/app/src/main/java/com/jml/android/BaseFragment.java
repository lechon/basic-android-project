package com.jml.android;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by jose on 28/02/15.
 *
 * @version 0.1.0
 * @since 1
 */
public abstract class BaseFragment extends Fragment {


    public static final String TAG = BaseFragment.class.getSimpleName();

    protected ProgressDialog progressDialog;
    private int countDialogs = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(Boolean.TRUE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutResource(), container, false);

        ButterKnife.inject(this, view);

        return view;
    }

    protected abstract int getLayoutResource();


    public void showDialog(String text) {
        countDialogs++;
        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog = ProgressDialog.show(getActivity(), "", text, true);
        }
    }

    public void dismissDialog() {
        countDialogs--;
        if (progressDialog != null && countDialogs <= 0) {
            progressDialog.dismiss();
            progressDialog = null;
            countDialogs = 0;
        }
    }


    protected void addFragment(Fragment fragment, String tagFragment) {

        if (getActivity() instanceof BaseActivity)
            ((BaseActivity) getActivity()).addFragmentToBackStack(fragment, tagFragment);
        else
            throw new RuntimeException(" addFragment  method cannot be used with an Activity that not extends BaseActivity");
    }

}
