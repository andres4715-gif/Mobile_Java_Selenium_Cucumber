# 🏗️ Selenium, Java, Cucumber, Appium

# TOOLS:
- Android Studio
- Appium server
- IntelliJ IDEA 2024.3.4 (Community Edition)

# Android Emulator data:
```properties
platform.name=Android
automation.name=UiAutomator2
device.name=Pixel 8 API 35
device.udid=emulator-5554
platform.version=35
```

## Get the app.activity for APK Android Apps
```shell
$ adb shell dumpsys package org.fdroid.fdroid | findstr "Activity"
```

# 🛠️⚙️ TECHNOLOGIES:
- Java SDK: 21
- Selenium: 4.29.0
- Appium: 9.4.0
- Cucumber: 7.14.0
- Lombok: 1.18.30
- Log4j2: 2.20.0
- maven-compiler-plugin: 3.11.0
- maven-surefire-plugin: 3.1.2

# 🔎 TO DO...🛠️⚙️🔨
- [ ] Verify if log feature is working fine
- [ ] Make a new scenario with complete steps
- [ ] Check the BrowserStack execution

