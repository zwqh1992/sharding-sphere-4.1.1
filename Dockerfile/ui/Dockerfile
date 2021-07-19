# 设置基准镜像
FROM java:8

# 设置描述
MAINTAINER zwqh.top

# 环境变量
ENV SHARDING_VERSION=4.1.1
ENV SHARDING_UI=apache-shardingsphere-$SHARDING_VERSION-shardingsphere-ui-bin
ENV SHARDING_PORT=8088
ENV SHA512_VAL=17ab2089d1ddd00f13ea270b996fbf447aa16b5408ef1e5cbca56eeb1828e06a841fad54d78417278db6e876c0c65fa23d86dcbed5c1e3fd2796d4747b4bac4b
ENV SHARDING_HOME=/opt/shardingsphere-ui

# 下载
RUN curl "https://archive.apache.org/dist/shardingsphere/shardingsphere-ui-$SHARDING_VERSION/$SHARDING_UI.tar.gz" -o $SHARDING_UI.tar.gz

# 验证
RUN if [ "$SHA512_VAL" != "$(sha512sum $SHARDING_UI.tar.gz | awk '{print($1)}')" ];\
    then \
        echo "sha512 values doesn't match! exiting."  && \
        exit 1; \
    fi;
# 文件解压、链接、授权等
RUN tar xzf $SHARDING_UI.tar.gz -C  /opt && \
    ln -s /opt/$SHARDING_UI $SHARDING_HOME && \
    useradd -r -M -d $SHARDING_HOME sharding && \
    chown -R sharding:sharding /opt/$SHARDING_UI && \
    chown -h sharding:sharding $SHARDING_HOME 

USER sharding

WORKDIR $SHARDING_HOME
# 容器启动时运行
ENTRYPOINT $SHARDING_HOME/bin/start.sh $SHARDING_PORT && tail -f $SHARDING_HOME/logs/stdout.log
