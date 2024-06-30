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

    private final Blockchain blockchain;

    @Autowired
    public BlockchainController(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    @PostMapping("/transactions/new")
    public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction) {
        int index = blockchain.createNewTransaction(transaction.getSender(), transaction.getRecipient(), transaction.getAmount());
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Transaction will be added to Block " + index);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/mine")
    public ResponseEntity<?> mine() {
        Block lastBlock = blockchain.getLastBlock();
        int lastProof = lastBlock.getProof();
        int proof = blockchain.proofOfWork(lastProof);
        blockchain.createNewTransaction("0", "your-address", 1);
        Block block = blockchain.createNewBlock(proof, Blockchain.hash(lastBlock));
        Map<String, Object> response = new HashMap<>();
        response.put("message", "New Block Forged");
        response.put("index", block.getIndex());
        response.put("transactions", block.getTransactions());
        response.put("proof", block.getProof());
        response.put("previousHash", block.getPreviousHash());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/chain")
    public ResponseEntity<?> getChain() {
        Map<String, Object> response = new HashMap<>();
        response.put("chain", blockchain.getChain());
        response.put("length", blockchain.getChain().size());
        return ResponseEntity.ok(response);
    }
}
