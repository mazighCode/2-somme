package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.lang.Math;


public class Main {
    static File file = new File("src/com/company/dico.txt");
    static int m = nextPrime((int) countLineBufferedReader(file.toString())/3);

    public static void main(String[] args) throws Exception {
        ArrayList<LinkedList<LettersWord>> hashTable = new ArrayList<LinkedList<LettersWord>>();
        for (int i=0; i<m; i++){
            LinkedList<LettersWord> emptyList = new LinkedList<LettersWord>();
            hashTable.add(i, emptyList);
        }

        //long start = System.currentTimeMillis();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            String string = sc.nextLine();

            char[] sortedLetters = sortLetters(string);

            LettersWord lettersWord = new LettersWord(sortedLetters, string);
            hashTable.get(keyGenerator(sortedLetters)).add(lettersWord);
        }
        //long end = System.currentTimeMillis();
        //double time = (end-start)/1000.0;
        //System.out.println(time+" s");


        //  Remarque: les caractères entrés en paramètre à la fonction displayResult doivent etre triés en ordre alphabétique


                                // Tests pour le fichier minidico.txt

        /*
        long start = System.currentTimeMillis();
        System.out.println(displayResult(new char[]{'a', 'd', 'e', 'e', 'i', 'i', 'l', 'n', 'n', 'n', 'o', 'r', 'u', 'x'}, hashTable));
        long end = System.currentTimeMillis();
        double time = (end-start)/1000.0;
        System.out.println(time+" s");
        */

        /*
        long start = System.currentTimeMillis();
        System.out.println(displayResult(new char[]{'a', 'a', 'a', 'a', 'e', 'i', 'n', 'n', 'o', 'r', 's', 's', 's', 's', 't', 'w', 'z'}, hashTable));
        long end = System.currentTimeMillis();
        double time = (end-start)/1000.0;
        System.out.println(time+" s");
        */



                                // Tests pour le fichier dico.txt


        long start = System.currentTimeMillis();
        System.out.println(displayResult(new char[]{'a', 'b', 'e', 'e', 'i', 'i', 'i', 'l', 'l', 'n', 'n', 'o', 'r', 'r', 's', 's', 't', 'y', 'z', 'é'}, hashTable));
        long end = System.currentTimeMillis();
        double time = (end-start)/1000.0;
        System.out.println(time+" s");




        /*
        long start = System.currentTimeMillis();
        System.out.println(displayResult(new char[]{'a', 'a', 'a', 'b', 'd', 'e', 'i', 'i', 'i', 'l', 'l', 'n', 'o', 'r', 'r', 'r', 's', 't', 'z', 'z'}, hashTable));
        long end = System.currentTimeMillis();
        double time = (end-start)/1000.0;
        System.out.println(time+" s");
         */




        /*
        long start = System.currentTimeMillis();
        System.out.println(displayResult(new char[]{'a', 'a', 'd', 'e', 'h', 'i', 'm', 'n', 'n', 'o', 'o', 'r', 'r', 't', 't', 'x', 'z'}, hashTable));
        long end = System.currentTimeMillis();
        double time = (end-start)/1000.0;
        System.out.println(time+" s");
        */


        /*
                    // haddadi mazigh
        long start = System.currentTimeMillis();
        System.out.println(displayResult(new char[]{'a', 'a', 'a', 'd', 'd', 'd', 'g', 'h', 'h', 'i', 'm', 'z'}, hashTable));
        long end = System.currentTimeMillis();
        double time = (end-start)/1000.0;
        System.out.println(time+" s");
        */

        /*
                    // otmane cherif mohammed islem
        long start = System.currentTimeMillis();
        System.out.println(displayResult(new char[]{'a', 'a', 'c', 'd', 'e', 'e', 'e', 'e', 'f', 'h', 'h', 'i', 'i','l','m','m', 'm', 'm', 'm', 'n','o','o','r','s','t'}, hashTable));
        long end = System.currentTimeMillis();
        double time = (end-start)/1000.0;
        System.out.println(time+" s");

         */

    }

    static boolean isPrime(int n)
    {
        // Corner cases
        if (n <= 1) return false;
        if (n <= 3) return true;

        // This is checked so that we can skip
        // middle five numbers in below loop
        if (n % 2 == 0 || n % 3 == 0) return false;

        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;

        return true;
    }


    static int nextPrime(int N)
    {

        // Base case
        if (N <= 1)
            return 2;

        int prime = N;
        boolean found = false;

        // Loop continuously until isPrime returns true for a number greater than n
        while (!found)
        {
            prime++;

            if (isPrime(prime))
                found = true;
        }

        return prime;
    }



    public static long countLineBufferedReader(String fileName) {

        long lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.readLine() != null) lines++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;

    }


    public static int keyGenerator(char[] sortedWord){
        int key = 0;
        int size = sortedWord.length - 1;
        for(int i = 0 ; i<sortedWord.length ; i++){
            key = (key + (int) (sortedWord[i] * Math.pow(256, size))) % m;
            size--;
        }
        return key;
    }


    public static char[] sortLetters(String word){
        String str = word;
        char[] arr = str.toCharArray();
        char temp;
        int i = 0;
        while (i < arr.length-1) {
            int j = i + 1;
            while (j < arr.length) {
                if (arr[j] < arr[i]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
                j += 1;
            }
            i += 1;
        }
        return arr;
    }


    public static boolean isPartOfString(char[] sortedDictWord, char[] sortedGivenWord){
        int indexDictWord = 0;
        int indexGivenWord = 0;
        while (indexDictWord < sortedDictWord.length &&  indexGivenWord < sortedGivenWord.length){
            if (sortedDictWord[indexDictWord] == sortedGivenWord[indexGivenWord]){
                indexDictWord++;
                indexGivenWord++;
            }
            else if (sortedDictWord[indexDictWord] > sortedGivenWord[indexGivenWord]){
                indexGivenWord++;
            }
            else return false;
        }

        if (indexDictWord >= sortedDictWord.length && indexGivenWord >= sortedGivenWord.length){
            return true;
        }
        else if (indexDictWord >= sortedDictWord.length && indexGivenWord < sortedGivenWord.length){
            return true;
        }
        else return false;
    }


    public static char[] findComplementary (char[] sortedDictWord, char[] sortedGivenWord) {
        int indexDictWord = 0;
        int indexGivenWord = 0;
        char[] complementary = new char[sortedGivenWord.length-sortedDictWord.length];
        int i=0;
        while (indexDictWord < sortedDictWord.length &&  indexGivenWord < sortedGivenWord.length){
            if (sortedDictWord[indexDictWord] == sortedGivenWord[indexGivenWord]){
                indexDictWord++;
                indexGivenWord++;
            }
            else if (sortedDictWord[indexDictWord] > sortedGivenWord[indexGivenWord]){
                complementary[i] = sortedGivenWord[indexGivenWord];
                i++;
                indexGivenWord++;
            }

        }

        if (indexDictWord >= sortedDictWord.length && indexGivenWord >= sortedGivenWord.length){
            return complementary;
        }
        else if (indexDictWord >= sortedDictWord.length && indexGivenWord < sortedGivenWord.length){
           for (int j=indexGivenWord; j<sortedGivenWord.length; j++){
               complementary[i] = sortedGivenWord[j];
               i++;
           }
        }


        return complementary;
    }


    public static String displayResult(char[] bigWord, ArrayList<LinkedList<LettersWord>> hashTable){
        System.out.println(bigWord);

        for (int indexOfArrayList=0; indexOfArrayList<hashTable.size(); indexOfArrayList++){
            for (int indexOfLinkedList =0; indexOfLinkedList< hashTable.get(indexOfArrayList).size(); indexOfLinkedList++){
                if (isPartOfString(sortLetters(hashTable.get(indexOfArrayList).get(indexOfLinkedList).getWord()), bigWord)){
                    char[] compl = findComplementary(sortLetters(hashTable.get(indexOfArrayList).get(indexOfLinkedList).getWord()), bigWord);
                    int hashKey = keyGenerator(compl);
                    for (int j=0; j<hashTable.get(hashKey).size(); j++){
                        if (String.valueOf(compl).equals(String.valueOf(sortLetters(hashTable.get(hashKey).get(j).getWord())))){
                            return ("Les deux mots sont: " + hashTable.get(indexOfArrayList).get(indexOfLinkedList).getWord() + " et " + hashTable.get(hashKey).get(j).getWord());
                        }

                    }
                }
            }
        }
        return ("Il n'y a pas deux mots dans le dictionnaire qui forment votre mot.");
    }

}
