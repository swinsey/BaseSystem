package de.awesome.smarthome.apfcreator;

/**
 * Created by Sebif on 24.02.2017.
 */
class Assert {
    public static void assertTrue(boolean expr, String message){
        if(!expr){
            throw new AssertException(message);
        }
    }
    public static void assertFalse(boolean expr, String message){
        if(expr){
            throw new AssertException(message);
        }
    }

    public static void assertNotNull(Object obj, String message){
        if(obj == null){
            throw new AssertException(message);
        }
    }

    public static void assertEquals(Comparable<?> obj1, Comparable<?> obj2, String message){
        if(!obj1.equals(obj2)){
            throw new AssertException(message);
        }
    }

    public static void assertEqual(long element1, long element2, String message){
        if(element1 != element2){
            throw new AssertException(message);
        }
    }
    public static void assertNotEqual(long element1, long element2, String message){
        if(element1 == element2){
            throw new AssertException(message);
        }
    }
    public static void assertLower(long lower, long greater, String message){
        if(lower >= greater){
            throw new AssertException(message);
        }
    }
    public static void assertGreater(long greater, long lower, String message){
        if(greater <= lower){
            throw new AssertException(message);
        }
    }
    public static void assertLowerEquals(long lower, long greater, String message){
        if(lower > greater){
            throw new AssertException(message);
        }
    }
    public static void assertGreaterEquals(long greater, long lower, String message){
        if(greater < lower){
            throw new AssertException(message);
        }
    }
}

