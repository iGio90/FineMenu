package com.igio90.finemenuexample;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class DummyPagerAdapter extends FragmentPagerAdapter {
    DummyPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return DummyFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 3;
    }

    public static class DummyFragment extends Fragment {
        static DummyFragment newInstance(int pos) {
            Bundle args = new Bundle();
            args.putInt("pos", pos);
            DummyFragment dummyFragment = new DummyFragment();
            dummyFragment.setArguments(args);
            return dummyFragment;
        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
            return new TextView(parent.getContext());
        }

        @Override
        public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
            int pos = getArguments().getInt("pos");
            TextView textView = (TextView) view;
            ViewGroup.LayoutParams params = textView.getLayoutParams();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;

            textView.setGravity(Gravity.CENTER);
            textView.setText(String.valueOf(pos));
            textView.setLayoutParams(params);
        }
    }
}
