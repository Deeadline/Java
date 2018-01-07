package Singleton;


public enum ESingleton {
    INSTANCE;
    int value;
    public int getValue(){
        return value;
    }
    public void setValue(int value){
        this.value=value;
    }
}
