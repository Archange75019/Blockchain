package com.blockchain.blockchain.repository;

import com.blockchain.blockchain.model.Block;
import org.springframework.data.repository.CrudRepository;

public interface BlockRepository extends CrudRepository<Block, Long> {
}
