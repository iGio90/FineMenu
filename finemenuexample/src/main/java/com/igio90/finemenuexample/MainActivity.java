/*
   Copyright 2019 | Giovanni - iGio90 - Rocca.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package com.igio90.finemenuexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
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
        addRainbowMenu();
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

    private void addRainbowMenu() {
        FrameLayout menuContainer = findViewById(R.id.rainbow_menu_container);
        new FineMenu()
                .withViewPager(mPager)
                .withButtons(
                        new FineMenu.FineMenuButton()
                                .withLabel("label 1")
                                .withSelectedLabelSize(18)
                                .withLabelColor(Color.RED)
                                .withIndicatorColor(Color.BLUE),
                        new FineMenu.FineMenuButton()
                                .withLabel("label 2")
                                .withSelectedLabelSize(16)
                                .withLabelColor(Color.BLUE)
                                .withIndicatorColor(Color.DKGRAY),
                        new FineMenu.FineMenuButton()
                                .withLabel("label 3")
                                .withSelectedLabelSize(20)
                                .withLabelColor(Color.DKGRAY)
                                .withIndicatorColor(Color.RED)
                )
                .withSelectedBold(true)
                .withUnselectedAlpha(1f)
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
                                .withSelectedBackgroundColor(buttonsSelectedColor),
                        new FineMenu.FineMenuButton()
                                .withLabel("label 3")
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
                                .withLabelSize(12)
                                .withSelectedLabelSize(14)
                                .withSVGIcon("icon.svg")
                                .withIconSize(24, 24)
                                .withBackgroundColor(buttonsBgColor),
                        new FineMenu.FineMenuButton()
                                .withLabel("label 2")
                                .withLabelColor(Color.WHITE)
                                .withLabelSize(12)
                                .withSelectedLabelSize(14)
                                .withSVGIcon("icon.svg")
                                .withIconSize(24, 24)
                                .withBackgroundColor(buttonsBgColor),
                        new FineMenu.FineMenuButton()
                                .withLabel("label 3")
                                .withLabelColor(Color.WHITE)
                                .withLabelSize(12)
                                .withSelectedLabelSize(14)
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
