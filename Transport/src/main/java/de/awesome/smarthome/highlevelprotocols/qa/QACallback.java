package de.awesome.smarthome.highlevelprotocols.qa;

/**
 * Created by Beppo on 28.01.2017.
 */
public interface QACallback {
    /**
     * Invoke method
     * @param response Response message
     */
    void invoke(QAMessage response);
}
