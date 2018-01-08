package ua.kiev.prog.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.kiev.prog.preprocessor.BadPreprocessor;
import ua.kiev.prog.preprocessor.DatePreprocessor;
import ua.kiev.prog.notifier.Notifier;
import ua.kiev.prog.preprocessor.Preprocessor;
import ua.kiev.prog.logger.ConsoleLoggerAPI;
import ua.kiev.prog.logger.FileLoggerAPI;
import ua.kiev.prog.logger.LoggerAPI;

@Configuration
public class AppConfig {
    // comment out this bean to test the result
    @Bean
    public Preprocessor datePreprocessor() {
        return new DatePreprocessor();
    }

    @Bean
    public Preprocessor badPreprocessor() {
        return new BadPreprocessor();
    }

    @Bean(initMethod = "open", destroyMethod = "close")
    public LoggerAPI fileLoggerAPI() {
        return new FileLoggerAPI("log.txt");
    }

    @Bean(name = "consoleLogger")
    public LoggerAPI consoleLoggerAPI() {
        return new ConsoleLoggerAPI();
    }

    @Bean(name = "notifier1")
    public Notifier notifier1(@Qualifier("fileLoggerAPI") LoggerAPI api) {
        return new Notifier(api);
    }

    @Bean(name = "notifier2")
    public Notifier notifier2(@Qualifier("consoleLogger") LoggerAPI api) {
        return new Notifier(api);
    }
}
