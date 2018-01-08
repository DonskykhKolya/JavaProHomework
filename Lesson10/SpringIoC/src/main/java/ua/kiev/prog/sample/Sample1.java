package ua.kiev.prog.sample;

import ua.kiev.prog.logger.ConsoleLoggerAPI;
import ua.kiev.prog.logger.FileLoggerAPI;
import ua.kiev.prog.logger.LoggerAPI;
import ua.kiev.prog.logger.LoggerType;
import ua.kiev.prog.notifier.Notifier;
import ua.kiev.prog.preprocessor.BadPreprocessor;
import ua.kiev.prog.preprocessor.DatePreprocessor;
import ua.kiev.prog.preprocessor.Preprocessor;

import java.io.IOException;

public class Sample1 {

    private static LoggerType loggerType = LoggerType.File;
    private static boolean usePreprocessors = true;

    public static void show() {
        System.out.println(">>> Sample #1:");

        LoggerAPI api = null;
        if (loggerType == LoggerType.Console)
            api = new ConsoleLoggerAPI();
        else if (loggerType == LoggerType.File)
            api = new FileLoggerAPI("log.txt");

        try {
            api.open();
            try {
                if (usePreprocessors) {
                    Preprocessor datePreprocessor = new DatePreprocessor();
                    api.addPreprocessor(datePreprocessor);
                    Preprocessor badPreprocessor = new BadPreprocessor();
                    api.addPreprocessor(badPreprocessor);
                }
                Notifier notifier = new Notifier(api);
                notifier.sendSms();
            } finally {
                api.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

}
