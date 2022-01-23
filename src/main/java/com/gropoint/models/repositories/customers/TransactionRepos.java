package com.gropoint.models.repositories.customers;

import com.gropoint.models.entities.customers.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepos extends JpaRepository<Transaction, Long> {

}
