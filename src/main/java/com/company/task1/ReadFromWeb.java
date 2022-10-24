package com.company.task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.*;

public class ReadFromWeb{
    static Logger LOGGER = LogManager.getLogger(ReadFromWeb.class);
    public static boolean isFibonacci(int n) {
        LOGGER.info("Check if number is Fibonacci");
        int fib1 = 0;
        int fib2 = 1;
        do {
            int saveFib1 = fib1;
            fib1 = fib2;
            fib2 = saveFib1 + fib2;
        }
        while (fib2 < n);

        return fib2 == n;

    }
    public static void main(String[] args)  {
        try {
            LOGGER.info("Read data from website anf write into file");
            FileWriter fWriter = new FileWriter("test1.txt");
            URL url = new URL("https://en.wikipedia.org/wiki/Fibonacci_number");
            InputStream content = url.openStream();
            int c;
            while ((c = content.read())!=-1) {
                fWriter.write(c);
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
        BufferedReader reader;
        try {
            int numberOfLine = 1;
            FileWriter fWriter1 = new FileWriter("newFile.txt");
            reader = new BufferedReader(new FileReader("test1.txt"));
            String line = reader.readLine();
            while (line != null) {
                if (isFibonacci(numberOfLine)){
                    fWriter1.write(line);
                    fWriter1.write("Number of line - " + numberOfLine);
                }
                line = reader.readLine();
                numberOfLine++;
            }
            reader.close();
        } catch (IOException e) {
            LOGGER.error(e);
        }

    }

}