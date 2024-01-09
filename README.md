
BlockChain-simple-demo 是一个使用Java、Maven和Springboot构建的简单区块链原理实现，最最最基本的版本，后续完善。

安装：

克隆本仓库：git clone git@github.com:imminer668/web3camp-blockChain-simple.git


进入项目目录：cd web3camp-blockChain-simple


构建项目：mvn clean install


使用方法
run BlockchainTenetApplication.java


访问：
挖矿 get ：http://ip:8080/btc/mine
![mine](https://github.com/imminer668/web3camp-blockChain-simple/blob/master/blockchain-tenet/image/mine.png) 


提交交易
post ：http://ip:8080/btc/new
![newTrx](https://github.com/imminer668/web3camp-blockChain-simple/blob/master/blockchain-tenet/image/newTrx.png)



获取链上信息

get: http://ip:8080/btc/getChain
![chain](https://github.com/imminer668/web3camp-blockChain-simple/blob/master/blockchain-tenet/image/getChain.png)
