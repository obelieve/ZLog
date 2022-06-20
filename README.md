# ZLog

![image](https://thumbsnap.com/i/bfQkkmjg.gif)


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
    <com.obelieve.zlog.MarqueeTextSwitcher
        android:id="@+id/mts_content"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
```