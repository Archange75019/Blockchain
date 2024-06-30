package com.blockchain.blockchain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Block {
    private int index;
    private long timestamp;
    private List<Transaction> transactions;
    private int proof;
    private String previousHash;
}
