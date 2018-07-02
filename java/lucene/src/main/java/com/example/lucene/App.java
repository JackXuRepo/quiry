package com.example.lucene;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {

    private static String dataDir = "";

    public static void main(String[] args) {
        try {
            createIndex();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void createIndex() throws IOException {
        Index index = new Index();

        long startTime = System.currentTimeMillis();    
        int numIndexed = index.createIndex(dataDir);
        long endTime = System.currentTimeMillis();
        
        System.out.println(numIndexed + " File indexed, time taken: " + (endTime - startTime) +" ms");        
    }

}
