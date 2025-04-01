package com.medvedev.restapi.service.impl;

import com.medvedev.restapi.dto.ExpenseDTO;
import com.medvedev.restapi.entity.ExpenseEntity;
import com.medvedev.restapi.exceptions.ResourceNotFoundException;
import com.medvedev.restapi.repository.ExpenseRepository;
import com.medvedev.restapi.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Service implementation for Expense module
 * @author Eugene
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ModelMapper modelMapper;

    /**
     * It will fetch the expenses from database
     * @return list
     */

    @Override
    public List<ExpenseDTO> getAllExpenses() {
        // Call the repository method
        List<ExpenseEntity> list = expenseRepository.findAll();
        log.info("Printing the data from repository {}", list);

        // convert the Entity object to DTO object
        List<ExpenseDTO> listOfExpenses =  list.stream().map(expenseEntity -> mapToExpenseDTO(expenseEntity)).collect(Collectors.toList());

        // Return the list
        return listOfExpenses;
    }

    /**
     * It will fetch the single expense details from database
     * @param expenseId
     * @return ExpenseDTO
     */

    @Override
    public ExpenseDTO getExpenseByExpenseId(String expenseId) {
        ExpenseEntity expenseEntity = expenseRepository.findByExpenseId(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found for the expense id " + expenseId));

        log.info("Printing the expense entity details {}", expenseEntity);
        return mapToExpenseDTO(expenseEntity);
    }

    /**
     * Mapper method to convert expense entity to expense DTO
     * @param expenseEntity
     * @return expenseDTO
     */

    private ExpenseDTO mapToExpenseDTO(ExpenseEntity expenseEntity) {
        return modelMapper.map(expenseEntity, ExpenseDTO.class);
    }
}
