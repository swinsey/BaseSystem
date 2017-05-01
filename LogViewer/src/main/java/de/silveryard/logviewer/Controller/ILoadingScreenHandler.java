package de.silveryard.logviewer.Controller;

import de.silveryard.logviewer.Utility.IActionP1;

/**
 * Created by Sebif on 11.04.2017.
 */
@FunctionalInterface
public interface ILoadingScreenHandler {
    void invoke(IActionP1<String> statusSetter);
}