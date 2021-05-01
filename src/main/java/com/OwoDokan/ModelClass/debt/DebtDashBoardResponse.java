package com.OwoDokan.ModelClass.debt;

import lombok.Data;

import java.io.Serializable;

@Data
public class DebtDashBoardResponse implements Serializable {
    private Double totalLoan;
    private Double totalPaid;
}
