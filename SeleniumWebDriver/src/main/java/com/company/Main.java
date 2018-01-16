package com.company;


import org.apache.log4j.xml.DOMConfigurator;

public class Main {
    public static void main(String[] args) {
        DOMConfigurator.configure("src\\main\\resources\\log4j.xml");
    }
}
