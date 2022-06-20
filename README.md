# ZLog

<img src="./screenshots/screen.png" title="Logo" width="200"/>

### Step 1. Add the JitPack repository to your build file
```
...
allprojects {
    repositories {
        ...
        maven(){url 'https://jitpack.io'}
    }
}
...
```
### Step 2. Add the dependency
```
	dependencies {
	        implementation 'com.github.obelieve:ZLog:1.0.0'
	}
```

### Step 3. Use
```xml
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleLogWindowManager.getInstance().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SimpleLogWindowManager.getInstance().unregister(this);
    }

    //switch show/hide log windows
    SimpleLogWindowManager.getInstance().switchLogShowOrHide();
```