### ZooKeeper 单点搭建

#### 1、镜像拉取（拉取最新的）

```shell
docker pull zookeeper:latest
```

#### 2、创建并启动 zk 镜像

```shell
docker run --name my_zookeeper -d zookeeper:latest
```

这个命令会在后台运行一个 zookeeper 容器, 名字是 my_zookeeper, 并且它默认会导出 2181 端口，也可以通过 -p 2181:2181 绑定宿主机端口供一些客户端连接。

```shell
docker logs -f my_zookeeper
```
使用如上命令，我们可以看到 ZooKeeper 相关日主信息。

#### 3、使用 ZK 命令行客户端连接 ZK

因为刚才我们启动的那个 ZK 容器并没有绑定宿主机的端口, 因此我们不能直接访问它. 但是我们可以通过 Docker 的 link 机制来对这个 ZK 容器进行访问. 执行如下命令:
```shell
docker run -it --rm --link my_zookeeper:zookeeper zookeeper zkCli.sh -server zookeeper
```
这个命令的含义是:
1. 启动一个 zookeeper 镜像, 并运行这个镜像内的 zkCli.sh 命令, 命令参数是 "-server zookeeper"
2. 将我们先前启动的名为 my_zookeeper 的容器连接(link) 到我们新建的这个容器上, 并将其主机名命名为 zookeeper

当我们执行了这个命令后, 就可以像正常使用 ZK 命令行客户端一样操作 ZK 服务了。

### ZooKeeper 集群搭建

#### 1、docker-compose.yml

首先创建一个名为 docker-compose.yml 的文件, 其内容如下:

```yaml
version: '3.1'
services:
  zoo1:
    image: zookeeper
    restart: always
    hostname: zoo1
    container_name: zoo1
    ports:
      - "2181:2181"
    environment:
      ZOO_MY_ID: 1
      ZOO_SERVERS: server.1=zoo1:2888:3888;2181  server.2=zoo2:2888:3888;2181  server.3=zoo3:2888:3888;2181
  zoo2:
    image: zookeeper
    restart: always
    hostname: zoo2
    container_name: zoo2
    ports:
      - "2182:2181"
    environment:
      ZOO_MY_ID: 2
      ZOO_SERVERS: server.1=zoo1:2888:3888;2181  server.2=zoo2:2888:3888;2181  server.3=zoo3:2888:3888;2181
  zoo3:
    image: zookeeper
    restart: always
    hostname: zoo3
    container_name: zoo3
    ports:
      - "2183:2181"
    environment:
      ZOO_MY_ID: 3
      ZOO_SERVERS: server.1=zoo1:2888:3888;2181  server.2=zoo2:2888:3888;2181  server.3=zoo3:2888:3888;2181
networks:
  default:
    external:
      name: zookeeper_network
```
这个配置文件会告诉 Docker 分别运行三个 zookeeper 镜像, 并分别将本地的 2181, 2182, 2183 端口绑定到对应的容器的2181端口上。

ZOO_MY_ID 和 ZOO_SERVERS 是搭建 ZK 集群需要设置的两个环境变量：

- ZOO_MY_ID 表示 ZK 服务的 id, 它是1-255 之间的整数, 必须在集群中唯一。
- ZOO_SERVERS 是ZK 集群的主机列表。

端口：

- 2888是zk之间通信的端口。
- 3888是zk之间投票选举的端口。

networks创建：

```shell
docker networks create zookeeper_network
```



#### 2、启动 ZK 集群

接着我们在 docker-compose.yml 当前目录下运行：

```shell
COMPOSE_PROJECT_NAME=zk_cluster docker-compose up -d
```
即可启动 ZK 集群了。

#### 3、查看 ZK 集群容器

接着在另一个终端中运行 docker-compose ps 命令（在当前目录下执行），可以查看启动的 ZK 容器：

```shell
>>> COMPOSE_PROJECT_NAME=zk_cluster docker-compose ps

Name              Command               State                          Ports                        
----------------------------------------------------------------------------------------------------
zoo1   /docker-entrypoint.sh zkSe ...   Up      0.0.0.0:2181->2181/tcp, 2888/tcp, 3888/tcp, 8080/tcp
zoo2   /docker-entrypoint.sh zkSe ...   Up      0.0.0.0:2182->2181/tcp, 2888/tcp, 3888/tcp, 8080/tcp
zoo3   /docker-entrypoint.sh zkSe ...   Up      0.0.0.0:2183->2181/tcp, 2888/tcp, 3888/tcp, 8080/tcp

```
注意, 我们在 "docker-compose up" 和 "docker-compose ps" 前都添加了 COMPOSE_PROJECT_NAME=zk_cluster 这个环境变量, 这是为我们的 compose 工程起一个名字, 以免与其他的 compose 混淆。

#### 4、关闭 ZK 集群

```shell
COMPOSE_PROJECT_NAME=zk_cluster docker-compose stop
```


#### 5、使用 Docker 命令行客户端连接 ZK 集群
通过 docker-compose ps 命令, 我们知道启动的 ZK 集群的三个主机名分别是 zoo1, zoo2, zoo3, 因此我们分别 link 它们即可:

```shell
docker run -it --rm \
        --link zoo1:zk1 \
        --link zoo2:zk2 \
        --link zoo3:zk3 \
        --net zookeeper_network \
        zookeeper zkCli.sh -server zk1:2181,zk2:2181,zk3:2181
```
#### 6、直接进入容器内部查看

```shell
docker exec -it zoo1 bash
./bin/zkServer.sh status
----------------------------------------------------------------------------------------------------
ZooKeeper JMX enabled by default
Using config: /conf/zoo.cfg
Client port found: 2181. Client address: localhost. Client SSL: false.
Mode: follower
```
```shell
docker exec -it zoo2 bash
./bin/zkServer.sh status
----------------------------------------------------------------------------------------------------
ZooKeeper JMX enabled by default
Using config: /conf/zoo.cfg
Client port found: 2181. Client address: localhost. Client SSL: false.
Mode: follower
```

```shell
docker exec -it zoo3 bash
./bin/zkServer.sh status
----------------------------------------------------------------------------------------------------
ZooKeeper JMX enabled by default
Using config: /conf/zoo.cfg
Client port found: 2181. Client address: localhost. Client SSL: false.
Mode: leader

```

从上状态可以看出，zoo3为leader节点，zoo1和zoo2位follower节点，至此zk集群搭建成功了。

### 命令总结

**拉取镜像**

```shell
docker pull  {镜像名称}:{版本号（默认为latest）}
```

**创建并启动镜像**

```shell
docker run --name {自定义容器名称} -d {镜像名称}:{版本号（默认为latest）}
```

**查看日志**

```shell
docker logs -f {容器名称}
```

**创建网络**

```shell
docker networks create {网络名称}
```

**运行docker-compose**

```shell
COMPOSE_PROJECT_NAME={自定义docker-compose名称，用于区分} docker-compose up -d
```

**查看docker-compose相关容器**

```shell
COMPOSE_PROJECT_NAME={自定义docker-compose名称，用于区分} docker-compose ps
```

**停止docker-compose**

```shell
COMPOSE_PROJECT_NAME={自定义docker-compose名称，用于区分} docker-compose stop
```

**进入容器**

```shell
docker exec -it {容器名称或容器ID} bash
```

**查看ZooKeeper服务状态**

```shell
./zkServer.sh status
```

