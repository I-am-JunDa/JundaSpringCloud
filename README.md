## 项目开发环境
MacOS | Windows| Linux
Java 8
IntelliJ IDEA
## 相关软件

| 软件名   | 软件版本  | 是否必须 |
|-------|-------|------|
| MySql | 8.0+  | 是    |
| Redis | 5.0.7 | 是    |
| Nacos |       | 是    |

## 组件说明



## 四、项目结构

    jundaSpringCloud -- 根目录
    ├── junda-auth -- 权限认证模块
    ├── junda-core -- 核心公共模块
    |   ├──security 安全模块
    |   ├──common 公共模块 全局异常，自定义注解
    |   ├──common-api 服务调用api接口,feign
    |   ├──model 业务相关公共 entity、枚举、VO
    ├── junda-service01 -- 服务01
    ├── junda-service02 -- 服务02
