# InternalLib

This library is not finished yet, I'm working very hard to add the most features that I can imagine, if you want me to add a new feature, please add a Issue or contact me on Discord (xGabriel#0007).

# Features

This library currently has the following features:

- [X] Config
- [X] MySQL
- [X] Titles and Actionbars
- [X] Item and Skulls builders.
- [X] Bossbar API
- [X] Bungee API
- [X] A in-game refresh plugin command
- [X] New features are coming soon...

# Developer Portal

To use it in your projects, first add the project as a dependency in your ``plugin.yml``, it should look like this:

```yaml
name: ProjectName
main: me.gabriel.project.com.Name
version: 1.0
depend: [InternalLib] 
```

Now, if you're not using Maven or Gradle, all you need to do
is download the jar of the project and put it as a library in your plugin. Else, check the tutorials below:

<h2>Maven</h2>

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

<h2>Gradle</h2>

Inside your build.gradle, put that in:

```groovy
    repositories {
        maven { url 'https://jitpack.io' }
    }
    
    dependencies {
        implementation 'com.github.xGabrielDEV:InternalLib:Tag'
    }
```

If you need any help, go check the repository's [documentation](https://github.com/xGabrielDEV/InternalLib/wiki/)!

That's all currently. <br>
I hope so much you enjoy it, and feel free to leave an pull request or post an issue.
