package org.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yuanqiang.liao
 * @description TODO
 * @createTime 2022-09-03 18:46:00
 */
public class Main {
    protected static volatile boolean running = true;

    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext(new String[]{"spring-config.xml"});
        HelloWorld helloWorld = (HelloWorld) applicationContext.getBean("helloWorld");
        helloWorld.PrintHello();
        applicationContext.refresh();
        applicationContext.start();

        synchronized (Main.class) {
            while (running) {
                try {
                    Main.class.wait();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
