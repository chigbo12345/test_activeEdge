package com.project.activeedge.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Stock {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(nullable = false)
    private String name;
    private BigDecimal currentPrice;
    @Column(nullable = false)
    private Date createDate;
    @Version
    private Date lastUpdate;

    public Stock(String name, BigDecimal currentPrice) {
        this.name = name;
        this.currentPrice = currentPrice;
        this.createDate = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return getId().equals(stock.getId())
                && getName().equals(stock.getName())
                && Objects.equals(getCurrentPrice(), stock.getCurrentPrice())
                && getCreateDate().equals(stock.getCreateDate())
                && Objects.equals(getLastUpdate(), stock.getLastUpdate());
    }



    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCurrentPrice(), getCreateDate(), getLastUpdate());
    }
}
