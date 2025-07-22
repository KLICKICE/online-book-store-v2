package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Product {
    private final String name;
    private final int price;
    private final String category;

    public Product(String name, int price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    /**
     * Повертає мапу: ключ — назва категорії, значення — список назв продуктів
     * цієї категорії, відсортований за зростанням ціни.
     */
    public static Map<String, List<String>> groupByCategorySortedByPrice(List<Product> products) {
        return products.stream()
                // 1. Сортуємо весь стрім за price, щоб порядок уже був потрібний
                .sorted(Comparator.comparingInt(Product::getPrice))
                // 2. Групуємо за category
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        // якщо бажаєш зберегти порядок додавання категорій — LinkedHashMap
                        LinkedHashMap::new,
                        // 3. Перетворюємо Product → лише name, збираємо у List
                        Collectors.mapping(Product::getName, Collectors.toList())
                ));
    }
}
