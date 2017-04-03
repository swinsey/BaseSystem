package de.awesome.smarthome.appmanager.Controller;

import de.awesome.smarthome.appmanager.Utility.IActionP1;

/**
 * Created by Sebif on 18.03.2017.
 */
@FunctionalInterface
public interface ILoadingScreenHandler {
    void invoke(IActionP1<String> statusSetter);
}
