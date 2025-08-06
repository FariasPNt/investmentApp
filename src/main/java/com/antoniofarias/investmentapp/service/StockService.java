package com.antoniofarias.investmentapp.service;

import com.antoniofarias.investmentapp.dto.CreateStockDto;
import com.antoniofarias.investmentapp.entity.Stock;
import com.antoniofarias.investmentapp.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void createStock(CreateStockDto createStockDto) {

        // dto -> entity
        var stock = new Stock(
                createStockDto.stockId(),
                createStockDto.description()
        );

        stockRepository.save(stock);
    }
}
