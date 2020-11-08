package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Parser parser = new Parser("https://mail.ru", "");
        String list = parser.getStringRaw();
        System.out.println(parser.getLinks(list).toString());

        for (String link: parser.getLinks(list)) {
            System.out.println(link);
        }

    }
}
