# blog 一个基于SpringBoot技术栈的个人博客--->服务端  

主要技术栈:**SpringBoot** + Spring Data JPA + SpringMVC + Thymeleaf + Druid + Redis + Mysql + Tomcat  
**此博客系统采用的是前后端分离,前端项目地址**:[https://github.com/sa4e/blog-view](https://github.com/sa4e/blog-view "blog-view")

# 2017/8/14 星期一 下午 9:11:36 
服务端的初始导入:
项目整体架构的初步搭建 + 简单连接测试  
服务端项目结构如图  
![](http://i.imgur.com/DbvJhhk.png)  
> 
- blog:父工程(管理依赖版本)
- blog-model:blog实体层
- blog-repository:blog持久层
- blog-interface:blog服务层(接口)
- blog-service:blog服务层(实现类)  


#### <font face="微软雅黑" color="red">dubbo与Spring整合的一个坑!</font> ####
**dubbo的@Service注解不能和@Transactional注解同时使用**  
我们知道@Serveice注解用来发布服务的,而@Transactional是控制事务管理的,涉及到数据库的**增删改**就要有事务的管理,所以这两个注解在服务层是少不了的了。但是**同时使用就会导致服务发布失败**!  
### 解决办法: ###
打开dubbo的源码包,打开目录com/alibaba/config/annotaion/,找到**Service.java**文件  
1. 增加一个 **@Inherited的注解(记得导包)**  
![图片](http://i.imgur.com/uT5wEbH.png)  
2. 编译 **javac Service.java**  
3. 找到dubbo-x.x.x.jar,将编译后的Service.class替换掉原来的  
4. 在**application.properties**中配置使用cglib动态代理  
`spring.aop.proxy-target-class=true`

简单4步就能解决了,当然也有其他的解决办法,可以不用修改源码,缺点是要使用到配置文件,我使用SpringBoot开发一般是全注解,所以就这样了。  
  
**注意:服务发布的格式如下:**
![图片](http://i.imgur.com/8d2gpzV.png)  
# 2017/9/14 星期四 下午 11:54:55 
12号才开始编写的服务端代码,使用springboot来开发进度是真的猛,核心的功能代码已经编写完成了,还是写服务端来的舒服,根据业务需求返回正确的数据即可,不像前端交互要考虑一大推东西最费时间的,相信过几天就能上线0.1版本了,然后就是优化补坑!  
![](https://i.imgur.com/S0q3P6d.png)  

#### 当日的积累  
1. **SpringData JPA uuid生成策略**  
![](https://i.imgur.com/i6afb2g.png)  
2. **表多对多关联关系**  
Blog表  
![](https://i.imgur.com/qs1YOb0.png)  
Tag表  
![](https://i.imgur.com/eZGfIKf.png)  
3. **SpringMvc操作session作用域保存用户信息**  
首先在控制器上定义sesison的Key
![](https://i.imgur.com/WNwrqDK.png)  
保存用户信息就像绑定属性一样`modelMap.addAttribute("user", user);`

# 2017/9/18 星期一 下午 9:50:23 
本想着接下来的编码会一帆风顺,没想到陷入了一个大坑。**dubbo**默认序列化使用**hessian** ,在网络传输(RPC)序列化过程中,无法序列化对象,进入到死循环中,直至抛出**stackOverError** ,导致程序卡死。而网上对这部分的问题描述非常少,苦苦找了几天,还是无办法解决。后来了解到**dubbo**在前期的搭建使用是简便,而后期维护和很多高级特性,个人觉得以目前的技术水平很难驾驭此框架,所以果断放弃使用 **dubbo**。  
#### 使用Java默认序列化
使用Java默认的序列化得以解决问题。然而前期搭建项目整体架构时,就使用了dubbo,如今放弃使用,就不得不从架构上改变。  
**RPC**无法使用了,原本的`blog-view`项目,现整合进`blog`成为`blog`的一个子模块  
现整个项目结构如图:  
![](https://i.imgur.com/6sQXG3n.png)  
> 
- blog:父工程(管理依赖版本)
- blog-model:blog实体层
- blog-repository:blog持久层
- blog-interface:blog服务层(接口)
- blog-service:blog服务层(实现类) 
- blog-view:blog表现层

