package de.awesome.smarthome.td.util;

/**
 * Created by Sebif on 18.03.2017.
 */
@FunctionalInterface
public interface ActionP1<P1> {
    void invoke(P1 param);
}
