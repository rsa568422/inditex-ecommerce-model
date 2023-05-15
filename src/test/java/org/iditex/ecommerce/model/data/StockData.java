package org.iditex.ecommerce.model.data;

import org.iditex.ecommerce.model.entities.Stock;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StockData {

    public static final Map<Long, Stock> STOCKS = Stream.of(
            new Stock(11L, 0L),
            new Stock(12L, 0L),
            new Stock(13L, 0L),
            new Stock(22L, 0L),
            new Stock(31L, 10L),
            new Stock(32L, 10L),
            new Stock(33L, 10L),
            new Stock(41L, 0L),
            new Stock(42L, 0L),
            new Stock(43L, 0L),
            new Stock(44L, 10L),
            new Stock(51L, 10L),
            new Stock(52L, 10L),
            new Stock(53L, 10L),
            new Stock(54L, 10L)
    ).collect(Collectors.groupingBy(Stock::getSizeId, Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0))));

}
