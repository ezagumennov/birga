package ru.sberbank.birga.entities;

/**
 * Типы ценных бумаг
 */
public enum Paper {
    A("A"), B("B"), C("C"), D("D");

    private String type;

    Paper(String name) {
        this.type = name;
    }

    public void setType(String type) {
        this.type = type;
    }
}
