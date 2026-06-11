package com.suyash.expenseservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public class ExpenseDto {
    private String externalId;

    @JsonProperty(value="amount" )
    private BigDecimal amount;

    @JsonProperty(value="user_id" )
    private String userId;

    @JsonProperty(value="merchant" )
    private String merchant;

    @JsonProperty(value="currency" )
    private String currency;

    @JsonProperty(value="created_at" )
    private Timestamp createdAt;


    public ExpenseDto(String json){
        try{
            ObjectMapper mapper = new ObjectMapper();
            ExpenseDto expenseDto = mapper.readValue(json , ExpenseDto.class);

            this.externalId = expenseDto.getExternalId();
            this.amount = expenseDto.getAmount();
            this.createdAt = expenseDto.getCreatedAt();
            this.currency = expenseDto.getCurrency();
            this.merchant =expenseDto.getMerchant();
            this.userId = expenseDto.getUserId();
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize ExpenseDto from JSON",e);
        }
    }

}


