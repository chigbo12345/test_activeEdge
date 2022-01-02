package com.project.activeedge.services;

import com.project.activeedge.exceptions.CustomCheckedException;
import com.project.activeedge.models.Stock;
import com.project.activeedge.models.requests.StockRequest;

import java.util.List;

public interface StockService {
    Stock createStock(StockRequest createStockRequest) throws CustomCheckedException;

    Stock editStock(Long stockId, StockRequest editStockRequest) throws CustomCheckedException;

    boolean deleteStock(Long id) throws CustomCheckedException;

    Stock getStock(Long stockId) throws CustomCheckedException;

    List<Stock> getAllStocks(Integer pageNo, Integer pageSize);
}
