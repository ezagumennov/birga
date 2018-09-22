package ru.sberbank.birga.entities;

/**
 * Типы операций
 *  b - покупка
 *  s - продажа
 */
public enum Operation {
    B("b"), S("s");

    private String type;

    Operation(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
