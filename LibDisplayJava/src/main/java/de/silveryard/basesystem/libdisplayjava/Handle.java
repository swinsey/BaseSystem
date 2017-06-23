package de.silveryard.basesystem.libdisplayjava;

/**
 * Hope for the best that long maps to native long
 *
 * Created by Sebif on 21.06.2017.
 */
public final class Handle {
    public long data1;
    public long data2;
    public long data3;
    public long data4;

    public Handle(){
        data1 = 0;
        data2 = 0;
        data3 = 0;
        data4 = 0;
    }

    @Override
    public boolean equals(Object other){
        if(other == null){
            return false;
        }
        if(!(other instanceof Handle)){
            return false;
        }
        if(other == this){
            return true;
        }

        return Display.compareHandles(this, (Handle)other);
    }
    @Override
    public String toString(){
        return "{ "+ data1 + ", " + data2 + ", " + data3 + ", " + data4 + " }";
    }
};