package com.blockchain.blockchain.controller;

import com.blockchain.blockchain.model.Block;
import com.blockchain.blockchain.model.Transaction;
import com.blockchain.blockchain.service.BlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlockchainController {

    private final BlockchainService blockchainService;

    @Autowired
    public BlockchainController(BlockchainService blockchainService) {
        this.blockchainService = blockchainService;
    }

    @PostMapping("/transactions")
    public int createTransaction(@RequestBody Transaction transaction) {
        return blockchainService.createNewTransaction(transaction.getSender(), transaction.getRecipient(), transaction.getAmount());
    }

    @GetMapping("/chain")
    public List<Block> getBlockchain() {
        return blockchainService.getChain();
    }

    @PostMapping("/mine")
    public Block mineBlock(@RequestParam String minerAddress) {
        return blockchainService.mineBlock(minerAddress);
    }
}
