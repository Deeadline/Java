package com.company.java.whoIsRunningFirst;

class MyRunner extends MyRunnerB{
    public MyRunner() {
        System.out.println("A1");
    }
    public void test(){
        System.out.println("T1");
    }
    {
        System.out.println("I1");
    }
    static{
        System.out.println("I0");
    }
}

/*
class B{
 A a = new A();
 }
 main --> B b = B();
 b.b();
 b.getA();

 Dla static void wyw w main
 static
 static void
 konstruktor
 //
 Dla tworzenia obiektu (konstruktor w main)
 static B
 static A
 blok z B
 konstruktor B
 blok z A
 konsutkro A
 void z A
 */
