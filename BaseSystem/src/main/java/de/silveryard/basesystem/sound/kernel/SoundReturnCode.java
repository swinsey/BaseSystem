package de.silveryard.basesystem.sound.kernel;

/**
 * Created by Sebif on 06.04.2017.
 */
public enum SoundReturnCode {
        OK(1),
        UNKNOWN_ERROR(2),
        PATH_NOT_EXISTENT(3),
        PATH_NO_FILE(4),
        INVALID_ID(5);

        private int value;

        SoundReturnCode(int value){
            this.value = value;
        }

        public int getValue(){
            return value;
        }
}
