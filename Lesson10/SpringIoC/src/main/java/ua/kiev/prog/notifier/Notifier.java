package ua.kiev.prog.notifier;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.kiev.prog.logger.LoggerAPI;

@Component
@Scope("prototype")
public class Notifier {
    private LoggerAPI loggerAPI;

    public Notifier(){}

    public Notifier(LoggerAPI loggerAPI) {
        this.loggerAPI = loggerAPI;
    }

    public void sendSms() {
        try {
            loggerAPI.log("Sending sms...");
            // emulate some job
            Thread.sleep(3000);
            // done
            loggerAPI.log("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
