package ru.sberbank.birga.output;

import org.apache.log4j.Logger;
import ru.sberbank.birga.entities.Client;
import ru.sberbank.birga.util.ParseUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

/**
 *
 */
public class OutputFileProcessor {
    private static final Logger logger = Logger.getLogger(OutputFileProcessor.class);

    private  Map<String, Client> clientMap;

    public OutputFileProcessor(Map<String, Client> clientMap) {
        this.clientMap = clientMap;
    }

    public void process(BufferedWriter writer) {
        try {
            for (Client client : clientMap.values()) {
                writer.write(ParseUtil.clientToString(client));
                writer.newLine();
            }
            logger.info(String.format("%d клиента(ов) было успешно записано", clientMap.size()));
        } catch (IOException e) {
            logger.error("Ошибка записи данных", e);
        }
    }
}
