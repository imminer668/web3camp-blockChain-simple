package cc.fone.web3camp.blockchaintenet.blockchain.b;

import lombok.Data;

import java.io.Serializable;

@Data
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sender;
    private String recipient;
    private int amount;

    public Transaction(String sender, String recipient, int amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return sender + recipient + amount;
    }
}

