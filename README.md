# ItemHelper

[![ItemHelper](https://img.shields.io/badge/ItemHelper-1.1.0_SNAPSHOT-blue.svg)]()
<br><br>
[![Java](https://img.shields.io/badge/Java-16-FF7700.svg?logo=java)]()
[![Kotlin](https://img.shields.io/badge/Kotlin-1.5.31-186FCC.svg?logo=kotlin)]()
[![PaperMC](https://img.shields.io/badge/PaperMC-1.17-222222.svg)]()


<br>
<br>

##### Use API


## Maven
```xml
<repositories>
    <repository>
        <id>project-central</id>
        <url>https://repo.projecttl.net/repository/maven-public/</url>
    </repository>
</repositories>

<dependency>
    <groupId>io.github.blugon09</groupId>
    <artifactId>ItemHelper</artifactId>
    <version>VERSION</version>
</dependency>
```


## Gradle
```gradle
repositories {
    ...
    maven { 'https://repo.projecttl.net/repository/maven-public/' }
}

dependencies {
    implementation 'io.github.blugon09:ItemHelper:VERSION'
}
```

## Kotlin DSL
```gradle
repositories {
    ...
    maven("https://repo.projecttl.net/repository/maven-public/")
}

dependencies {
    implementation("io.github.blugon09:ItemHelper:VERSION")
}
```

<br><br>

Create ItemObject
```kotlin
val itemObject : ItemObject = ItemObject(Material, Amount, DisplayName, Lore)

//Build to ItemStack
val itemStack : ItemStack = itemObject.build()
```

<br>

Change ItemStack Info
```kotlin
val itemObject : ItemObject = player.inventory.itemInMainHand.asItemObject()
itemObject.displayName = "String.component()로 String을 TextComponent로 간단하게 변경".component()
itemObject.addLore("Lore!".component())
itemObject.lore = arrayListOf("Lore2!".component(), "Lore3!".component())
```
<br>

**해당 API는 현재 개발중인 SNAPSHOT버전이며 언제든지 생성 방식이 바뀔수 있습니다(근데 웬만하면 안바뀔듯)**
