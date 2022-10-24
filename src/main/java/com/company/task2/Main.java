package com.company.task2;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    static Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        MyHashMap hashMap = new MyHashMap();
        hashMap.put(1,5);
        hashMap.put(2,7);

        LOGGER.info(hashMap.get(1));
        hashMap.put(1,4);
        LOGGER.info(hashMap.get(1));
        LOGGER.info(hashMap.get(3));
    }
}
