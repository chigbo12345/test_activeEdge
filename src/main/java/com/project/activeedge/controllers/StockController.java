package com.project.activeedge.controllers;

import com.project.activeedge.exceptions.CustomCheckedException;
import com.project.activeedge.models.Stock;
import com.project.activeedge.models.requests.StockRequest;
import com.project.activeedge.models.responses.ErrorResponse;
import com.project.activeedge.models.responses.SuccessResponse;
import com.project.activeedge.services.StockService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RequestMapping("/api/stocks")
@RestController
public class StockController {
    private StockService stockService;

    @Autowired
    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    @ApiOperation(value = "Get all stocks", response = SuccessResponse.class)
    @GetMapping
    public ResponseEntity<?> getAllStocks(@RequestParam(required = false) Integer pageNo, @RequestParam(required = false) Integer pageSize) {
        return new ResponseEntity<>(new SuccessResponse("Stocks fetched successfully", stockService.getAllStocks(pageNo, pageSize)), HttpStatus.OK);
    }

    @ApiOperation(value = "Get single stock", response = SuccessResponse.class)
    @GetMapping("{id}")
    public ResponseEntity<?> getSingleStock(@PathVariable Long id) throws CustomCheckedException {
        return new ResponseEntity<>(new SuccessResponse("Stock gotten successfully", stockService.getStock(id)), HttpStatus.OK);
    }

    @ApiOperation(value = "Update stock", response = SuccessResponse.class)
    @PutMapping("{id}")
    public ResponseEntity<?> updateStock(@PathVariable Long id, @RequestBody StockRequest editStockRequest) throws CustomCheckedException {
        Stock stock = stockService.editStock(id, editStockRequest);
        if(stock == null)
            return new ResponseEntity<>(new ErrorResponse("A critical error occurred while trying to update stock. Please try again later"), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(new SuccessResponse("Stock updated successfully", stock), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete stock", response = SuccessResponse.class)
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStock(@PathVariable Long id) throws CustomCheckedException {
        boolean isStockDeleted = stockService.deleteStock(id);
        if(!isStockDeleted)
            return new ResponseEntity<>(new ErrorResponse("A critical error occurred while trying to delete stock. Please try again later"), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(new SuccessResponse("Stock deleted successfully", null), HttpStatus.OK);
    }

    @ApiOperation(value = "Create stock", response = SuccessResponse.class)
    @PostMapping
    public ResponseEntity<?> createStock(@Valid @RequestBody StockRequest createStockRequest) throws CustomCheckedException {
        Stock stock = stockService.createStock(createStockRequest);
        if(stock == null)
            return new ResponseEntity<>(new ErrorResponse("A critical error occurred while trying to create stock. Please try again later"), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(new SuccessResponse("Stock created successfully", stock), HttpStatus.OK);
    }
}
