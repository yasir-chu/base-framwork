# base-framwork
SpringBoot基础框架

# 基础配置
1. 修改数据库连接数据
2. xxl-job的使用需要有xxl-job的服务端并在application.properties中配置
3. 该项目是eureka的服务提供者也是服务消费者


# 分支说明:
## api-server: 
- 简单的SpringBoot+MybatisPlus+Swagger 架构
- 使用两个maven项目来区分api层和server层，这样 其他服务使用，只需要引用api层依赖即可

