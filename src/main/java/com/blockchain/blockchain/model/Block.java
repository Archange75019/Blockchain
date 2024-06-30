package com.blockchain.blockchain.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int index;
    private long timestamp;
    private int proof;
    private String previousHash;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Transaction> transactions;

    public Block(int i, String previousHash, long l, List<Transaction> currentTransactions, int proof) {
    }
}
