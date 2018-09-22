package ru.sberbank.birga.input;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 *
 */
public class InputFilesReader {
    private static final Logger logger = Logger.getLogger(InputFilesReader.class);

    private String clientFile;
    private String orderFile;
    private InputFilesProcessor inputFilesProcessor;

    public InputFilesReader(String clientFile, String orderFile, InputFilesProcessor inputFilesProcessor) {
        this.clientFile = clientFile;
        this.orderFile = orderFile;
        this.inputFilesProcessor = inputFilesProcessor;
    }

    public void readData() {
        try(Stream<String> clientsStream = Files.lines(Paths.get(clientFile));
            Stream<String> ordersStream  = Files.lines(Paths.get(orderFile)))
        {
           inputFilesProcessor.process(clientsStream, ordersStream);
           logger.info("Клиенты и заявки были успешно прочитаны");
        } catch(IOException e) {
            logger.error("Ошибка чтения данных", e);
        }
    }
}
