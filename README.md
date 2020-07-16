# community

### 🎓开发日志

#### 6.18 用户登陆注册后台功能

用户注册主要是根据邮箱注册，注册成功之后会发激活邮件，用户登陆邮箱激活，才能正常使用

登陆根据邮箱，密码进行登陆，验证码采用kaptcha包

#### 6.19 用户登陆注册前端页面，密码校验

注册登陆时候出错，页面会显示出错信息，密码必须是大于6位且小于15位。

#### 6.22 用户登陆凭证，退出功能，登陆拦截器

用户登陆的时候同时把凭证添加至cookie ，下一次访问的时候直接登陆。

退出功能，凭证，假删除，把status字段变成0

拦截器，用户登陆会把信息放在cookie里面，我下一次访问网页的时候，会通过拦截器的prehandler方法，先去查看cookie里面有没有ticket信息，有的话根据ticket信息获取userId，获取用户的信息。前端页面显示会根据cookie里面有没有用户，显示登陆注册按钮还是用户具体的信息。

#### 6.23 发表帖子

发帖之前会查看有没有登陆，没有登陆的情况下，点击发帖会直接跳转登陆页面

如果登陆了会直接跳转发帖页面

用户填写信息之后点击提交发表成功。

#### 6.25 页面查询 发表帖子校验 置顶

发表帖子之后，返回首页，首页会显示所有的帖子。后端逻辑 和 前端页面显示

按照不同的帖子类型进行显示，周榜，月榜，最新，加精，热评。

用户发帖之后，会验证title，content，会做敏感词处理，验证码校验

首先显示置顶帖子

#### 6.27 根据标签显示页面

主要的标签是置顶，加精，最赞，按最热，按最新，按最热，同时加上了标签提问，讨论，建议等等

#### 7.8 文章详情 专栏显示

富文本编辑器的支持，试错，最后采用了Editer.md的文本编辑器，回显问题主要解决是通过js代码解决
专栏显示，重新建一张comlun表，，post表里面的columnid 做外键与 column表相关联，显示的时候查column表即可

#### 7.9 富文本编辑器 图片上传 更新个人资料 
更新编辑器为Editer.md富文本编辑器，图片上传使用阿里云oss对象存储。支持网络地址和本地上传。

用户设置模块，支持更新个人资料，包括 邮箱，密码，昵称，城市，个性签名(有bug，textarea无法回显后台数据问题)。

更新个人头像。本地上传。接口测试成功。

#### 7.10 最近访问
最近访问三种思路(采用第2种)
1. 使用redis的list类型，主要的目的是控制list的大小，list可以设置key的进出，当list里面key的大小为12时，再次添加key就要删除尾部的key，永远保持key的大小为12
2. 直接查表，在数据库里面添加一个login_time字段，每次登陆 或者，每次通过cookie直接登陆的时候，都会重新更新登陆时间，然后根据登陆时间直接order by
3. Redis Ltrim 命令

#### 7.11 主页显示问题

页面显示问题的bug修改

置顶加精显示问题

收藏功能 redis中key的设计：connection：user：1;   当收藏的时候吧帖子id放到set集合里面，方面查询每一个用户收藏了多少帖子

#### 7.12 收藏功能 redis乱码问题

收藏功能，根据每次收藏功能的class，'收藏' 是一个class '已收藏'是一个class 进行AJAX请求的处理。

Spring Boot & ControllerAdvice  全局处理异常 

#### 7.15 收藏功能 点赞功能

用reids 的set类型实现


    

### 🎓帮助文档

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