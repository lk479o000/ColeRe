## 微服务文档 ✨

### 启动 🐱‍🏍

1. 配置环境变量 

     ```
     # nacos 服务地址w
     NACOS_SERVER_ADDR=nacos.bkex.vv
     # nacos 用户名
     NACOS_USERNAME=nacos
     # nacos 密码
     NACOS_PASSWORD=nacos
     # nacos 服务注册命名空间
     NACOS_NAMESPACE=stage
     # nacos 配置命名空间
     NACOS_CONFIG_NAMESPACE=stage
     # 开发环境必填
     ENV_OPTS=test
     # 日志路径
     LOG_HOME=/logs
     ##########################################
     `LOG_HOME` 日志文件路径可按需自定义
     Nacos 参数是压测环境, 可按需配置
     `LOG_HOME` 日志文件路径可按需自定义
     Nacos 参数是压测环境, 可按需配置
     ```

2. 在 Nacos 钟配置当前服务配置
    ```
    app:
        appKey: xxx
   # sharding 配置等...
   ```
3. 在 Nacos `feign-servers.yaml` 配置中声明当前服务

4. 按需在 Nacos 中配置相关 **-db-conf.yaml, **-redis-conf.yaml

🎉 开发指北: http://conf.bkex.io/pages/viewpage.action?pageId=31916063