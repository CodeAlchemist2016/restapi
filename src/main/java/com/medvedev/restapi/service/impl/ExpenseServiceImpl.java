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
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * Service implementation for Expense module
 *
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
     *
     * @return list
     */

    @Override
    public List<ExpenseDTO> getAllExpenses() {
        // Call the repository method
        List<ExpenseEntity> list = expenseRepository.findAll();
        log.info("Printing the data from repository {}",
                list);

        // convert the Entity object to DTO object
        List<ExpenseDTO> listOfExpenses = list.stream().map(expenseEntity -> mapToExpenseDTO(expenseEntity)).collect(Collectors.toList());

        // Return the list
        return listOfExpenses;
    }

    /**
     * It will fetch the single expense details from database
     *
     * @param expenseId - expenseId
     * @return ExpenseDTO
     */

    @Override
    public ExpenseDTO getExpenseByExpenseId(String expenseId) {
        ExpenseEntity expenseEntity = getExpenseEntity(expenseId);
        log.info("Printing the expense entity details {}",
                expenseEntity);
        return mapToExpenseDTO(expenseEntity);
    }

    /**
     * It will delete the expense from database
     *
     * @param expenseId - expenseId
     */

    @Override
    public void deleteExpenseByExpenseId(String expenseId) {
        ExpenseEntity expenseEntity = getExpenseEntity(expenseId);
        log.info("Printing the expense entity {}",
                expenseEntity);
        expenseRepository.delete(expenseEntity);
    }

    /**
     * It will save the expense details to the database
     * @param expenseDTO - expenseDTO
     * @return ExpenseDTO
     */

    @Override
    public ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO) {
        ExpenseEntity newExpenseEntity = mapToExpenseEntity(expenseDTO);
        newExpenseEntity.setExpenseId(UUID.randomUUID().toString());
        newExpenseEntity = expenseRepository.save(newExpenseEntity);
        log.info("Printing the new expense entity details {}", newExpenseEntity);
        return mapToExpenseDTO(newExpenseEntity);
    }

    @Override
    public ExpenseDTO updateExpenseDetails(ExpenseDTO expenseDTO,
                                           String expenseId) {
        ExpenseEntity existingExpense = getExpenseEntity(expenseId);
        ExpenseEntity updatedExpenseEntity = mapToExpenseEntity(expenseDTO);
        updatedExpenseEntity.setId(existingExpense.getId());
        updatedExpenseEntity.setExpenseId(existingExpense.getExpenseId());
        updatedExpenseEntity.setCreatedAt(existingExpense.getCreatedAt());
        updatedExpenseEntity.setUpdatedAt(existingExpense.getUpdatedAt());
        updatedExpenseEntity = expenseRepository.save(updatedExpenseEntity);
        log.info("Printing the updated expense entity details {}", updatedExpenseEntity);
        return mapToExpenseDTO(updatedExpenseEntity);
    }

    /**
     * Mapper method to map values from ExpenseDTO to ExpenseEntity
     * @param expenseDTO = expenseDTO
     * @return ExpenseEntity
     */

    private ExpenseEntity mapToExpenseEntity(ExpenseDTO expenseDTO) {
        return modelMapper.map(expenseDTO,
                ExpenseEntity.class);
    }

    /**
     * Mapper method to convert expense entity to expense DTO
     *
     * @param expenseEntity - expenseEntity
     * @return expenseDTO
     */

    private ExpenseDTO mapToExpenseDTO(ExpenseEntity expenseEntity) {
        return modelMapper.map(expenseEntity,
                ExpenseDTO.class);
    }

    /**
     * Fetch the expense by expense id from database
     *
     * @param expenseId - expenseId
     * @return ExpenseEntity
     */

    private ExpenseEntity getExpenseEntity(String expenseId) {
        return expenseRepository.findByExpenseId(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found for the expense id " + expenseId));
    }
}
