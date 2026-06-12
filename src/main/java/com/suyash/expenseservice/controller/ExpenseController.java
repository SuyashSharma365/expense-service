package com.suyash.expenseservice.controller;


import com.suyash.expenseservice.dto.ExpenseDto;
import com.suyash.expenseservice.service.ExpenseService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense/v1")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @GetMapping("/getExpense")
    public ResponseEntity<List<ExpenseDto>>getExpense(@RequestHeader("X-User-Id") @NonNull String userId){
        try{
            List<ExpenseDto> expenseDtoList = expenseService.getExpense(userId);
            return new ResponseEntity<>(expenseDtoList , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null , HttpStatus.OK);
        }
    }
    @PostMapping("/addExpense")
    public ResponseEntity<Boolean>addExpense(@RequestHeader("X-User-Id") @NonNull String userId ,@RequestBody ExpenseDto newExpenseDto){
        try{;
            newExpenseDto.setUserId(userId);
            Boolean result = expenseService.createExpense(newExpenseDto);
            return new ResponseEntity<>(result , HttpStatus.OK);
        } catch (Exception e) {
            return new  ResponseEntity<>(false , HttpStatus.BAD_REQUEST);
        }
    }



}
