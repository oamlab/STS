
<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">STS v0.0.1</h1>
<h4 align="center">Secret Transfer Service</h4>
<p align="center"></p>

---

## 前言：
- [在线文档：https://github.com/oamlab/STS](https://github.com/oamlab/STS)

## 背景：
- 解决业务环境配置涉密项的加密需求。
- 譬如：某系统平台的管理有明文的问题，在日常管理过程中，可能造成人为的秘钥外泄。

## 用途
- **1、** 可用于业务环境配置项加密。
- **2、** 可用于基础设施环境交付前的接口测试、压力测试。
- **3、** 其他。

## 基本概况
- 开发语言：JAVA
- 秘钥算法：SM4加密，对称加密
- 接口类型：RESTful
- 使用范围：业务项目范围内、微服务集群内
- 访问控制：iptables限制客户端IP范围，nginx限制客户端IP范围
- 高可用：数据库主从（主宕机不能新增，但不影响查询）、接口双活+VIP

## 概要功能伪代码

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
```
## 待改善

- **1、** 当前只是实现设想中的功能，代码结构需要进一步设计，也可以嵌入到更完善的框架内。
- **2、** 建议部署于集群内，因为接口无鉴权，当前看起来也不需要鉴权。
- **3、** 待添加URL过滤等。
- **4、** 明文要进行base64编码。
- **5、** 待进行进一步安全设计。
- **6、** 异常捕捉、日志（可以不记录业务日志）。
- **7、** 其他。

## 其他
- **1、** 工程内的数据库密码等，是随手写的。

``` java
自测地址：
http://127.0.0.1:8080/swaggerTest/doc.html#/%E7%A7%98%E9%92%A5%E7%AE%A1%E7%90%86API/%E7%A7%98%E9%92%A5%E7%AE%A1%E7%90%86API/makeKeyUsingPOST

制造秘钥：
{
	"cipherText": "",
	"id": 0,
	"key": "",
	"keyId": "",
	"plainText": ""
}

加密：
{
	"cipherText": "",
	"id": 0,
	"key": "",
	"keyId": "6GsjGc6febXsRPqyRMKAATe2olVdjqBJuja5vbr6lCmpaia54qdvNhr2KZNYITu3",
	"plainText": "OAMLab"
}

解密：
{
	"cipherText": "4808bd3336f933121118ba0798dc0a61",
	"id": 0,
	"key": "",
	"keyId": "6GsjGc6febXsRPqyRMKAATe2olVdjqBJuja5vbr6lCmpaia54qdvNhr2KZNYITu3",
	"plainText": ""
}
``` 

## 技术交流群
深圳运维圈 QQ交流群：216589280 [点击加入](https://jq.qq.com/?_wv=1027&k=tdDtDoUp)


<br>
<p align="center">
	<img alt="logo" src="https://github.com/oamlab/STS/blob/main/101_build_example.png">
</p>

<p align="center">
	<img alt="logo" src="https://github.com/oamlab/STS/blob/main/102_start_example.png" width="150" height="150">
</p>

<p align="center">
	<img alt="logo" src="https://github.com/oamlab/STS/blob/main/201_makekey.png" width="150" height="150">
</p>

<p align="center">
	<img alt="logo" src="https://github.com/oamlab/STS/blob/main/202_dataEncrypt.png" width="150" height="150">
</p>

<p align="center">
	<img alt="logo" src="https://github.com/oamlab/STS/blob/main/203_dataDecrypt.png" width="150" height="150">
</p>
