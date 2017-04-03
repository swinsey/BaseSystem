package de.awesome.smarthome.td.util;

/**
 * Created by Sebif on 09.03.2017.
 */
public class LRValue<LType, RType> {
     /*
        As java does not allow generic overloaded constructors
        we need to create LRValues via static methods to ensure that
        not both values are being set
     */

    public static <LType, RType> LRValue<LType, RType> createLValue(LType lValue){
        return new LRValue<LType, RType>(lValue, null);
    }
    public static <LType, RType> LRValue<LType, RType> createRValue(RType rValue){
        return new LRValue<LType, RType>(null, rValue);
    }

    private LType lValue;
    private RType rValue;

    private LRValue(LType lValue, RType rValue){
        this.lValue = lValue;
        this.rValue = rValue;
    }

    public boolean isLValue(){
        return lValue != null;
    }
    public boolean isRValue(){
        return rValue != null;
    }

    public LType getLValue(){
        return lValue;
    }
    public RType getRValue(){
        return rValue;
    }
}
