package com.suyash.expenseservice.service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suyash.expenseservice.dto.ExpenseDto;
import com.suyash.expenseservice.entities.Expense;
import com.suyash.expenseservice.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final  ObjectMapper objectMapper;

    public boolean createExpense(ExpenseDto expenseDto){
        try{
            expenseRepository.save(objectMapper.convertValue(expenseDto , Expense.class));
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public List<ExpenseDto> getExpense(String userId){
        List<Expense> expenseOpt = expenseRepository.findByUserId(userId);
        return objectMapper.convertValue(expenseOpt, new TypeReference<List<ExpenseDto>>() {});
    }

    public boolean updateExpense(ExpenseDto newExpense){
        Optional<Expense>oldExpenseOpt = expenseRepository.findByUserIdAndExternalId(newExpense.getUserId() , newExpense.getExternalId());
        if(oldExpenseOpt.isEmpty()){
            return false;
        }
        Expense oldExpense = oldExpenseOpt.get();

        if(newExpense.getAmount() != null){
            oldExpense.setAmount(newExpense.getAmount());
        }
        oldExpense.setCurrency(Strings.isNotBlank(newExpense.getCurrency()) ? newExpense.getCurrency() : oldExpense.getCurrency());
        oldExpense.setMerchant(Strings.isNotBlank(newExpense.getMerchant())? newExpense.getMerchant() : oldExpense.getMerchant());
        expenseRepository.save(oldExpense);
        return true;
    }

}
