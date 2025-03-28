package com.medvedev.restapi.repository;

import com.medvedev.restapi.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {
}
