package cc.fone.web3camp.blockchaintenet.blockchain.c;

import cc.fone.web3camp.blockchaintenet.blockchain.b.Block;
import cc.fone.web3camp.blockchaintenet.blockchain.b.Blockchain;
import cc.fone.web3camp.blockchaintenet.blockchain.b.Transaction;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RequestMapping("/btc")
@Controller
public class BlockChainController {
    public static int difficulty = 4; // 设置挖矿难度，即哈希值前几位为0
    public static  String prefix = new String(new char[difficulty]).replace('\0', '0');
    public static Blockchain blockchain = new Blockchain();
    public static List<Transaction> transactions = new ArrayList<>();
    @GetMapping(value = "/mine")
    @ResponseBody
    public String mine(){
        int nonce = 0; // 初始nonce值
        int sice=blockchain.getChain().size()-1;
        Block b=blockchain.getChain().get(sice);
        String previousHash =b.getHash(); // 上一个区块的哈希值
        System.out.println("上一个区块Hash:"+previousHash);
        while (true) {




            long timeStamp = new Date().getTime(); // 当前时间戳

            Block block = new Block(nonce, previousHash, timeStamp, transactions); // 创建区块
            String data = block.toString(); // 获取区块数据
            String hash = calculateHash(data); // 计算哈希值

            if (hash.startsWith(prefix)) {
                System.out.println("挖矿成功！Nonce值为: " + nonce);
                System.out.println("对应的哈希值为: " + hash);
                blockchain.addBlock(block);
                return JSONUtil.toJsonStr(block);
            }

            nonce++;
        }

    }

    @PostMapping(value = "/new")
    @ResponseBody
    public String pushTrx(String sender,String recipient,int amount) {
        Transaction trx=new Transaction(sender,recipient,amount);
        transactions.add(trx);
        return "Transactions will be added to Block";

    }
    @GetMapping(value = "/getChain")
    @ResponseBody
    public String getChain() {
        return JSONUtil.toJsonStr(blockchain);

    }
    private static String calculateHash(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
