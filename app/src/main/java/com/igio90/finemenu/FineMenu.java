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
package com.igio90.finemenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.graphics.drawable.DrawableCompat;
import androidx.viewpager.widget.ViewPager;

import com.caverock.androidsvg.SVGImageView;

public class FineMenu {
    private FineMenuButton[] mButtons;

    private ViewPager mPager;
    private OnMenuButtonClickListener mOnMenuClickListener;

    private boolean mSelectedBold = true;
    private boolean mWrapWithScrollView = false;
    private float mUnselectedAlpha = .3f;

    public FineMenu withButtons(FineMenuButton... buttons) {
        mButtons = buttons;
        return this;
    }

    public FineMenu withViewPager(ViewPager pager) {
        mPager = pager;
        return this;
    }

    public FineMenu withOnMenuClickListener(OnMenuButtonClickListener onMenuClickListener) {
        mOnMenuClickListener = onMenuClickListener;
        return this;
    }

    public FineMenu withSelectedBold(boolean selectedBold) {
        mSelectedBold = selectedBold;
        return this;
    }

    public FineMenu withWrapWithScrollView(boolean wrapWithScrollView) {
        mWrapWithScrollView = wrapWithScrollView;
        return this;
    }

    public FineMenu withUnselectedAlpha(float alpha) {
        if (alpha > 1f || alpha < 0f) {
            alpha = 1f;
        }

        mUnselectedAlpha = alpha;

        return this;
    }

    public void injectTo(ViewGroup parent) {
        final Context context = parent.getContext();
        final LayoutInflater inflater = LayoutInflater.from(context);

        LinearLayout menuContainer;
        final View root;
        if (mWrapWithScrollView) {
            root = inflater.inflate(R.layout.fine_menu_scroll_container, parent, false);
            menuContainer = root.findViewById(R.id.fine_menu_button_container);
        } else {
            menuContainer = (LinearLayout)
                    inflater.inflate(R.layout.fine_menu_buttons_container, parent, false);
            root = menuContainer;
        }

        if (mButtons != null) {
            for (int i = 0; i < mButtons.length; i++) {
                FineMenuButton fineMenuButton = mButtons[i];

                FrameLayout button = (FrameLayout) inflater.inflate(R.layout.fine_menu_button, menuContainer, false);
                View buttonContainer = button.findViewById(R.id.fine_menu_button_container);
                FrameLayout iconContainer = button.findViewById(R.id.fine_menu_button_icon_container);

                fineMenuButton.mButtonContainer = buttonContainer;
                fineMenuButton.mPosition = i;

                if (fineMenuButton.mLabel != null) {
                    TextView label = button.findViewById(R.id.fine_menu_button_label);
                    label.setVisibility(View.VISIBLE);
                    label.setText(fineMenuButton.mLabel);

                    if (fineMenuButton.mLabelColor != null) {
                        label.setTextColor(fineMenuButton.mLabelColor);
                    }

                    if (fineMenuButton.mTypeface != null) {
                        label.setTypeface(fineMenuButton.mTypeface);
                    }

                    label.setTextSize(TypedValue.COMPLEX_UNIT_SP, fineMenuButton.mLabelSize);

                    fineMenuButton.mButtonLabel = label;
                }

                if (fineMenuButton.mBackgroundColor != null) {
                    buttonContainer.setBackgroundColor(fineMenuButton.mBackgroundColor);
                }

                if (fineMenuButton.mPadding >= 0) {
                    int padding = (int) dpToPx(fineMenuButton.mPadding, context);
                    buttonContainer.setPadding(padding, padding, padding, padding);
                }

                ImageView icon = null;
                if (fineMenuButton.mIconResource != null) {
                    icon = new ImageView(context);
                    icon.setImageResource(fineMenuButton.mIconResource);
                } else if (fineMenuButton.mIconDrawable != null) {
                    icon = new ImageView(context);
                    icon.setImageDrawable(fineMenuButton.mIconDrawable);
                } else if (fineMenuButton.mIconSVG != null) {
                    icon = new SVGImageView(context);
                    ((SVGImageView) icon).setImageAsset(fineMenuButton.mIconSVG);
                }
                if (icon != null) {
                    FrameLayout.LayoutParams iconParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    if (fineMenuButton.mIconSize != null) {
                        iconParams.width = (int) dpToPx(fineMenuButton.mIconSize.first, context);
                        iconParams.height = (int) dpToPx(fineMenuButton.mIconSize.second, context);
                    }
                    iconParams.gravity = Gravity.CENTER;
                    int margin = (int) dpToPx(8, context);
                    iconParams.setMargins(margin, margin, margin, margin);
                    iconContainer.addView(icon, iconParams);
                    fineMenuButton.mButtonIcon = icon;
                }

                if (fineMenuButton.mIndicatorColor != null) {
                    ImageView indicator = button.findViewById(R.id.fine_menu_button_indicator);
                    indicator.setImageDrawable(loadIndicatorDrawableColored(
                            context, fineMenuButton.mIndicatorColor));
                    fineMenuButton.mIndicator = indicator;
                }

                button.setTag(fineMenuButton);
                button.setOnClickListener(mOnClickListener);

                LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
                buttonParams.weight = 1;
                menuContainer.addView(button, buttonParams);
            }
        }

        if (mPager != null) {
            mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
                @Override
                public void onPageSelected(int position) {
                    setButtonSelected(position);
                    if (root instanceof HorizontalScrollView) {
                        ViewGroup container = (ViewGroup) ((HorizontalScrollView) root)
                                .getChildAt(0);
                        ((HorizontalScrollView) root).smoothScrollTo(
                                container.getChildAt(position).getLeft(), 0);
                    }
                }
                @Override
                public void onPageScrollStateChanged(int state) {}
            });
        }

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        parent.addView(root, params);

        setButtonSelected(0);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private Drawable loadIndicatorDrawableColored(Context context, int color) {
        Drawable indicatorDrawable;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            indicatorDrawable = context.getResources().getDrawable(
                    R.drawable.finemenu_indicator, context.getTheme());
        } else {
            indicatorDrawable = context.getResources().getDrawable(
                    R.drawable.finemenu_indicator);
        }
        Drawable wrapped = indicatorDrawable.mutate();
        wrapped = DrawableCompat.wrap(wrapped);
        DrawableCompat.setTint(wrapped, color);
        DrawableCompat.setTintMode(wrapped, PorterDuff.Mode.SRC_IN);
        return wrapped;
    }

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FineMenuButton fineMenuButton = (FineMenuButton) v.getTag();

            if (mPager != null) {
                mPager.setCurrentItem(fineMenuButton.mPosition);
            } else {
                setButtonSelected(fineMenuButton.mPosition);
            }

            if (mOnMenuClickListener != null) {
                mOnMenuClickListener.onMenuButtonClickListener(fineMenuButton.mPosition);
            }
        }
    };

    private void setButtonSelected(int position) {
        if (mButtons != null) {
            for (FineMenuButton button : mButtons) {
                if (button.mPosition.equals(position)) {
                    if (button.mSelectedBackgroundColor != null) {
                        button.mButtonContainer.setBackgroundColor(button.mSelectedBackgroundColor);
                    }
                    if (button.mButtonLabel != null) {
                        if (mSelectedBold) {
                            button.mButtonLabel.setTypeface(null, Typeface.BOLD);
                        }

                        button.mButtonLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, button.mSelectedLabelSize);
                        button.mButtonLabel.setAlpha(1f);
                    }
                    if (button.mIndicator != null) {
                        button.mIndicator.setVisibility(View.VISIBLE);
                    }
                    if (button.mButtonIcon != null) {
                        button.mButtonIcon.setAlpha(1f);
                    }
                } else {
                    if (button.mBackgroundColor != null) {
                        button.mButtonContainer.setBackgroundColor(button.mBackgroundColor);
                    }
                    if (button.mButtonLabel != null) {
                        if (mSelectedBold) {
                            button.mButtonLabel.setTypeface(null, Typeface.NORMAL);
                        }

                        button.mButtonLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, button.mLabelSize);
                        button.mButtonLabel.setAlpha(mUnselectedAlpha);
                    }
                    if (button.mIndicator != null) {
                        button.mIndicator.setVisibility(View.INVISIBLE);
                    }
                    if (button.mButtonIcon != null) {
                        button.mButtonIcon.setAlpha(mUnselectedAlpha);
                    }
                }
            }
        }
    }

    private float dpToPx(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static class FineMenuButton {
        private Integer mPosition = null;
        private View mButtonContainer = null;
        private TextView mButtonLabel = null;
        private ImageView mButtonIcon = null;
        private ImageView mIndicator = null;

        private String mLabel = null;

        private Integer mIconResource = null;
        private Drawable mIconDrawable = null;
        private String mIconSVG;

        private Integer mLabelColor = null;
        private Integer mBackgroundColor = null;
        private Integer mSelectedBackgroundColor = null;
        private Integer mIndicatorColor = null;

        private Pair<Integer, Integer> mIconSize = null;
        private Integer mLabelSize = 14;
        private Integer mSelectedLabelSize = 14;

        private Integer mPadding = -1;

        private Typeface mTypeface;

        public FineMenuButton withLabel(String label) {
            mLabel = label;
            return this;
        }

        public FineMenuButton withIconResource(int iconResId) {
            mIconResource = iconResId;
            return this;
        }

        public FineMenuButton withIconDrawable(Drawable iconDrawable) {
            mIconDrawable = iconDrawable;
            return this;
        }

        public FineMenuButton withSVGIcon(String svgIcon) {
            mIconSVG = svgIcon;
            return this;
        }

        public FineMenuButton withLabelColor(int color) {
            mLabelColor = color;
            return this;
        }

        public FineMenuButton withBackgroundColor(int color) {
            mBackgroundColor = color;
            return this;
        }

        public FineMenuButton withSelectedBackgroundColor(int color) {
            mSelectedBackgroundColor = color;
            return this;
        }

        public FineMenuButton withIndicatorColor(int color) {
            mIndicatorColor = color;
            return this;
        }

        public FineMenuButton withIconSize(int widthDp, int heightDp) {
            mIconSize = new Pair<>(widthDp, heightDp);
            return this;
        }

        public FineMenuButton withLabelSize(int sizeSp) {
            mLabelSize = sizeSp;
            return this;
        }

        public FineMenuButton withSelectedLabelSize(int selectedLabelSizeSp) {
            mSelectedLabelSize = selectedLabelSizeSp;
            return this;
        }

        public FineMenuButton withTypeface(Typeface typeface) {
            mTypeface = typeface;
            return this;
        }

        public FineMenuButton withPaddingDp(int paddingDp) {
            mPadding = paddingDp;
            return this;
        }
    }

    public interface OnMenuButtonClickListener {
        void onMenuButtonClickListener(int position);
    }
}
