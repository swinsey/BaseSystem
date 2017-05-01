package de.silveryard.basesystem.sound.kernel;

/**
 * Created by Sebif on 06.04.2017.
 */
public enum SoundReturnCode {
        /**
         * Everything went fint
         */
        OK(1),
        /**
         * Some unknown error occured.
         * Will also be set when FmodResult is not FmodResult.OK
         */
        UNKNOWN_ERROR(2),
        /**
         * A given path does not exist
         */
        PATH_NOT_EXISTENT(3),
        /**
         * A given path is no file
         */
        PATH_NO_FILE(4),
        /**
         * A given id is not valid in the given context
         */
        INVALID_ID(5);

        /**
         * Converts an integer value to an enum value
         * @param value Integer value
         * @return Enum value
         */
        public static SoundReturnCode getEnumValue(int value){
                SoundReturnCode[] values = SoundReturnCode.values();
                for(int i = 0; i < values.length; i++){
                        if(values[i].getValue() == value){
                                return values[i];
                        }
                }
                return null;
        }

        private int value;

        /**
         * Constuctor
         * @param value Integer value
         */
        SoundReturnCode(int value){
                this.value = value;
        }

        /**
         * @return Returns the enums integer value
         */
        public int getValue(){
                return value;
        }
}
