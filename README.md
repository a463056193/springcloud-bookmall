# springcloud-bookmall
a project for studying springcloud

Spring Cloud 为最常见的分布式系统模式提供了一种简单且易于接受的编程模型，帮助开发人员构建有弹性的、可靠的、协调的应用程序。Spring Cloud 构建于 Spring Boot 之上，使得开发者很容易入手并快速应用于生产中。

## 项目
学习springcloud，通过该项目重在了解springcloud各组件的使用，有部分业务操作没有实现

**该项目包含以下模块**
* **授权中心模块** ：bookmall-auth
* **购物车模块** ：bookmall-cart
* **通用工具模块** ：bookmall-common
* **网关模块** ：bookmall-gateway
* **构建商品页面模块** ：bookmall-goods-web
* **商品微服务模块** ： bookmall-item
* **订单模块** ：bookmall-order
* **注册中心** ：bookmall-registry
* **搜索模块** ：bookmall-search
* **短信服务模块** ：bookmall-sms
* **上传模块** ：bookmall-upload
* **用户模块** ：bookmall-user

该项目的前台模块在bookmall-portal仓库中

### 授权中心模块
微服务集群中的每个服务，对外提供的都是Rest风格的接口。而Rest风格的一个最重要的规范就是：服务的无状态性

**实现无状态登录流程**：
- 当客户端第一次请求服务时，服务端对用户进行信息认证（登录）
- 认证通过，将用户信息进行加密形成token，返回给客户端，作为登录凭证
- 以后每次请求，客户端都携带认证的token
- 服务的对token进行解密，判断是否有效。

token是识别客户端身份的唯一标示，如果加密不够严密，被人伪造那就完蛋了。

项目将采用JWT + RSA非对称加密保证token的安全性

在网关模块中使用过滤器对token进行校验，实现无状态登录

### 购物车模块

购物车功能分未登录和已登录两种状态
新增商品：

- 判断是否登录
  - 是：则添加商品到后台Redis中
  - 否：则添加商品到本地的Localstorage

无论哪种新增，完成后都需要查询购物车列表：

- 判断是否登录
  - 否：直接查询localstorage中数据并展示
  - 是：已登录，则需要先看本地是否有数据，
    - 有：需要提交到后台添加到redis，合并数据，而后查询
    - 否：直接去后台查询redis，而后返回

在未登录状态使用web本地存储Localstorage实现对购物车数据的保存

登录状态使用JWT鉴权

因业务数据，并没有对该模块做具体实现


### 通用工具模块

该模块封装了一些全局使用的工具类：如分页结果、加盐工具类等


### 网关模块

zuul网关统一消费者工程的调用入口，便于访问和管理
是系统唯一对外的入口，介于客户端与服务器端之间，用于对请求进行鉴权、限流、 路由、监控等功能。


### 构建商品页面模块
因业务数据，并没有对该模块做具体实现

### 商品模块

该模块分为接口和模块和业务模块，对商品的业务操作做了具体实现

### 订单模块

使用了学习资源所给的接口，对接口进行了实现

### 注册中心模块

Eureka 就是一个服务发现框架

服务发现：其实就是一个“中介”，整个过程中有三个角色：服务提供者(出租房子的)、服务消费者(租客)、服务中介(房屋中介)。

服务提供者： 就是提供一些自己能够执行的一些服务给外界。

服务消费者： 就是需要使用一些服务的“用户”。

服务中介： 其实就是服务提供者和服务消费者之间的“桥梁”，服务提供者可以把自己注册到服务中介那里，而服务消费者如需要消费一些服务(使用一些功能)就可以在服务中介中寻找注册在服务中介的服务提供者。


### 搜索模块

用户访问首页，一般都会直接搜索来寻找自己想要购买的商品。

而商品的数量非常多，而且分类繁杂。如何能正确的显示出用户想要的商品，并进行合理的过滤，尽快促成交易，是搜索系统要研究的核心。

面对这样复杂的搜索业务和数据量，使用传统数据库搜索就显得力不从心，一般都会使用全文检索技术

该模块拟使用elasticsearch完成


### 短信服务模块

对需要进行短信验证的业务，使用该模块

因为短信发送API调用时长的不确定性，为了提高程序的响应速度，短信发送我们都将采用异步发送方式，即：

- 短信服务监听MQ消息，收到消息后发送短信。
- 其它服务要发送短信时，通过MQ通知短信微服务。

该模块参考了腾讯云的短信服务文档进行实现


### 上传模块

springcloud对应高并发的系统，数据量庞大，一般使用分布式文件系统代替本地文件存储

分布式文件系统（Distributed File System）是指文件系统管理的物理存储资源不一定直接连接在本地节点上，而是通过计算机网络与节点相连。 

FastDFS是由淘宝的余庆先生所开发的一个轻量级、高性能的开源分布式文件系统。用纯C语言开发，功能丰富：

- 文件存储
- 文件同步
- 文件访问（上传、下载）
- 存取负载均衡
- 在线扩容

适合有大容量存储需求的应用或系统。

### 用户模块
一些用户登录等功能。
