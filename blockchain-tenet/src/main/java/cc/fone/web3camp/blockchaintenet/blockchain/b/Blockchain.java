package cc.fone.web3camp.blockchaintenet.blockchain.b;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
public class Blockchain  implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Block> chain;


    public Blockchain() {
        this.chain = new ArrayList<>();
        createGenesisBlock();
    }

    private void createGenesisBlock() {
        int nonce = 0;
        String previousHash = "00000000000000000000000000000000";
        long timeStamp = new Date().getTime();
        List<Transaction> transactions = new ArrayList<>();

        Block genesisBlock = new Block(nonce, previousHash, timeStamp, transactions);
        chain.add(genesisBlock);
    }

    public void addBlock(Block block) {
        chain.add(block);
    }

    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            // 验证当前区块的哈希值是否正确
            String currentBlockData = currentBlock.toString();
            String currentBlockHash = calculateHash(currentBlockData);
            if (!currentBlock.getHash().equals(currentBlockHash)) {
                return false;
            }

            // 验证当前区块的前一个哈希值是否与上一个区块的哈希值相等
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
        }

        return true;
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

