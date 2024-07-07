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
public class BlockchainService {
    private List<Block> chain;

    private final BlockRepository blockRepository;
    private final TransactionRepository transactionRepository;

    private List<Transaction> currentTransactions;

    @Autowired
    public BlockchainService(BlockRepository blockRepository, TransactionRepository transactionRepository) {
        this.blockRepository = blockRepository;
        this.transactionRepository = transactionRepository;
        this.currentTransactions = new ArrayList<>();
        this.chain = new ArrayList<>();
        // Create the genesis block
        createNewBlock(100, "1");
    }

    public Block createNewBlock(int proof, String previousHash) {
        Block block = new Block(
                chain.size() + 1,
                previousHash,
                System.currentTimeMillis(),
                new ArrayList<>(currentTransactions),
                proof
        );
        currentTransactions.clear();
        chain.add(block);
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
        return chain.get(chain.size() - 1);
    }

    public List<Block> getChain() {
        return chain;
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

    public Block mineBlock(String minerAddress) {
        int lastProof = getLastBlock().getProof();
        int proof = proofOfWork(lastProof);

        createNewTransaction("0", minerAddress, 1);

        Block lastBlock = getLastBlock();
        Block newBlock = createNewBlock(proof, hash(lastBlock));
        return newBlock;
    }

    public static String hash(Block block) {
        return DigestUtils.sha256Hex(block.toString());
    }
}
