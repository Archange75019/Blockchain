package com.blockchain.blockchain.model;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Blockchain {

    private List<Block> chain;
    private List<Transaction> currentTransactions;

    public Blockchain() {
        chain = new ArrayList<>();
        currentTransactions = new ArrayList<>();
        // Créer le bloc génésis
        createNewBlock(100, "1");
    }

    public Block createNewBlock(int proof, String previousHash) {
        Block block = new Block (
                chain.size() + 1,
                previousHash,
                System.currentTimeMillis(),
                currentTransactions,
                proof
        );
        currentTransactions.clear();
        chain.add(block);
        return block;
    }

    public int createNewTransaction(String sender, String recipient, double amount) {
        Transaction transaction = new Transaction(sender, recipient, amount);
        currentTransactions.add(transaction);
        return getLastBlock().getIndex() + 1;
    }

    public Block getLastBlock() {
        return chain.get(chain.size() - 1);
    }

    public static String hash(Block block) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(block.toString().getBytes(StandardCharsets.UTF_8));
            return String.format("%064x", new BigInteger(1, hash));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validProof(int lastProof, int proof) {
        String guess = lastProof + "" + proof;
        String guessHash = DigestUtils.sha256Hex(guess);
        return guessHash.startsWith("0000");
    }

    public int proofOfWork(int lastProof) {
        int proof = 0;
        while (!validProof(lastProof, proof)) {
            proof++;
        }
        return proof;
    }

    public List<Block> getChain() {
        return chain;
    }

    public List<Transaction> getCurrentTransactions() {
        return currentTransactions;
    }
}
