package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
Класс Parser решает задачу получения необходимых данных из источника (файл или ссылка интернета)
Источником является экземпляры классов (объект) реализующих интерфейс IWorkSource. Таковыми классами являются SourceNet
и SourceFile, в конструкторы которых передаются ссылки на источник данных.

 */

public class Parser {

    IWorkSource iWorkSource; // ссылка на объект реализующих интерфейс IWorkSource
    String fileSave;         // путь к файлу для сохранения выходных данный
    String pattern;          // строка шаблона поиска в стиле regex.
    String rawString;        // строка для хранения сырых данных от объекта IWorkSource

    /*
     Конструктор принимает три параметра
     IWorkSource - экземпляр класса реализующих интерфейс IWorkSource
     fileSave    - Путь файла для сохранения выходных данный. Если файл не существует то он создается
     pattern     - Шаблон по которому будут фильтроваться данные
     */
    public Parser(IWorkSource iWorkSource, String fileSave, String pattern) {
        this.iWorkSource = iWorkSource;
        this.fileSave = fileSave;
        this.pattern = pattern;
    }


    // Метод возвращает список найденных вхождений в строке согласно паттерну.
    private ArrayList<String> getData() {

        try {
            rawString = iWorkSource.getStringRaw();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> arrayList = new ArrayList<>();
        Pattern p = Pattern.compile(this.pattern);
        Matcher m = p.matcher(rawString);

        while (m.find()) {
            arrayList.add(m.group());
        }

        return arrayList;

    }

   // Метод сохраняет в файл список найденных вхождений
    public void saveDataInFile() throws IOException {
        ArrayList<String> data = getData();
        File file = new File(fileSave);
        if (!file.exists() && !file.isDirectory()) {
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String value : data) {
                writer.write(value + "\n");
            }
            // Если файл пустой и не является директорией
        } else {

            if (file.length() == 0 ) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                for (String value : data) {
                    writer.write(value + "\n");
                }
            }

        }
    }

}
