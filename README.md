# custom-jmeter-functions

#### 介绍
    最近在做性能测试，想着扩展下函数助手（自定义函数），方便日常使用，以后不定期持续开发，欢迎朋友一起帮想想还需要哪些参数，作者空了可以更新上。

* 开发环境：
Java JDK 1.8以上
Jmeter（version 5.4.1） 

* 项目目录层级
![输入图片说明](https://foruda.gitee.com/images/1668887398525593483/5df30101_5573483.png "屏幕截图")

#### 使用说明
1、将lib下jar包添加进项目（Project Structure  ->  Libraries  -> +Java）
![输入图片说明](https://foruda.gitee.com/images/1668886470234687656/f6ebe1fa_5573483.png "屏幕截图")
2、编译jar包，放置
使用Build Artifacts编译打包 （无主函数入口，也未使用mavan、gradle等构建工具）
![Build Artifacts](https://foruda.gitee.com/images/1668887444072456034/18a44b81_5573483.png "屏幕截图")
![输入图片说明](https://foruda.gitee.com/images/1668887498672339324/45e5f349_5573483.png "屏幕截图")
![输入图片说明](https://foruda.gitee.com/images/1668887515702426173/c6eabe0e_5573483.png "屏幕截图")
![输入图片说明](https://foruda.gitee.com/images/1668887610503507232/771f5909_5573483.png "屏幕截图")
3、jar包放入Jmeter安装目录下lib/ext 下即可使用
![输入图片说明](https://foruda.gitee.com/images/1668887693650559077/f0b1f612_5573483.png "屏幕截图")
4、Jmeter使用函数助手
![输入图片说明](https://foruda.gitee.com/images/1668887775618855880/5f9c626a_5573483.png "屏幕截图")
5、类似的，很多自定义的函数都可同样的模式开发扩展。
#### 其他

1. 本人博客地址 [https://blog.csdn.net](https://blog.csdn.net/weixin_43104848?spm=1000.2115.3001.5343)
2. 特别鸣谢相关作者及文章
    [测试数据生成](https://github.com/binarywang/java-testdata-generator) 
    [CSDN-JMeter之函数二次开发/插件开发](https://blog.csdn.net/u011072936/article/details/125946439)
