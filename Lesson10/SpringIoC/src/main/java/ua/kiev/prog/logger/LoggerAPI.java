package ua.kiev.prog.logger;

import ua.kiev.prog.preprocessor.Preprocessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class LoggerAPI {

    private List<Preprocessor> preprocessors = new ArrayList<>();

    public void addPreprocessor(Preprocessor pre) {
        preprocessors.add(pre);
    }

    public void log(String msg) throws IOException {
        if (preprocessors != null)
            for (Preprocessor preprocessor : preprocessors) {
                msg = preprocessor.prepare(msg);
            }
        doLog(msg);
    }

    protected abstract void doLog(String msg) throws IOException;

    public abstract void open() throws IOException;

    public abstract void close();
}
