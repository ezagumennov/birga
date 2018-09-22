package ru.sberbank.birga;

import org.apache.log4j.Logger;
import ru.sberbank.birga.entities.Client;
import ru.sberbank.birga.entities.Order;
import ru.sberbank.birga.entities.Paper;
import ru.sberbank.birga.input.InputFilesProcessor;
import ru.sberbank.birga.input.InputFilesReader;
import ru.sberbank.birga.output.OutputFileProcessor;
import ru.sberbank.birga.output.OutputFileWriter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 */
public class Birga {
    private static final Logger logger = Logger.getLogger(Birga.class);

    private static final String HELP =
            "Запускайте программу следующим образом: java -jar birga-jar-with-dependencies.jar <CLIENTFILE> <ORDERFILE> <RESULTFILE>";

    private String[] args;

    private Map<String, Client> clientMap;
    private Map<String, Map<Paper, List<Order>>> buyMap;
    private Map<String, Map<Paper, List<Order>>> sellMap;

    public Birga(String[] args) {
        this.args = args;

        clientMap = new TreeMap<>();
        buyMap = new HashMap<>();
        sellMap = new HashMap<>();
    }

    public void start(){
        if(args.length != 3){
            logger.error("Неверное количество аргументов!");
            logger.error(HELP);

            return;
        }

        String clientFile = args[0];
        String orderFile  = args[1];
        String outputFile  = args[2];

        new InputFilesReader(clientFile, orderFile, new InputFilesProcessor(clientMap, buyMap, sellMap)).readData();
        new TradingRunner(clientMap, buyMap, sellMap).run();
        new OutputFileWriter(outputFile, new OutputFileProcessor(clientMap)).write();

        logger.info("Заявки успешно обработаны");
    }
}
