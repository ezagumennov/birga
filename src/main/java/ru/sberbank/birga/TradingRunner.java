package ru.sberbank.birga;

import org.apache.log4j.Logger;
import ru.sberbank.birga.entities.Client;
import ru.sberbank.birga.entities.Order;
import ru.sberbank.birga.entities.Paper;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class TradingRunner {
    private static final Logger logger = Logger.getLogger(TradingRunner.class);

    private Map<String, Client> clientMap;
    private Map<String, Map<Paper, List<Order>>> buyMap;
    private Map<String, Map<Paper, List<Order>>> sellMap;

    public TradingRunner(Map<String, Client> clientMap,
                         Map<String, Map<Paper, List<Order>>> buyMap,
                         Map<String, Map<Paper, List<Order>>> sellMap
    ) {
        this.clientMap = clientMap;
        this.buyMap = buyMap;
        this.sellMap = sellMap;
    }

    /**
     * Поиск и сопоставление заявок
     */
    public void run(){
        //Для каждой покупки
        buyMap.entrySet().forEach(buyEntry -> {
            String clientName = buyEntry.getKey();
            Map<Paper, List<Order>> clientOrders = buyEntry.getValue();

            //Ищем продажи, исключая покупающего клиента (самому себе продавать нельзя)
            sellMap.entrySet()
                    .stream()
                    .filter(sellEntry -> !sellEntry.getKey().equals(clientName))
                    .forEach(sellEntry -> {
                        String name = sellEntry.getKey();
                        Map<Paper, List<Order>> orders = sellEntry.getValue();

                        //Для каждого типа ценных бумаг сравнивем списки заявок
                        for(Paper p: Paper.values()) {
                            List<Order> buyList = clientOrders.get(p);
                            List<Order> sellList = orders.get(p);
                            matchOrders(buyList, sellList);
                        }
                    });
        });
    }


    /**
     * Сопоставление списков заявок покупки и продаже разных клиентов по одной ценной бумаге
     *
     * @param buyList
     * @param sellList
     */
    private void matchOrders(List<Order> buyList, List<Order> sellList){
        Iterator<Order> buyIter = buyList.iterator();
        Iterator<Order> sellIter = sellList.iterator();

        while(buyIter.hasNext()){
            //Для каждой заявки на покупку
            Order buyOrder = buyIter.next();

            //Ищей подходящую заявку на продажу
            while(sellIter.hasNext()){
                Order sellOrder = sellIter.next();

                //Если цена и количество ценных бумаг совпадает
                if(buyOrder.getPrice() == sellOrder.getPrice() && buyOrder.getNumber() == sellOrder.getNumber()){
                    Client buyClient = clientMap.get(buyOrder.getName());
                    Client sellClient = clientMap.get(sellOrder.getName());
                    Integer orderPaperNumber = buyOrder.getNumber();
                    Paper paper = buyOrder.getPaper();
                    int buyPaperNumber = buyClient.getPapers().get(paper);
                    int sellPaperNumber = sellClient.getPapers().get(paper);

                    //Общее количество денег необходимое для совершения сделки
                    int sum = buyOrder.getPrice() * orderPaperNumber;

                    //Прибавлем продавцу, отнимаем у покупателя
                    buyClient.setMoney(buyClient.getMoney() - sum);
                    sellClient.setMoney(sellClient.getMoney() + sum);

                    //Соответственно изменяем количество ценнной бумаги, по которой идет сделка
                    buyClient.getPapers().put(paper, buyPaperNumber + orderPaperNumber);
                    sellClient.getPapers().put(paper, sellPaperNumber - orderPaperNumber);

                    //Удалем заявки на продажу, как уже обработанные
                    sellIter.remove();
                    buyIter.remove();
                    break;
                }
            }
        }
    }
}
