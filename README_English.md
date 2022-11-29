
<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">STS v0.0.1</h1>
<h4 align="center">Secret Transfer Service</h4>
<p align="center"></p>

---

## Preface：
- [在线文档：https://github.com/oamlab/STS](https://github.com/oamlab/STS)
- [Online Documentation：https://github.com/oamlab/STS/](https://github.com/oamlab/STS/blob/main/README_English.md)

## Background:
- Solve the encryption requirements of confidential passwords of business environment configuration items. For example, the management configuration items of a certain system platform have the problem of clear keys, which may result in the leakage of artificial keys in the daily management process.
- After the infrastructure platform of a project is deployed, a stress test needs to be performed from the interface to the DB.

## Purpose
- **Scenario A:** It can be used to encrypt confidential configuration items in business environment, as follows:
- 1.The database administrator of a project's production environment creates a new database account A, password P
- 2.The database administrator in the production environment uses the STS interface to obtain the keyid, combined with the password P, and obtains the ciphertext XXX of the password P on the STS interface
- 3.The database administrator in the production environment distributes the ciphertext XXX and keyid to the project team members into the configuration items of the business program, or into the ConfigMaps of the platform.
- 4.fter the business program starts, send the ciphertext XXX and keyid to the STS interface to obtain the plaintext password P
- 5.The business program uses account A and password P to connect to the database.
- Notice:
- 1.It is required that only the production environment database administrator and business program host IP can access the STS, and other people and devices cannot access the STS interface;
- 2.You can also encrypt your account.
- 3.Theoretically, the leakage of the ciphertext XXX and keyid will not cause leakage, because the ciphertext cannot be decrypted without the STS.
- 4.The test environment can independently deploy a set of STS, which is different from the production environment.
- 
- 
- **Scenario B:** It can be used for interface testing and stress testing before the delivery of the infrastructure environment, as follows:
- 1.A project has newly deployed a kubernetes platform.
- 2.The tester deploys STS into kubernetes and starts 100 Pods, which connect to the same database.
- 3.The tester made a 10-minute interface request to create a secret key at a speed of 10,000 QPS, which will theoretically generate 6 million pieces of data.
- 4.The tester divides the actual number of data bars by 6 million to obtain the interface availability data and performance data.
- 5.During this process, the testers also observed the relevant load capacity data and charts of the host and platform such as CPU, memory, network, disk, etc.
- 6.The tester summarizes and generates a comprehensive report, and feeds back the load capacity of the kubernetes platform to the business department.

## Basic profile
- Development language: JAVA
- Key algorithm: SM4 encryption, symmetric encryption
- Interface type: RESTful
- Scope of use: within the scope of business projects, within the microservice cluster
- Access control: iptables restricts client IP range, nginx restricts client IP range
- High availability: database master-slave (master downtime cannot add keys, but does not affect query), interface HA+VIP

## Summary function pseudo code

``` java
def makeKey():
    key = random(Symmetric key X bits)
    sqlquery(conn,insert,key)
    keyId = sqlquery(conn,select,key)
    return keyId,key

def getKey(keyId):
    key = sqlquery(conn,select,keyId)
    return key

def encrypt(keyId,plaintext):
    ciphertext = algorithm(encrypt,plaintext,key=getKey(keyId))
    return ciphertext

def decrypt(keyId,ciphertext):
    plaintext = algorithm(decrypt,ciphertext,key=getKey(keyId))
    return plaintext
```
## To be improved

- **1.** At present, it is only to implement the envisaged functions, and the code structure needs to be further designed, and it can also be embedded in a more complete framework.
- **2.** It is recommended to deploy in the cluster, because the interface is not authenticated, and it does not seem to require authentication at present.
- **3.** URL filtering, etc. to be added.
- **4.** The plaintext should be base64 encoded.
- **5.** To be further safety design.
- **6.** Exception capture, log (business log may not be recorded).
- **7.** others.

## other
- **1.** The database password, etc. in the project are handwritten and can be modified as needed.

## Test
``` java
Test address:
http://127.0.0.1:8080/OAMLab/doc.html

MakeKey：http://127.0.0.1:8080/OAMLab/api/v1/key/makeKey
{
	"cipherText": "",
	"id": 0,
	"key": "",
	"keyId": "",
	"plainText": ""
}

Encrypt：http://127.0.0.1:8080/OAMLab/api/v1/key/dataEncrypt
{
	"cipherText": "",
	"id": 0,
	"key": "",
	"keyId": "6GsjGc6febXsRPqyRMKAATe2olVdjqBJuja5vbr6lCmpaia54qdvNhr2KZNYITu3",
	"plainText": "OAMLab"
}

Decrypt：http://127.0.0.1:8080/OAMLab/api/v1/key/dataDecrypt
{
	"cipherText": "4808bd3336f933121118ba0798dc0a61",
	"id": 0,
	"key": "",
	"keyId": "6GsjGc6febXsRPqyRMKAATe2olVdjqBJuja5vbr6lCmpaia54qdvNhr2KZNYITu3",
	"plainText": ""
}
``` 

## Technical exchange club
(CN)SHENZHEN Operation And Maintenance CLUB, QQ group：216589280 [Click to join](https://jq.qq.com/?_wv=1027&k=tdDtDoUp)

## Compile
<br>
<p align="center">
	<img alt="logo" src="https://github.com/oamlab/STS/blob/main/101_build_example.png">
</p>

## Start STS
<p align="center">
	<img alt="logo" src="https://github.com/oamlab/STS/blob/main/102_start_example.png">
</p>

## Use
<p align="center">
	<img alt="logo" src="https://github.com/oamlab/STS/blob/main/201_makekey.png">
</p>

<p align="center">
	<img alt="logo" src="https://github.com/oamlab/STS/blob/main/202_dataEncrypt.png">
</p>

<p align="center">
	<img alt="logo" src="https://github.com/oamlab/STS/blob/main/203_dataDecrypt.png">
</p>

## Performance_Testing
- Cloud platform:Kubernetes
- Number of containers(STS)：2
- Concurrency:200
- Total requests：20000
- [JMeter-Testing-profile](https://github.com/oamlab/STS/blob/main/Performance_Testing/301_Apache_JMeter_TestPlanA.20221114.1115.jmx)

<p align="center">
	<img alt="logo" src="https://github.com/oamlab/STS/blob/main/Performance_Testing/151_Kubernetes_STS.png">
</p>

<p align="center">
	<img alt="logo" src="https://github.com/oamlab/STS/blob/main/Performance_Testing/201_Thread_Group.png">
</p>

<p align="center">
	<img alt="logo" src="https://github.com/oamlab/STS/blob/main/Performance_Testing/202_Report.png">
</p>

<p align="center">
	<img alt="logo" src="https://github.com/oamlab/STS/blob/main/Performance_Testing/203_data.png">
</p>