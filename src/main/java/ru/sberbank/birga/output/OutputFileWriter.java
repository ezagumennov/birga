package ru.sberbank.birga.output;

import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 */
public class OutputFileWriter {
    private static final Logger logger = Logger.getLogger(OutputFileWriter.class);

    private String outputFileName;
    private OutputFileProcessor outputFileProcessor;

    public OutputFileWriter(String outputFileName, OutputFileProcessor outputFileProcessor) {
        this.outputFileName = outputFileName;
        this.outputFileProcessor = outputFileProcessor;
    }

    public void write() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFileName))) {
            outputFileProcessor.process(writer);
        } catch (IOException e){
            logger.error("Ошибка при открытии файла", e);
        }
    }
}
