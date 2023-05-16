package com.company;

import java.util.ArrayList;

public class LettersWord {
    public char[] letters;
    public String word;

    public LettersWord(char[] letters , String word){
        this.letters = letters;
        this.word = word;
    }

    public char[] getLetters(){
        return this.letters;
    }

    public String getWord(){
        return this.word;
    }
}
