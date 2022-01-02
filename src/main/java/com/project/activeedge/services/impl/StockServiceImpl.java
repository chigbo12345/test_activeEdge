package com.project.activeedge.services.impl;

import com.project.activeedge.exceptions.CustomCheckedException;
import com.project.activeedge.models.Stock;
import com.project.activeedge.models.requests.StockRequest;
import com.project.activeedge.repositories.StockRepository;
import com.project.activeedge.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {
    private StockRepository stockRepository;

    @Autowired
    public void setStockRepository(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock createStock(StockRequest createStockRequest) throws CustomCheckedException {
        if(createStockRequest.getCurrentPrice().compareTo(BigDecimal.ZERO) <= 0)
            throw new CustomCheckedException("Current price must be a value greater than zero (0)");
        Stock stock = new Stock(createStockRequest.getName(), createStockRequest.getCurrentPrice());
        return stockRepository.save(stock);
    }

    @Override
    public Stock getStock(Long stockId) throws CustomCheckedException {
        Optional<Stock> stock = stockRepository.findById(stockId);
        if(!stock.isPresent())
            throw new CustomCheckedException(String.format("Stock with ID %d does not exist", stockId));
        return stock.get();
    }

    @Override
    public Stock editStock(Long stockId, StockRequest editStockRequest) throws CustomCheckedException {
        Stock stock = this.getStock(stockId);
        if(editStockRequest.getName() != null && !editStockRequest.getName().isEmpty())
            stock.setName(editStockRequest.getName());
        if(editStockRequest.getCurrentPrice() != null && editStockRequest.getCurrentPrice().compareTo(BigDecimal.ZERO) > 0)
            stock.setCurrentPrice(editStockRequest.getCurrentPrice());
        return stockRepository.save(stock);
    }

    @Override
    public boolean deleteStock(Long id) throws CustomCheckedException {
        Stock stock = this.getStock(id);
        stockRepository.delete(stock);
        try {
            this.getStock(id);
            return false;
        }catch (CustomCheckedException ignored) {
            return true;
        }
    }

    @Override
    public List<Stock> getAllStocks(Integer pageNo, Integer pageSize) {
        if(pageNo == null) pageNo = 0;
        if(pageSize == null) pageSize = 100;
        return stockRepository.findAll(PageRequest.of(pageNo, pageSize)).getContent();
    }
}
