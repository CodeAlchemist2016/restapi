package com.medvedev.restapi.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseResponse {
    private String expense_id;
    private String name;
    private String note;
    private String category;
    private Date date;
    private BigDecimal amount;
    private Timestamp created_at;
    private Timestamp updated_at;
}
