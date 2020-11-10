/*
Класс реализует интерфейс IWorkSource
Представляет обработку данных от источника типа файл

 */

package com.company;
import java.io.*;

public class SourceFile implements IWorkSource {
    String fileName;
    File file;


    public SourceFile(String fileName) {
        this.fileName = fileName;

        File file = new File(this.fileName);
        if (file.exists() && !file.isDirectory()) {
            this.file = file;
        } else {
            System.out.println("Неверное имя файла или указан каталог");
            System.exit(0);
        }

    }

    @Override
    public String getStringRaw() throws IOException {

            String line;
            StringBuilder respBody = new StringBuilder();
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
            } catch (IOException e) {
                System.out.print("Не удалось открыть поток " + e.getMessage());
            }

            while ((line = reader.readLine()) != null) {
                respBody.append(line);
            }

            try {
                reader.close();
            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println("Не удалось закрыть поток " + e.getMessage());
            }

        return respBody.toString();

    }
}

