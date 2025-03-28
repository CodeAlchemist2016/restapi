package com.medvedev.restapi.service;

import com.medvedev.restapi.dto.ExpenseDTO;

import java.util.List;

/**
 * Service interface for Expense module
 * @author Eugene
 */

public interface ExpenseService {

    /**
     * It will fetch the expenses from database
     * @return list
     */

    List<ExpenseDTO> getAllExpenses();
}
