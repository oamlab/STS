
<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">STS v0.1.0</h1>
<h4 align="center">Secret Transfer Service</h4>
<p align="center">
  <a href="./LICENSE"><img alt="license" src="https://img.shields.io/github/license/oamlab/STS" /></a>
  <img alt="repo-size" src="https://img.shields.io/github/repo-size/oamlab/STS" />
</p>

<p align="center">
   <a href="https://github.com/oamlab/STS">github</a> | 
   <a href="https://gitee.com/oamlab/STS">gitee</a> | 
   <a href="https://github.com/oamlab/STS/blob/main/README.English.md">English</a>
</p>

<p align="center"></p>

---

## 🌏 前言
- [在线文档：https://gitee.com/oamlab/STS](https://gitee.com/oamlab/STS)
- [Online Documentation：https://github.com/oamlab/STS/](https://github.com/oamlab/STS/blob/main/README.English.md)

*[国际访问请使用国际资料仓github入口，点击进入](https://github.com/oamlab/STS)

## 🔒 背景
- 解决业务环境配置项的涉密密码的加密需求。譬如：某系统平台的管理的配置项有秘钥明码的问题，在日常管理过程中，可能造成人为的秘钥外泄。
- 某项目的基础设施平台部署后，需要进行从接口到DB的压力测试。

## 🔑 用途
- **场景A：** 可用于业务环境涉密配置项加密，如下：
- 1、某项目的生产环境数据库管理员新建了数据库账号A，密码P
- 2、生产环境数据库管理员使用STS接口获取keyid，结合密码P，在STS接口获得密码P的密文XXX
- 3、生产环境数据库管理员将密文XXX和keyid发放给项目组成员放入业务程序的配置项，或放入平台的ConfigMaps。
- 4、业务程序启动后将密文XXX和keyid发送到STS接口获得明文密码P
- 5、业务程序使用账号A和密码P连接数据库。
- 注意：
- 1、要求该STS只有生产环境数据库管理员和业务程序主机IP可访问，其他人和设备无法访问该STS接口；
- 2、也可以把账号也加密了。
- 3、理论上密文XXX和keyid对外泄露不会产生泄密影响，因为脱离该STS则该密文无法解密。
- 4、测试环境可以独立部署一套STS，与生产环境区别开来。
-
-
- **场景B：** 可用于基础设施环境交付前的接口测试、压力测试，如下：
- 1、某项目新部署了一套kubernetes平台。
- 2、测试人员将STS部署到kubernetes内，并启动100个Pod，这些Pod连接同一数据库。
- 3、测试人员以1万QPS的速度进行了10分钟制造秘钥的接口请求，理论上将会产生600万条数据。
- 4、测试人员将实际获得的数据条数除以600万，获得了接口可用率数据、性能数据。
- 5、在此过程中测试人员也观察到了宿主机和平台的CPU、内存、网络、磁盘等相关负载能力数据和图表。
- 6、测试人员总结生成综合报告，向业务部门反馈该kubernetes平台的负载能力。

## 🔖 基本概况
- 开发语言：JAVA
- Java_Version：1.8.0
- 秘钥算法：SM4加密，对称加密
- 接口类型：RESTful
- 使用范围：业务项目范围内、微服务集群内
- 访问控制：iptables限制客户端IP范围，nginx限制客户端IP范围
- 高可用：数据库主从（主宕机不能新增key，但不影响查询）、接口双活+VIP

## 📃 概要功能伪代码

``` java
def makeKey():
    key = random(对称秘钥X位)
    sqlquery(conn,insert,key)
    keyId = sqlquery(conn,select,key)
    return keyId,key

def getKey(keyId):
    key = sqlquery(conn,select,keyId)
    return key

def encrypt(keyId,明文):
    密文 = 算法(encrypt,明码字符,key=getKey(keyId))
    return 密文

def decrypt(keyId,密文):
    明文 = 算法(decrypt,密文,key=getKey(keyId))
    return 明文

def healthy():
    healthyStatus = Math.abs(mysqlTimeStamp - javaTimeStamp)
    return healthyStatus
```
## 📑 待改善

- **1、** 当前只是实现设想中的功能，代码结构需要进一步设计，也可以嵌入到更完善的框架内。
- **2、** 建议部署于集群内，因为接口无鉴权，当前看起来也不需要鉴权。
- **3、** 待添加URL过滤、字符过滤等。
- **4、** 明文要进行base64编码。
- **5、** 待进行进一步安全设计。
- **6、** 异常捕捉、日志（可以不记录业务日志）。
- **7、** 其他。

## 📰 其他
- **1、** 工程内的数据库密码等，是随手写的，可以依需要进行修改。

## 📋 自测
``` java
自测地址:
http://127.0.0.1:8080/OAMLab/doc.html

制造秘钥: http://127.0.0.1:8080/OAMLab/api/v1/key/makeKey
{
	"cipherText": "",
	"id": 0,
	"key": "",
	"keyId": "",
	"plainText": ""
}

加密: http://127.0.0.1:8080/OAMLab/api/v1/key/dataEncrypt
{
	"cipherText": "",
	"id": 0,
	"key": "",
	"keyId": "6GsjGc6febXsRPqyRMKAATe2olVdjqBJuja5vbr6lCmpaia54qdvNhr2KZNYITu3",
	"plainText": "OAMLab"
}

解密: http://127.0.0.1:8080/OAMLab/api/v1/key/dataDecrypt
{
	"cipherText": "4808bd3336f933121118ba0798dc0a61",
	"id": 0,
	"key": "",
	"keyId": "6GsjGc6febXsRPqyRMKAATe2olVdjqBJuja5vbr6lCmpaia54qdvNhr2KZNYITu3",
	"plainText": ""
}

API健康检查: http://127.0.0.1:8080/OAMLab/api/v1/healthy
{
	"healthy": "1",
}
``` 

## 📶 技术交流群
深圳运维圈 QQ交流群：216589280 [点击加入](https://jq.qq.com/?_wv=1027&k=tdDtDoUp)

*[国际访问请使用国际资料仓github入口，点击进入](https://github.com/oamlab/STS)

## 🔨 编译
<br>
<p align="center">
	<img alt="OAMLab_sts_build_example" src="https://gitee.com/OAMLab/STS/raw/main/Compile_to_Trial/101_build_example.png">
</p>

## 📡 启动
<p align="center">
	<img alt="OAMLab_sts_start_example" src="https://gitee.com/OAMLab/STS/raw/main/Compile_to_Trial/102_start_example.png">
</p>

## 🔧 调试与试用
<p align="center">
	<img alt="OAMLab_sts_MakeKey" src="https://gitee.com/OAMLab/STS/raw/main/Compile_to_Trial/201_makekey.png">
</p>

<p align="center">
	<img alt="OAMLab_sts_dataEncrypt" src="https://gitee.com/OAMLab/STS/raw/main/Compile_to_Trial/202_dataEncrypt.png">
</p>

<p align="center">
	<img alt="OAMLab_sts_dataDecrypt" src="https://gitee.com/OAMLab/STS/raw/main/Compile_to_Trial/203_dataDecrypt.png">
</p>

## 🔩 压力测试
- 云平台：Kubernetes
- 容器数(STS)：2
- 并发：200
- 总请求次数：20000
- [JMeter-Testing-profile](https://gitee.com/OAMLab/STS/raw/main/Performance_Testing/301_Apache_JMeter_TestPlanA.20221114.1115.jmx)

<p align="center">
	<img alt="OAMLab_sts_Kubernetes_STS" src="https://gitee.com/OAMLab/STS/raw/main/Performance_Testing/151_Kubernetes_STS.png">
</p>

<p align="center">
	<img alt="OAMLab_sts_Kubernetes_STS_Jmeter_Thread_Group" src="https://gitee.com/OAMLab/STS/raw/main/Performance_Testing/201_Thread_Group.png">
</p>

<p align="center">
	<img alt="OAMLab_sts_Kubernetes_STS_Jmeter_Report" src="https://gitee.com/OAMLab/STS/raw/main/Performance_Testing/202_Report.png">
</p>

<p align="center">
	<img alt="OAMLab_sts_Kubernetes_STS_Jmeter_Mysql_Data" src="https://gitee.com/OAMLab/STS/raw/main/Performance_Testing/203_data.png">
</p>

## 🔃 提交代码
- 提交代码路径：个人分支 >> develop >> main
- 譬如：develop_AndyYao_202301 >> develop >> main
- 为了方便做代码评审，每次最多提交5个代码文件，每次最多提交200行代码。

## 🆓 版权说明
- 原软件或依赖软件仍保持其原有授权。
- 独立于原软件或依赖软件之外编写的代码、辅助软件、使用方法、文档等，是GNU General Public License v3.0授权。
- 譬如：XXX Community Server 1.0.1是基于GPL v2.0授权，我们编写的辅助软件start_XXX.sh和文档是基于GPL v3.0。
