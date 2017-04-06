package com.beurive;
import java.util.HashMap;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class Main {

    public static void main(String[] args) {

        // -----------------------------------------
        // Fixed size arrays
        // -----------------------------------------

        Byte[] array1 = new Byte[3];
        array1[0] = 1;
        array1[1] = 2;
        array1[2] = 3;

        System.out.println("Length of the aray1: " + array1.length);
        for (int i=0; i<array1.length; i++) {
            System.out.println(array1[i]);
        }

        Byte[] array2 = {10,20,30};

        System.out.println("Length of the aray2: " + array2.length);
        for (int i=0; i<array2.length; i++) {
            System.out.println(array2[i]);
        }

        // -----------------------------------------
        // Associative arrays
        // -----------------------------------------

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("A", "AA");
        map.put("B", "BB");

        // -----------------------------------------
        // Variable size arrays (lists)
        // -----------------------------------------

        ArrayList<Integer> array3 = new ArrayList<Integer>();
        array3.add(100);
        array3.add(200);
        array3.add(300);
        for (int i=0; i<array3.size(); i++) {
            System.out.println(array3.get(i));
        }

        Integer[] table = array3.toArray(new Integer[0]);
        for (int i=0; i<table.length; i++) {
            System.out.println(table[i]);
        }

        // -----------------------------------------
        // Files
        // -----------------------------------------

        // Create the path of the file that will be created.
        Path filePath = Paths.get(System.getProperty("java.io.tmpdir"), "java.txt");
        System.out.println(filePath.toString());
        byte[] buffer = { 10, 20, 30};
        try {
            Files.write(filePath, buffer);
            byte[] data = Files.readAllBytes(filePath);
            for (int i=0; i<data.length; i++) {
                System.out.println(data[i]);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }


    }
}
