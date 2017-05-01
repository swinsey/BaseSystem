package de.silveryard.appmanager.Controller;

import de.silveryard.appmanager.Utility.IActionP1;

/**
 * Created by Sebif on 18.03.2017.
 */
@FunctionalInterface
public interface ILoadingScreenHandler {
    void invoke(IActionP1<String> statusSetter);
}
