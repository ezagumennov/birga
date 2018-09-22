package ru.sberbank.birga.entities;

import java.util.Objects;

/**
 * Класс инкапсулирующий данные о заявке
 */
public class Order {
    private static final String SEPARATOR = "\t";

    private String name;
    private Operation operation;
    private Paper paper;
    private int price;
    private int number;


    public Order(String name, Operation operation, Paper paper, int price, int number) {
        this.name = name;
        this.operation = operation;
        this.paper = paper;
        this.price = price;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return price == order.price &&
                number == order.number &&
                Objects.equals(name, order.name) &&
                operation == order.operation &&
                paper == order.paper;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, operation, paper, price, number);
    }
}
