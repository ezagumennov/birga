package ru.sberbank.birga.input;

import org.apache.log4j.Logger;

import ru.sberbank.birga.entities.Client;
import ru.sberbank.birga.entities.Operation;
import ru.sberbank.birga.entities.Order;
import ru.sberbank.birga.entities.Paper;
import ru.sberbank.birga.util.ParseUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 *
 */
public class InputFilesProcessor {
    private static final Logger logger = Logger.getLogger(InputFilesProcessor.class);

    private Map<String, Client> clientMap;
    private Map<String, Map<Paper, List<Order>>> buyMap;
    private Map<String, Map<Paper, List<Order>>> sellMap;

    public InputFilesProcessor(Map<String, Client> clientMap,
                               Map<String, Map<Paper, List<Order>>> buyMap,
                               Map<String, Map<Paper, List<Order>>> sellMap
    ) {
        this.clientMap = clientMap;
        this.buyMap = buyMap;
        this.sellMap = sellMap;
    }

    /**
     * Создаем структуру {Клиент -> {Акция -> [Список заявок]}}
     *
     * @param clientStream
     * @param orderStream
     */
    public void process(Stream<String> clientStream, Stream<String> orderStream) {
        clientStream.forEach(clientStr -> {
            Client client = ParseUtil.parseClient(clientStr);
            clientMap.put(client.getName(), client);
        });

        orderStream.forEach(orderStr -> {
            Order order = ParseUtil.parseOrder(orderStr);

            if(order.getOperation() == Operation.B){
                putToMap(buyMap, order);
            } else {
                putToMap(sellMap, order);
            }
        });
    }

    private void putToMap(Map<String, Map<Paper, List<Order>>> map, Order order){
        if(map.get(order.getName()) == null){
            Map<Paper, List<Order>> paperOrderMap = new HashMap<>();

            for(Paper p: Paper.values()) {
                paperOrderMap.put(p, new ArrayList<>());
            }

            map.put(order.getName(), paperOrderMap);
        }

        map.get(order.getName())
                .get(order.getPaper())
                .add(order);
    }
}
