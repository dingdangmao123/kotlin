# Kotlin
### 仅仅是一些学习Kotlin的简单例子

- DSL.kt 主要是利用Kotlin的语法糖使m代码的可读性更贴近自然语言，为内部DSL,得益于其特性，当然也受制于其语法特性,这里主要利用匿名函数，且有类名前缀，因此可以访问其属性达到配置效果

```kotlin
  App {
        url="http://home.firefoxchina.cn/"

        type="url"
    }
```



