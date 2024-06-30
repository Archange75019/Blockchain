package com.blockchain.blockchain.controller;

import com.blockchain.blockchain.model.Block;
import com.blockchain.blockchain.model.Blockchain;
import com.blockchain.blockchain.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BlockchainController {

    private Blockchain blockchain;

    @Autowired
    public BlockchainController() {
        this.blockchain = new Blockchain();
    }

    @PostMapping("/transactions/new")
    public ResponseEntity<String> newTransaction(@RequestBody Transaction transaction) {
        int index = blockchain.createNewTransaction(transaction.getSender(), transaction.getRecipient(), transaction.getAmount());
        return ResponseEntity.ok("Transaction will be added to Block " + index);
    }

    @GetMapping("/mine")
    public ResponseEntity<Block> mine() {
        Block lastBlock = blockchain.getLastBlock();
        int lastProof = lastBlock.getProof();
        int proof = blockchain.proofOfWork(lastProof);

        blockchain.createNewTransaction("0", "node-identifier", 1);

        String previousHash = Blockchain.hash(lastBlock);
        Block block = blockchain.createNewBlock(proof, previousHash);

        return ResponseEntity.ok(block);
    }

    @GetMapping("/chain")
    public ResponseEntity<Map<String, Object>> fullChain() {
        Map<String, Object> response = new HashMap<>();
        response.put("chain", blockchain.getChain());
        response.put("length", blockchain.getChain().size());
        return ResponseEntity.ok(response);
    }
}
