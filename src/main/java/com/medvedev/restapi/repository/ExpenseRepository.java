package com.medvedev.restapi.repository;

import com.medvedev.restapi.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JPA repository for Expense resource
 * @author Eugene M
 */

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    /**
     * It will find the single expense from database
     * @param expenseId
     * @return Optioinal
     */

    Optional<ExpenseEntity> findByExpenseId(String expenseId);
}
