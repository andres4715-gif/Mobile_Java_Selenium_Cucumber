# 🏗️ Selenium, Java, Cucumber, Appium

📌 Project Overview
This project is a test automation framework using Selenium, Appium, and Cucumber for mobile testing on Android devices. 
It allows for UI testing of Android applications using Java and the Cucumber BDD approach

## 🛠️ Development Tools:
```properties
Android Studio - for managing the Android emulator.
Appium Server - for mobile automation.
IntelliJ IDEA 2024.3.4 (Community Edition) - for development.
```

🛠️ Framework Stack:
```md
- Java SDK: 21
- Selenium: 4.29.0
- Appium: 9.4.0
- Cucumber: 7.14.0
- Lombok: 1.18.30
- Log4j2: 2.20.0
- maven-compiler-plugin: 3.11.0
- maven-surefire-plugin: 3.1.2
```


📂 Project Structure
```md
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── tuempresa
│   │   │           ├── base
│   │   │           │   ├── BasePage.java
│   │   │           │   └── BaseTest.java
│   │   │           ├── config
│   │   │           │   ├── AppiumConfig.java
│   │   │           │   ├── BrowserStackConfig.java
│   │   │           │   └── ConfigReader.java
│   │   │           ├── pages
│   │   │           │   ├── LoginPage.java
│   │   │           │   └── HomePage.java
│   │   │           ├── locators
│   │   │           │   ├── LoginPageLocators.java
│   │   │           │   └── HomePageLocators.java
│   │   │           ├── utils
│   │   │           │   ├── AppiumDriverFactory.java
│   │   │           │   ├── WaitUtils.java
│   │   │           │   └── TestDataUtils.java
│   │   │           └── constants
│   │   │               ├── TimeoutConstants.java
│   │   │               └── AppConstants.java
│   │   └── resources
│   │       ├── config.properties
│   │       └── browserstack.properties
│   └── test
│       ├── java
│       │   └── com
│       │       └── tuempresa
│       │           ├── stepdefinitions
│       │           │   ├── Hooks.java
│       │           │   ├── LoginSteps.java
│       │           │   └── HomeSteps.java
│       │           └── runners
│       │               └── TestRunner.java
│       └── resources
│           ├── features
│           │   ├── login.feature
│           │   └── home.feature
│           └── apps
│               └── sampleApp.apk
└── pom.xml
```

# Android Emulator configuration:
```properties
platform.name=Android
automation.name=UiAutomator2
device.name=Pixel 8 API 35
device.udid=emulator-5554
platform.version=35
```

## 🔍 Finding App Activity & Package
```shell
$ adb shell dumpsys package org.fdroid.fdroid | findstr "Activity"
```
# 🚀 Running Tests
## Running Tests Locally:

- Start the Appium server:
```shell
$ appium
```

- Run tests using Maven:
```shell
$ mvn clean test
```
- Execute tests on specific feature files:

```shell
$ mvn test -Dcucumber.options="src/test/resources/features/login.feature"
```

- Running Tests on BrowserStack:
Set up BrowserStack credentials in browserstack.properties.

- Execute tests with:
```shell
$ mvn test -Dbrowserstack=true
```

# 🔎 TO DO...🛠
- [ ] Verify if log feature is working fine
- [ ] Make a new scenario with complete steps
- [ ] Check the BrowserStack execution
- [ ] Improve the readme file

---
🛠️⚙️🔨 Framework in construction 🛠️⚙️🔨