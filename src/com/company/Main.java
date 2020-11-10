package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {


        String pattern = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

        IWorkSource SourceFile = new SourceFile("C:\\Users\\vladus\\ursa.html");
        new Parser(SourceFile, "C:\\Users\\vladus\\dataFile.txt", pattern).saveDataInFile();


        IWorkSource sourceNet = new SourceNet("https://mail.ru");
        new Parser(sourceNet, "C:\\Users\\vladus\\dataInet.txt", pattern).saveDataInFile();


    }
}
