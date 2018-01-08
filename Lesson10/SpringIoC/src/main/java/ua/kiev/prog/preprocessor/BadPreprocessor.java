package ua.kiev.prog.preprocessor;

public class BadPreprocessor implements Preprocessor {

    @Override
    public String prepare(String msg) {
        return "[Bad] " + msg;
    }
}
