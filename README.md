# Lottery
微服务抽奖系统

DDD 分层架构

DDD (Domain-Driven Design) 是一种软件开发方法，它的核心是将系统按照业务领域进行划分，并将业务逻辑与技术实现相分离。DDD 的分层架构是其重要的组成部分，它主要包括以下四层：

表现层（Presentation Layer）：负责展示数据和接收用户的请求，以及将请求传递给应用服务层。在 Web 应用中通常是控制器层。

应用服务层（Application Service Layer）：负责处理业务逻辑，协调领域对象、基础设施服务和外部系统，以及将数据传递给领域层进行处理。在应用中通常是服务层。

领域层（Domain Layer）：是 DDD 的核心，负责定义业务领域中的概念、规则和行为。它包含实体、值对象、聚合根、领域服务等概念，以及业务逻辑的实现。

基础设施层（Infrastructure Layer）：负责提供技术实现和基础设施服务，如数据库、缓存、消息队列等。它是整个系统的基础，领域层和应用层可以使用它来实现业务逻辑。

其中，应用服务层通过领域服务来调用领域层中的业务逻辑，领域层通过基础设施服务来访问外部资源，如数据库、消息队列等。

DDD 分层架构的优点是能够将系统按照业务领域进行划分，使得系统更加清晰、易于维护和扩展。同时，它还能够将业务逻辑与技术实现相分离，使得系统更加松耦合、灵活性更高。

![](img/DDD+RPC_分层模块.png)

lottery-application，应用层，引用：domain

lottery-common，通用包，引用：无

lottery-domain，领域层，引用：infrastructure

lottery-infrastructure，基础层，引用：无

lottery-interfaces，接口层，引用：application、rpc

lottery-rpc，RPC接口定义层，引用：common

在抽奖活动的设计和开发过程中，会涉及到的表信息包括：活动表、奖品表、策略表、规则表、用户参与表、中奖信息表等

Dubbo 中广播模式的配置，在你实际使用的过程中一般都是使用注册中心模式









