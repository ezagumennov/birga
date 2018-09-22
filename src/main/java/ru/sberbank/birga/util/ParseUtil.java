package ru.sberbank.birga.util;

import ru.sberbank.birga.entities.Client;
import ru.sberbank.birga.entities.Operation;
import ru.sberbank.birga.entities.Order;
import ru.sberbank.birga.entities.Paper;

import java.util.Map;

public class ParseUtil {

    private static final String SEPARATOR = "\t";

    public static Client parseClient(String str){
        String[] subStrs = str.split(SEPARATOR);

        return new Client(subStrs[0],
                Integer.parseInt(subStrs[1]),
                Integer.parseInt(subStrs[2]),
                Integer.parseInt(subStrs[3]),
                Integer.parseInt(subStrs[4]),
                Integer.parseInt(subStrs[5]));
    }

    public static String clientToString(Client client) {
        Map<Paper, Integer> papers = client.getPapers();

        return client.getName() + SEPARATOR +
                client.getMoney() + SEPARATOR +
                papers.get(Paper.A) + SEPARATOR +
                papers.get(Paper.B) + SEPARATOR +
                papers.get(Paper.C) + SEPARATOR +
                papers.get(Paper.D);
    }

    public static Operation parseOperation(String str) {
        switch (str) {
            case "b": return Operation.B;
            case "s": return Operation.S;
        }

        return null;
    }


    public static Order parseOrder(String str){
        String[] subStrs = str.split(SEPARATOR);

        return new Order(subStrs[0],
                ParseUtil.parseOperation(subStrs[1]),
                ParseUtil.parsePaper(subStrs[2]),
                Integer.parseInt(subStrs[3]),
                Integer.parseInt(subStrs[4]));
    }


    public static Paper parsePaper(String str){
        switch (str){
            case "A": return Paper.A;
            case "B": return Paper.B;
            case "C": return Paper.C;
            case "D": return Paper.D;
        }

        return null;
    }
}
