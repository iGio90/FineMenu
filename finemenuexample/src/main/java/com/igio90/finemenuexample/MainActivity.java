package com.igio90.finemenuexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.igio90.finemenu.FineMenu;

public class MainActivity extends AppCompatActivity {
    private ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = findViewById(R.id.pager);
        mPager.setAdapter(new DummyPagerAdapter(getSupportFragmentManager()));

        addTopMenu();
        addBottomMenu();
        addBottomMenuTwo();
    }

    private void addTopMenu() {
        FrameLayout menuContainer = findViewById(R.id.menu_container);
        new FineMenu()
                .withViewPager(mPager)
                .withButtons(
                        new FineMenu.FineMenuButton()
                                .withLabel("label 1")
                                .withLabelColor(Color.BLACK)
                                .withIndicatorColor(Color.BLACK),
                        new FineMenu.FineMenuButton()
                                .withLabel("label 2")
                                .withIndicatorColor(Color.BLACK)
                                .withLabelColor(Color.BLACK),
                        new FineMenu.FineMenuButton()
                                .withLabel("label 3")
                                .withIndicatorColor(Color.BLACK)
                                .withLabelColor(Color.BLACK)
                )
                .withSelectedBold(true)
                .withUnselectedAlpha(.3f)
                .injectTo(menuContainer);
    }

    private void addBottomMenu() {
        FrameLayout menuContainer = findViewById(R.id.bottom_menu_container);
        int buttonsBgColor = Color.parseColor("#0b1930");
        int buttonsSelectedColor = Color.parseColor("#00a4ff");
        new FineMenu()
                .withViewPager(mPager)
                .withButtons(
                        new FineMenu.FineMenuButton()
                                .withLabel("label 1")
                                .withLabelColor(Color.WHITE)
                                .withIconResource(R.mipmap.ic_launcher)
                                .withIconSize(24, 24)
                                .withBackgroundColor(buttonsBgColor)
                                .withSelectedBackgroundColor(buttonsSelectedColor),
                        new FineMenu.FineMenuButton()
                                .withLabel("label 2")
                                .withLabelColor(Color.WHITE)
                                .withIconResource(R.mipmap.ic_launcher)
                                .withIconSize(24, 24)
                                .withBackgroundColor(buttonsBgColor)
                                .withSelectedBackgroundColor(buttonsSelectedColor)
                )
                .withSelectedBold(false)
                .withUnselectedAlpha(1f)
                .injectTo(menuContainer);
    }

    private void addBottomMenuTwo() {
        FrameLayout menuContainer = findViewById(R.id.bottom_menu_container_2);
        int buttonsBgColor = Color.parseColor("#2a2a2a");
        new FineMenu()
                .withViewPager(mPager)
                .withButtons(
                        new FineMenu.FineMenuButton()
                                .withLabel("label 1")
                                .withLabelColor(Color.WHITE)
                                .withSVGIcon("icon.svg")
                                .withIconSize(24, 24)
                                .withBackgroundColor(buttonsBgColor),
                        new FineMenu.FineMenuButton()
                                .withLabel("label 2")
                                .withLabelColor(Color.WHITE)
                                .withSVGIcon("icon.svg")
                                .withIconSize(24, 24)
                                .withBackgroundColor(buttonsBgColor)
                )
                .withOnMenuClickListener(new FineMenu.OnMenuButtonClickListener() {
                    @Override
                    public void onMenuButtonClickListener(int position) {
                        Toast.makeText(MainActivity.this, "pos: " + position, Toast.LENGTH_SHORT).show();
                    }
                })
                .withSelectedBold(true)
                .withUnselectedAlpha(.3f)
                .injectTo(menuContainer);
    }
}
