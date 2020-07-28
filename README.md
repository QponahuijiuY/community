### 🎓项目介绍
木同社区，前端使用Layui社区模板，后端采用Spring Boot + Mysql + MyBatis开发，使用thymeleaf做前端模板引擎
使用Kafka做消息通知，Redis数据存储，文件上传使用阿里云对象存储。

- 这个社区是基于大学时期的一个课设项目，当时课设也只是完成特定的功能，在页面的美观度，
价值上没有什么太多的体现，一次偶然的机会，我看到了Layui的前端社区模板跟我的课设项目有
几分相似，就想着能不能自己试着用Java写后端逻辑，于是一个多月的时间，终于完成了Layui的
大部分功能，之后也会不断的更新
- 课设项目大绝大多数功能都是我一个人开发的，之后更新的木同社区也是我一个人独立开发的，
能力有限，有的东西都是现学现用，可能没有时间了解太底层的东西。
- 项目本身的Mysql，Redis，Kafka等都是直接安装在服务器上面的，也避免了部署的麻烦，但是
服务器的性能有限，本人经济能力也有限，所有的环境都是单机部署，可能会存在一个单节点出现的问题。
- 现在整个项目的展示页面已经完成了，我现在正在做社区的后台关系系统，是基于Spring Boot + Vue实现的，
并且整个社区后台管理系统采用前后端分离的技术。我之前没有系统的学习过前端，我现在也是现学现用，可能
后台管理的开发会很长时间。
- 域名正在申请中，后期会加上OAuth2.0登陆。
### 🎓 站点演示

![](https://mutong-community.oss-cn-beijing.aliyuncs.com/KSZ7%7D%7E99%60GLF%607%5BX%25%25X6V7A.png)
![](https://mutong-community.oss-cn-beijing.aliyuncs.com/SK8%7DD8%60A_HB8%40M%7B%7D%7DS%5DFH06.png)
![](https://mutong-community.oss-cn-beijing.aliyuncs.com/52P%25%40I%5B4XVRDFO4RYL%604%29E3.png)
![](https://mutong-community.oss-cn-beijing.aliyuncs.com/YOOSS%25HV%5DLZL%25%7EWEN19GPBM.png)

### 🎓社区亮点

- 网站注册采用邮箱注册的形式，新用户注册之后，会给你的邮箱发一封激活邮件，点击邮件，即可激活账户
- 分页逻辑纯Java代码实现，没有使用诸如MyBatis的插件以及ui框架的js，编写Page相关类，在每一个Controller引入Page，
这个Page可以设置sql查询的offset，limit，然后把page相关信息通过模板引擎写进前端代码里面。
- kafka实现的消息通知，在每一个用户评论，收藏，点赞之后，会给相应的点赞收藏评论消息发送给目标用户，用户点击即可查看消息
同时可以把未读消息设置已读。
- 首页签到功能，每次签到会增加分数字段，后期会考虑添加一个排行榜的功能。

### 🎓帮助文档

下面的文章是我在开发的过程中遇到的一些问题相应的解决方案，在一个通用的代码逻辑上能复制粘贴不手写，
只需要关注关键代码逻辑的编写即可，

[1.0 Spring Boot整合 kaptcha 实现验证码的功能 ](https://blog.csdn.net/weixin_34221276/article/details/89657736)

[2.0 hutool官方文档](https://www.hutool.cn/docs/#/)

[3.0 Spring Boot 整合 Interceptor 实现拦截器的功能](https://blog.csdn.net/u012326462/article/details/80509718)

[4.0 集成富文本编辑器](https://www.layui.com/doc/modules/layedit.html)

[5.0 开发环境不出错，打成jar包发布出错](https://www.cnblogs.com/ming-blogs/archive/2019/01/18/10288579.html)

[6.0 CSS中 Important设置优先级](http://www.w3chtml.com/css3/rules/!important.html)

[7.0 Editer.md 官方文档](http://editor.md.ipandao.com/)

[8.0 Editer.md Github地址](https://github.com/pandao/editor.md)

[9.0 jquery里面执行post请求格式](https://www.cnblogs.com/AChongi/p/11368819.html)

[10.0 Spring Boot & ControllerAdvice  全局处理异常](https://blog.csdn.net/qq_29550537/article/details/96336131?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.nonecase)

[11.0 Redis乱码问题 没有序列化key和value](https://www.cnblogs.com/liuchuanfeng/p/7009027.html)

[12.0 Spring Boot集成Kafka](https://www.cnblogs.com/asker009/p/9990088.html)
