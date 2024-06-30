package com.blockchain.blockchain.service;

import com.blockchain.blockchain.model.Block;
import com.blockchain.blockchain.model.Transaction;
import com.blockchain.blockchain.repository.BlockRepository;
import com.blockchain.blockchain.repository.TransactionRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Blockchaine {
    private final BlockRepository blockRepository;
    private final TransactionRepository transactionRepository;

    private List<Transaction> currentTransactions;

    @Autowired
    public Blockchaine(BlockRepository blockRepository, TransactionRepository transactionRepository) {
        this.blockRepository = blockRepository;
        this.transactionRepository = transactionRepository;
        this.currentTransactions = new ArrayList<>();
        // Créer le bloc génésis
        createNewBlock(100, "1");
    }

    public Block createNewBlock(int proof, String previousHash) {
        Block block = new Block(
                (int) (blockRepository.count() + 1),
                System.currentTimeMillis(),
                new ArrayList<>(currentTransactions),
                proof,
                previousHash
        );
        currentTransactions.clear();
        blockRepository.save(block);
        return block;
    }

    public int createNewTransaction(String sender, String recipient, double amount) {
        Transaction transaction = new Transaction(sender, recipient, amount);
        transactionRepository.save(transaction);
        currentTransactions.add(transaction);
        return getLastBlock().getIndex() + 1;
    }

    public Block getLastBlock() {
        List<Block> blocks = (List<Block>) blockRepository.findAll();
        return blocks.get(blocks.size() - 1);
    }

    public List<Block> getChain() {
        return (List<Block>) blockRepository.findAll();
    }

    public int proofOfWork(int lastProof) {
        int proof = 0;
        while (!isValidProof(lastProof, proof)) {
            proof++;
        }
        return proof;
    }

    private boolean isValidProof(int lastProof, int proof) {
        String guess = lastProof + "" + proof;
        String guessHash = DigestUtils.sha256Hex(guess);
        return guessHash.startsWith("0000");
    }

    public static String hash(Block block) {
        return DigestUtils.sha256Hex(block.toString());
    }
}
