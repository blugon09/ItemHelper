# PluginApiTemplate

[![Kotlin](https://img.shields.io/badge/Java-16.0.2-FF7700.svg?logo=java)]()
[![Kotlin](https://img.shields.io/badge/Kotlin-1.5.30-186FCC.svg?logo=kotlin)]()
[![PaperMC](https://img.shields.io/badge/PaperMC-1.17-222222.svg)]()


<br>
<br>
Use API

<br>

## Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.blugon09</groupId>
    <artifactId>ItemHelper</artifactId>
    <version>VERSION</version>
</dependency>
```


## Gradle
```gradle
repositories {
    ...
    maven { 'https://jitpack.io/' }
}

dependencies {
    implementation 'com.github.blugon09:ItemHelper:VERSION'
}
```

## Kotlin DSL
```gradle
repositories {
    ...
    maven("https://jitpack.io/")
}

dependencies {
    implementation("com.github.blugon09:ItemHelper:VERSION")
}
```