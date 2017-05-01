package de.silveryard.basesystem.util;

/**
 * Created by Sebif on 09.03.2017.
 */
public class LRValue<LType, RType> {
     /*
        As java does not allow generic overloaded constructors
        we need to create LRValues via static methods to ensure that
        not both values are being set
     */

    /**
     * Creates a new LRValue with a left value
     * As java does not allow generic overloaded constructors
     * we need to create LRValues via static methods to ensure that
     `* not both values are being set
     * @param lValue Left value
     * @return LRValue instance
     */
    public static <LType, RType> LRValue<LType, RType> createLValue(LType lValue){
        return new LRValue<LType, RType>(lValue, null);
    }
    /**
     * Creates a new LRValue with a right value
     * As java does not allow generic overloaded constructors
     * we need to create LRValues via static methods to ensure that
     * not both values are being set
     * @param rValue Right value
     * @return LRValue instance
     */
    public static <LType, RType> LRValue<LType, RType> createRValue(RType rValue){
        return new LRValue<LType, RType>(null, rValue);
    }

    private LType lValue;
    private RType rValue;

    private LRValue(LType lValue, RType rValue){
        this.lValue = lValue;
        this.rValue = rValue;
    }

    /**
     * Checks if this LRValue has been created with a left value
     * @return True if there is a left value. False otherwise
     */
    public boolean isLValue(){
        return lValue != null;
    }
    /**
     * Checks if this LRValue has been created with a right value
     * @return True if there is a right value. False otherwise
     */
    public boolean isRValue(){
        return rValue != null;
    }

    /**
     * Returns the left value of this LRValue
     * @return Object of type LType when isLValue==true. Null otherwise
     */
    public LType getLValue(){
        return lValue;
    }
    /**
     * Returns the right value of this LRValue
     * @return Object of type RType when isRValue==true. Null otherwise
     */
    public RType getRValue(){
        return rValue;
    }
}
