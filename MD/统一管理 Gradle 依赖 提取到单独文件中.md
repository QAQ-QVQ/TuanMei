# 先从架构开始讲起

1. ## 为什么要把项目依赖抽取单独管理

   如果项目比较小，架构简单只有一个 `build.gradle` 文件那完全不必将依赖抽离出来，因为整个系统构建好还是要费一些精力的。

   但是如果按照著名的 `Android-CleanArchitecture` ([github.com/android10/A…](https://github.com/android10/Android-CleanArchitecture)) 介绍的架构对项目进行分层，整个项目会有好几个 `module` 。

   ![项目分层](https://user-gold-cdn.xitu.io/2018/11/3/166d7deec392e066?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)

   基于这一思路，分成以下几个模块

   ![整体结构](/Users/qaq/AndroidStudioProjects/TuanMei/MD/整体结构.png)

   **项目一共分为四层** 

   `common`: 整个依赖的最底层，抽取出其他层共有的代码。比如 RxJava的封装，工具类的封装等。

   `data`: 数据层，网络数据、数据库中数据处理层。 

   `domin`: 这层应该是纯 Java 代码，从 `data` 层出来的数据要经过 `domin` 转换一下，UI层 `app` 引用的实体都是 `domin` 层的。用这种方法来隔离网络和数据库数据变化对代码的影响。 

   `app`: 应该叫 `presentation` 表现层，觉得这名字太长还是 `app` 看着舒服~ 这层就是正常的 Android 代码。

   **重回刚才的问题，为什么要抽取依赖统一管理？**

   有四个 `module` 就有四个 `build.gradle`，并且依赖很多都是重复的，分开写在四个不同的文件不利于后期维护升级。 想想如果要更改某个依赖的版本，得打开四个文件挨个改，多麻烦。 所以将可变的依赖抽离出来统一管理是很有必要的。

2. ## 具体操作

   1. #### 创建管理依赖的文件

      在与 `app` , `data` 同级的根目录下创建了文件夹 `buildsystem`，然后在这个文件夹下创建文件 `dependences.gradle`。目录结构如下图：

      ![项目结构](/Users/qaq/AndroidStudioProjects/TuanMei/MD/结构.png)
      
   2. #### 声明创建的文件

      在项目的 `build.gradle` 第一行加上这行代码: `apply from: "buildsystem/dependences.gradle"`
      
   3. #### 在具体的位置使用
      
      引用根节点的数据结构
      
      ```
      def appDependence = rootProject.ext.app
      ```

   


