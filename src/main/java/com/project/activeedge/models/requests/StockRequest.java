package com.project.activeedge.models.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class StockRequest implements Serializable {
    @NotBlank(message = "Name is required")
    private String name;
    @NotNull(message = "Current price is required")
    private BigDecimal currentPrice;
}
