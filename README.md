# Selenium, Java, Cucumber, Appium

## Get the app.activity for APK Android Apps
```shell
$ adb shell dumpsys package org.fdroid.fdroid | findstr "Activity"
```