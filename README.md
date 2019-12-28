## FineMenu

A simple, clean and styleable menù/tabs for Android

--------

I recently had to build some Applications designed by different UI designers with some common things which I converted into this library.

![](https://media.giphy.com/media/ZEGqgH933XQvltJUHg/giphy.gif)

### Setup

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

```
dependencies {
        implementation 'com.github.iGio90:FineMenu:+'
}
```

### Sample code

```java
FrameLayout menuContainer = findViewById(R.id.bottom_menu_container_2);
int buttonsBgColor = Color.parseColor("#2a2a2a");
new FineMenu()
        .withViewPager(mPager)
        .withButtons(
                .withButtons(
                        new FineMenu.FineMenuButton()
                                .withLabel("label 1")
                                .withLabelColor(Color.WHITE)
                                .withLabelSize(12)
                                .withSelectedLabelSize(14)
                                .withSVGIcon("icon.svg")
                                .withTypeface(Typeface.DEFAULT)
                                .withIconSize(24, 24)
                                .withBackgroundColor(buttonsBgColor),
                        new FineMenu.FineMenuButton()
                                .withLabel("label 2")
                                .withLabelColor(Color.WHITE)
                                .withLabelSize(12)
                                .withSelectedLabelSize(14)
                                .withSVGIcon("icon.svg")
                                .withTypeface(Typeface.DEFAULT)
                                .withIconSize(24, 24)
                                .withBackgroundColor(buttonsBgColor),
                        new FineMenu.FineMenuButton()
                                .withLabel("label 3")
                                .withLabelColor(Color.WHITE)
                                .withLabelSize(12)
                                .withSelectedLabelSize(14)
                                .withSVGIcon("icon.svg")
                                .withTypeface(Typeface.DEFAULT)
                                .withIconSize(24, 24)
                                .withBackgroundColor(buttonsBgColor)
                )
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
```
----

Please open an issue to request specific usage case features

----


Checkout [here](https://github.com/iGio90/FineMenu/blob/master/finemenuexample/src/main/java/com/igio90/finemenuexample/MainActivity.java) the complete example from the screenshots above.
