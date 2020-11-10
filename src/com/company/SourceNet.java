/*
Класс реализует интерфейс IWorkSource
Представляет обработку данных от источника типа интернет

 */

package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SourceNet implements IWorkSource{
    String link;
    public SourceNet(String link) {
        this.link = link;

    }

    @Override
    public String getStringRaw() throws IOException {

        HttpURLConnection connection = null;
        String line;
        try {
            connection = (HttpURLConnection) new URL(this.link).openConnection();
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println("Не удалось открыть соединение " + e.getLocalizedMessage());
        }

        StringBuilder respBody = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
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

}
