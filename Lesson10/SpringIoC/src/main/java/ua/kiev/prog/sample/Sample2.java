package ua.kiev.prog.sample;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.kiev.prog.config.AppConfig;
import ua.kiev.prog.notifier.Notifier;

public class Sample2 {

    public static void show() {

        System.out.println(">>> Sample #2:");

        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        try {
            Notifier notifier1 = ctx.getBean("notifier1", Notifier.class);
            notifier1.sendSms();

            Notifier notifier2 = ctx.getBean("notifier2", Notifier.class);
            notifier2.sendSms();

        } finally {
            ctx.close();
        }
    }
}
