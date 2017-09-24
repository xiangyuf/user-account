# User Account Service

### 使用Sping Boot开发的一个简单的用户账户服务，可以对用户的账户信息进行基本的管理

## API:

API相关服务部署在host:8080，8081端口为ops端口, 访问 http://host:8081/ops/jstack 可以返回当前java进程的jstack

### 创建账户: 
	POST /user/add?user_id={user_id}&coins={coin_number}
	
	user_id：非负整数，required；
	coin_number: 非负整数，required；
	
	当用户id存在时会更新金币数量，否则创建新用户。
	
success response：
	
	{"message":"Success","data":""}	//创建成功
	
error response: 

	{"message":"Params should be greater than or equal to 0.","data":""}  //参数不规范

	
### 获取用户账户余额: 	
	GET /user/coins/{user_id}
	
	user_id：非负整数，required；
	
success response：
	
	{"message":"Success","data":50}	//50代表金币余额	
error response: 

	{"message":"Params should be greater than or equal to 0.","data":""}  //参数不规范
	{"message":"Invalid user id. Can't find this user by id.","data":""}  //找不到用户id		

### 用户之间转账: 	
	POST /user/transaction/transfer??from_user_id={user_id1}&to_user_id={user_id2}&coins={coin_number}
	
	user_id1：非负整数，required；
	user_id1：非负整数，required；
	coin_number: 非负整数，required；
	
success response：
	
	{"message":"Success","data":""}	//转账成功
	
error response: 

	{"message":"Params should be greater than or equal to 0.","data":""}  //参数不规范
	{"message":"Invalid user id. Can't find this user by id.","data":""}  //找不到用户id
	{"message":"No enough balance in user's account.","data":""}          //用户余额不足



## 服务部署步骤

### 依赖服务
** JDK8, Maven, Docker, Mysql **

	默认Mysql跑在localhost:3306端口，用户名root，密码root；
	Docker需要可以成功执行docker pull relateiq/oracle-java8;
	

### 安装步骤
1. git clone git@github.com:xiaotui/user-account.git
2. cd user-account
3. 根据./user-account/src/main/schema.sql准备好mysql环境；
4. 修改./user-account/src/main/resources/application.yaml里面的mysql连接字符串；（本地mysql不能用localhost，需要用internal ip才能被docker里面的进程访问到） 
5. 回到user-account目录下，执行 sh user-account.sh start；
6. 使用 sh user-account.sh stop|restart|status 等命令可以管理该帐号服务。


## 代码测试覆盖率报告
## /user-account/code-coverage/index.html
