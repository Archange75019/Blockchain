package com.blockchain.blockchain.repository;

import com.blockchain.blockchain.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
