package org.iditex.ecommerce.model.utils;

import org.iditex.ecommerce.model.entities.Product;
import org.iditex.ecommerce.model.entities.Size;
import org.iditex.ecommerce.model.entities.Stock;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Data {

    public static final List<Product> PRODUCTS = Stream.of(
            new Product(1L, 10L),
            new Product(2L, 7L),
            new Product(3L, 15L),
            new Product(4L, 13L),
            new Product(5L, 6L)
    ).collect(Collectors.toList());

    public static final Map<Long, List<Size>> SIZES = Stream.of(
            new Size(11L, 1L, true, false),
            new Size(12L, 1L, false, false),
            new Size(13L, 1L, true, false),
            new Size(21L, 2L, false, false),
            new Size(22L, 2L, false, false),
            new Size(23L, 2L, true, true),
            new Size(31L, 3L, true, false),
            new Size(32L, 3L, true, false),
            new Size(33L, 3L, false, false),
            new Size(41L, 4L, false, false),
            new Size(42L, 4L, false, false),
            new Size(43L, 4L, false, false),
            new Size(44L, 4L, true, true),
            new Size(51L, 5L, true, false),
            new Size(52L, 5L, false, false),
            new Size(53L, 5L, false, false),
            new Size(54L, 5L, true, true)
    ).collect(Collectors.groupingBy(Size::getProductId));

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
    ).collect(Collectors.groupingBy(Stock::getSizeId, Collectors.collectingAndThen(Collectors.toList(), stocks -> stocks.stream().findFirst().orElse(null))));

}
