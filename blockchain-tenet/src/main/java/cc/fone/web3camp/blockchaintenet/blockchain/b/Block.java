package cc.fone.web3camp.blockchaintenet.blockchain.b;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Data
public class Block implements Serializable {
    private static final long serialVersionUID = 1L;
    private int nonce;
    private String previousHash;
    private long timeStamp;
    private List<Transaction> transactions;
    private String hash;

    public Block(int nonce, String previousHash, long timeStamp, List<Transaction> transactions) {
        this.nonce = nonce;
        this.previousHash = previousHash;
        this.timeStamp = timeStamp;
        this.transactions = transactions;
        this.hash = calculateHash(this.toString());
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    @Override
    public String toString() {
        return nonce + previousHash + timeStamp + transactions;
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
