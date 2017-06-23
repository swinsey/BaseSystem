package de.silveryard.basesystem.libdisplayjava;

/**
 * Created by Sebif on 23.06.2017.
 */
public class ScreenSize {
    public int width;
    public int height;

    @Override
    public boolean equals(Object other){
        if(other == null){
            return false;
        }
        if(!(other instanceof ScreenSize)){
            return false;
        }
        if(other == this){
            return true;
        }

        ScreenSize sOther = (ScreenSize)other;
        return this.width == sOther.width &&
                this.height == sOther.height;
    }
    @Override
    public String toString(){
        return "{ " + width + ", " + height + " }";
    }
}
