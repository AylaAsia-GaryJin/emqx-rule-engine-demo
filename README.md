# EMQ X Rule Engine POC
验证 EMQ X 企业版规则引擎。
## 搭建环境
1. 运行 `docker-compose.yml` 以启动 EMQ X。 
   1. 确保服务器对外暴露 1883、18083、8083 端口号
2. 启动 kafka
3. 启动 MySQL 5.7+
4. 启动 RocketMQ 或者使用阿里云 RocketMQ 服务
5. 启动当前项目
   1. 如果与 EMQ X 部署在同一台机器，需要修改项目端口号
## 资源与规则管理
### 资源管理
相关 API 见 `ResourceController` 和 `ActionController`。
### 规则管理
相关 API 见 `RuleController`。
## 注意事项
1. `MessageController` 是用来接收 `data_to_webserver` 规则的数据的
2. `data_to_kafka` 规则的数据转发到 kafka 之后，可以使用 `com.ayla.emqxruleenginedemo.kafka` 包的 consumer 来消费
3. `data_to_rocket` 规则的数据转发到 kafka 之后，可以使用 `com.ayla.emqxruleenginedemo.rocketmq` 包的 listener 来消费
4. `Constant` 接口中描述了 EMQ X 企业版 4.3.3 版本所有支持的 action 和 resource_type