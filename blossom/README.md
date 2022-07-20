# Blossom
A cute, modern configuration library for specific mods.

# Dependencies

In your repository block, add:

**Groovy**
```groovy
maven {
    url = "https://maven.shuuyu.live/release"
}
```

```kotlin
maven("https://maven.shuuyu.live/release")
```

<details><summary>Fabric</summary>

```groovy
modImplementation(include("live.shuuyu:blossom-$mcVersion-fabric:$buildNumber"))
```

```kotlin
modImplementation(include("live.shuuyu:blossom-$mcVersion-fabric:$buildNumber")!!)
```
</details>

<details><summary>Forge</summary>

```kotlin   
implementation("live.shuuyu:blossom-$mcVersion-fabric:$buildNumber")
```
</details>

<details><summary>Quilt</summary>
```groovy
modImplementation(include("live.shuuyu:blossom-$mcVersion-fabric:$buildNumber"))
```

```kotlin
modImplementation(include("live.shuuyu:blossom-$mcVersion-fabric:$buildNumber")!!)
```
</details>