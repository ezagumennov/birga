package ru.sberbank;

import org.apache.log4j.Logger;
import ru.sberbank.birga.Birga;

/**
 * Запускатор для jar-ника
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Старт приложения");

        new Birga(args).start();
    }
}
