package ua.kiev.prog.sample;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.kiev.prog.notifier.Notifier;

public class Sample3 {

    public static void show() {
        System.out.println(">>> Sample #3:");

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-config.xml");
        try {
            Notifier notifier = ctx.getBean("notifier", Notifier.class);
            notifier.sendSms();
        } finally {
            ctx.close();
        }
    }
}
