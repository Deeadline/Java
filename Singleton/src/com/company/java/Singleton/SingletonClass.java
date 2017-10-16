package com.company.java.Singleton;

public class SingletonClass {
    private static SingletonClass instance; // zielona = new SingletonClass();
    private SingletonClass(){

    }
    public static SingletonClass getInstance(){
        if(instance==null){
            instance = new SingletonClass();
        }
        return instance;
    }

}