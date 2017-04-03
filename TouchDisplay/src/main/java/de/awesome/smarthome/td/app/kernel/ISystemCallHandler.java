package de.awesome.smarthome.td.app.kernel;

import de.awesome.smarthome.highlevelprotocols.qa.QAMessage;
import de.awesome.smarthome.td.app.RunningApp;

/**
 * Created by Sebif on 13.03.2017.
 */
@FunctionalInterface
public interface ISystemCallHandler {
    QAMessage invoke(RunningApp app, QAMessage message);
}
