package com.company.task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.LinkedList;

class MyHashMap {
    Logger LOGGER = LogManager.getLogger(MyHashMap.class);
    private LinkedList<Node>[] buckets;
    private int size = 0;

    private static class Node{
        int key;
        int value;
        Node(int k , int v){
            this.key = k;
            this.value = v;
        }
    }

    public MyHashMap() {
        LOGGER.info("Хеш-таблиця завжди починається з розміру 4, за потреби подвоюється");
        initialise(4);
    }

    private void initialise(int cap){
        LOGGER.info("Ініцілізація");
        buckets = new LinkedList[cap];
        for( int i = 0 ; i < cap ; i++ )
            buckets[i] = new LinkedList<>();
        size = 0;
    }

    private int hashFunction(Integer key){
        LOGGER.info("Хеш-функція для створення позитивного дійсного індексу в нашій таблиці для даного ключа");
        return Math.abs(key.hashCode()) % buckets.length;
    }


    private int searchInBucket(int key , int bi){
        LOGGER.info("Знайдемо індекс ключа в пов’язаному списку в цьому конкретному сегменті.");
        int di = 0;
        for( Node node : buckets[bi] ){
            if( node.key == key )
                return di;
            else di++;
        }
        return -1;
    }

    private void rehash(){
        LOGGER.info("Зроблено повторне хешування для покращення продуктивності в сегментах");
        LOGGER.info("Копіюємо стару таблицю у тимчасову таблицю");
        LinkedList<Node>[] oldBucket = buckets;
        LOGGER.info("Повторно ініціалізувати елементи з подвоєною попередньою місткістю");
        initialise(2*oldBucket.length);
        LOGGER.info("Копіюємо елементи назад з temp");
        for (LinkedList<Node> nodes : oldBucket) {
            for (Node node : nodes)
                put(node.key, node.value);
        }
    }

    public void put(int key, int value) {
        int bi = hashFunction(key);
        int di = searchInBucket(key,bi);
        if( di == -1 ){
            buckets[bi].addLast(new Node(key, value));
            size++;
        }
        else
            buckets[bi].get(di).value = value;

        LOGGER.info("Немає ключів, не допоможе, якщо хеш-функція погана з великою кількістю колізій.");
        double loadFactor = (1.0 * size) / buckets.length;
        if( loadFactor > 2 )
            rehash();

    }

    public int get(int key) {
        LOGGER.info("Отримуємо значення за ключем");
        int bi = hashFunction(key);
        int di = searchInBucket(key,bi);
        if( di == -1 )
            return -1;
        else
            return buckets[bi].get(di).value;
    }

    public int remove(int key) {
        LOGGER.info("Видаляємо елемент за ключем");
        int bi = hashFunction(key);
        int di = searchInBucket(key,bi);
        if( di == -1 )
            return -1;
        size--;
        return buckets[bi].remove(di).value;
    }
}
