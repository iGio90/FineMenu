## FineMenu

A simple, clean and styleable menù/tabs for Android

--------

I recently had to build some Applications designed by different UI designers with some common things which I converted into this library.


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
        implementation 'com.github.iGio90:FineMenu:1.0'
}
```

```java
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
```

![screenshot1](https://i.ibb.co/T1cb3df/Screenshot-1575415753.png)
![screenshot2](https://i.ibb.co/B2bJjGv/Screenshot-1575415759.png)


Checkout [here](https://github.com/iGio90/FineMenu/blob/master/finemenuexample/src/main/java/com/igio90/finemenuexample/MainActivity.java) the complete example from the screenshots above.