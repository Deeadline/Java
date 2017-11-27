package Singleton;

import java.io.Serializable;

public class Singleton implements Serializable {
    int value;
    private static Singleton INSTANCE = null;
    private Singleton(){

    }
    public static Singleton getInstance(){
        if(INSTANCE == null){
            //synchronized (Singleton.class){ to tez
                INSTANCE = new Singleton();
            //}
        }
        return INSTANCE;
    }
    protected Object readResolve(){
        return INSTANCE;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

//UDOWODNIĆ ŻE TO NIE JEST SINGLETON - REFLECTION
