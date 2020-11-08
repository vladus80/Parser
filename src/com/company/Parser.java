package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Parser {

    String link;
    String tag;

    public Parser(String link, String tag) {
        this.link = link;
        this.tag = tag;
    }



    public String getStringRaw()  {

        HttpURLConnection connection = null;
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
        reader.lines().forEach(respBody::append);
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
