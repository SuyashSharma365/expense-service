package com.suyash.expenseservice.consumer;

import com.suyash.expenseservice.dto.ExpenseDto;
import com.suyash.expenseservice.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExpenseConsumer {

    private final ExpenseService expenseService;

    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ExpenseDto expenseDto){
        try{
            expenseService.createExpense(expenseDto);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("AuthServiceConsumer: Exception is thrown while consuming kafka event");
        }
    }
}
