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

    /**
     * It will fetch the single expense details from database
     * @param expenseId - expenseId
     * @return ExpenseDTO
     */

    ExpenseDTO getExpenseByExpenseId(String expenseId);

    /**
     * It will delete the expense from database
     * @param expenseId - expenseId     *
     */

    void deleteExpenseByExpenseId(String expenseId);

    /**
     * It will save the details to database
     * @param expenseDTO - expenseDTO
     * @return ExpenseDTO
     */

    ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO);

    /**
     * It will update the expense details to database
      * @param expenseDTO - expenseDTO
     * @param expenseId - expenseId
     * @return ExpenseDTO
     */

    ExpenseDTO updateExpenseDetails(ExpenseDTO expenseDTO, String expenseId);
}
