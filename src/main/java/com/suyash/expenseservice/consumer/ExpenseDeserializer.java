package com.suyash.expenseservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suyash.expenseservice.dto.ExpenseDto;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class ExpenseDeserializer  implements Deserializer<ExpenseDto> {

    @Override
    public void close() {
    }
    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {
    }

    @Override
    public ExpenseDto deserialize(String topic , byte [] arg1){
        ObjectMapper objectMapper = new ObjectMapper();
        ExpenseDto expenseDto = null ;
        try{
            expenseDto = objectMapper.readValue(arg1 , ExpenseDto.class);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return expenseDto;
    }

}
