package de.silveryard.basesystem.logging;

import java.io.PrintStream;

/**
 * Created by Sebif on 12.04.2017.
 */
class ConsoleLogListener implements ILogListener {
    private final PrintStream outStream;
    private final PrintStream errStream;
    private final StringBuilder stringBuilder;

    public ConsoleLogListener(){
        outStream = System.out;
        errStream = System.err;
        stringBuilder = new StringBuilder();
    }

    @Override
    public void logMessage(String logger, String message, LogMessageType type) {
        stringBuilder.setLength(0);
        stringBuilder.append("[");
        stringBuilder.append(logger);
        stringBuilder.append("]: ");
        stringBuilder.append(message);
        String line = stringBuilder.toString();

        switch (type){
            case OUT:{
                writeLineToStream(line, outStream);
                break;
            }
            case ERROR:{
                writeLineToStream(line, errStream);
                break;
            }
        }
    }

    private void writeLineToStream(String s, PrintStream stream){
        for(int i = 0; i < s.length(); i++){
            stream.write(s.charAt(i));
        }
        stream.write('\n');
    }
}
