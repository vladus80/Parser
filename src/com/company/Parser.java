package com.company;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;

import java.net.*;
import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {

    String link;
    String tag;

    public Parser(String link) {
        this.link = link;

    }



    public String getStringRaw() throws IOException {

        HttpURLConnection connection = null;
        String line;
        try {
            connection = (HttpURLConnection) new URL(link).openConnection();
        } catch (IOException e) {
           // e.printStackTrace();
            System.out.println("Не удалось открыть соединение " + e.getLocalizedMessage());
        }

        StringBuilder respBody = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader( connection.getInputStream()));
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Не удалось открыть поток " + e.getLocalizedMessage());

        }
        //reader.lines().forEach(respBody::append);

        while ((line = reader.readLine()) != null) {
            respBody.append(line);
        }


        try {
            reader.close();
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Не удалось закрыть поток " + e.getLocalizedMessage());
        }

        return respBody.toString();

    }



    public ArrayList<String> getLinks(String rawString) {

        ArrayList<String> arrayList =new ArrayList<>();
        Pattern p = Pattern.compile("(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        Matcher m = p.matcher(rawString);

        while(m.find()){
            System.out.println(m.group());
            arrayList.add(m.group());
    }

       return  arrayList;

    }

    public void setLink(String link) {
        this.link = link;
    }


}
