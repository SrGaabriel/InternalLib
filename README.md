# InternalLib

This library is not finished yet, I'm working very hard to add the most features that I can imagine, if you want me to add a new feature, please add a Issue or contact me on Discord (xGabriel#0007).

# Features

This library currently has the following features:

* Config
* MySQL
* Titles and Actionbars
* Item and Skulls builders.
* Bossbar API
* Bungee API
* A in-game refresh plugin command
* New features are coming soon...

# Developer Portal

To use it in your projects that don't use Maven or Gradle, just download the .jar and put it as a library of your plugins, but, if you're using Maven and Gradle, look at the tutorials below

<h3>Maven</h3>

Inside your pom.xml, put that in:
```xml
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
    
    <dependency>
        <groupId>com.github.xGabrielDEV</groupId>
        <artifactId>InternalLib</artifactId>
        <version>Tag</version>
    </dependency>
```

<h3>Gradle</h3>

Inside your build.gradle, put that in:

```groovy
    repositories {
        maven { url 'https://jitpack.io' }
    }
    
    dependencies {
        implementation 'com.github.xGabrielDEV:InternalLib:Tag'
    }
```

That's all currently. <br>
I hope so much you enjoy it, and feel free to leave an pull request or post an issue.
