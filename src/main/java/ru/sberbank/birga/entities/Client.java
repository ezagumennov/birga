package ru.sberbank.birga.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Класс инкапсулирующий данные о клиенте
 */
public class Client {
    private static final String SEPARATOR = "\t";

    private String name;
    private int money;
    private Map<Paper, Integer> papers;

    public Client(String name, int money, int aNum, int bNum, int cNum, int dNum){
        this.name = name;
        this.money = money;

        papers = new HashMap<>();
        papers.put(Paper.A, aNum);
        papers.put(Paper.B, bNum);
        papers.put(Paper.C, cNum);
        papers.put(Paper.D, dNum);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Map<Paper, Integer> getPapers() {
        return papers;
    }

    public void setPapers(Map<Paper, Integer> papers) {
        this.papers = papers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return money == client.money &&
                Objects.equals(name, client.name) &&
                Objects.equals(papers, client.papers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money, papers);
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", papers=" + papers +
                '}';
    }
}
