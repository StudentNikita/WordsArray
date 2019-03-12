package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
	String [] array = {"и", "мы", "кот", "гора", "вирус", "дорога", "магазин", "карусель", "семинария", "утилизатор"};
	StringBuilder text = new StringBuilder();

	for(int i = 0;i<10000;i++){                                    // в случайном порядке заполнил строку словами из массива
        int random = (int) (Math.random()*10);
        text.append(array[random]).append(" ").toString();
        }


	File myFile = new File("text.txt");// создал текстовый файл, в который перенес содержимое строки "text"

        BufferedWriter writer = new BufferedWriter(new FileWriter(myFile));// создал текстовый файл, в который перенес содержимое строки "text"
        writer.write(String.valueOf(text));
        writer.flush();
        writer.close();



	Scanner scanner1 = new Scanner(new File("C:\\Users\\Admin\\IdeaProjects\\WordsArray\\text.txt")); // сортировка слов в массиве в алфавитном порядке
	List <String> alphabeticalOrder = new ArrayList<>();
	while (scanner1.hasNext()){
	    String word = scanner1.useDelimiter("\\s+").next();
	    alphabeticalOrder.add(word);
	    Collections.sort(alphabeticalOrder, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);}});
    }

        myFile.delete();                                    //перезаписать отсортированного массива в созданный файл text.txt
        myFile.createNewFile();
        BufferedWriter writer1 = new BufferedWriter(new FileWriter  (myFile));
        StringBuilder text2 = new StringBuilder();
        for (int i = 0; i<alphabeticalOrder.size(); i++){
            text2.append(alphabeticalOrder.get(i)).append(" ").toString();
        }
        writer1.write(String.valueOf(text2));
        writer1.flush();
        writer1.close();



	Scanner scanner2 = new Scanner(new File("C:\\Users\\Admin\\IdeaProjects\\WordsArray\\text.txt"));
	Map<String, Integer> statistics = new HashMap<>();                                                          //Считаем сколько раз каждое слово встречается в файле и выводим на экран статистику
	while (scanner2.hasNext()){
	    String word = scanner2.useDelimiter("\\s+").next();
	    Integer count = statistics.get(word);
	        if (count == null) count = 0;
	        statistics.put(word, ++count);
    }
	for(Map.Entry<String, Integer> pair: statistics.entrySet()){
        System.out.print(pair.getKey() + " = " + pair.getValue() + ", ");
        }
        System.out.println();

	int maxValue=0;         //ищем слово, которое повторяется наибольшее количество раз и выводим его на экран
	String maxValueWord = null;
	for(Map.Entry<String, Integer> pair: statistics.entrySet()){

	    if (maxValue < pair.getValue()){
	        maxValue = pair.getValue();
	        maxValueWord = pair.getKey();
        }
    }
        System.out.println("Слово " + maxValueWord.toUpperCase() + " встречается чаще всех - " + maxValue + " раз");





    Collections.sort(alphabeticalOrder,Comparator.comparing(String::length));               //сортируем старую коллекцию, в которой слова были отсортированы в алфавитном порядке, по длине слов
    StringBuilder text3 = new StringBuilder();
        for(int i = 0;i<alphabeticalOrder.size();i++){                                    //заносим слова из массива в строку

            text3.append(alphabeticalOrder.get(i) + " ");
        }

        // создал второй текстовый файл, в который перенес содержимое отсортированнйо по длине коллекции
        File myFile2 = new File("text2.txt");
            BufferedWriter writer2 = new BufferedWriter(new FileWriter(myFile2));
            writer2.write(String.valueOf(text3));
            writer2.flush();
            writer2.close();


        double WordLenght = 0; //поиск общей длины всех слов
        for (String word: alphabeticalOrder){
            WordLenght += word.length();
        }

        double medianWordLenght = 0; //поиск медианной длины всех слов
        if(alphabeticalOrder.size()%2==0){
            medianWordLenght = (alphabeticalOrder.get(alphabeticalOrder.size()/2).length()+alphabeticalOrder.get(alphabeticalOrder.size()/2-1).length())/2;
        }
        else {
            medianWordLenght = alphabeticalOrder.get(alphabeticalOrder.size()/2).length();
        }

        System.out.println("Средняя длина всех слов:" + WordLenght/alphabeticalOrder.size());
        System.out.println("Медианная длина всех слов: " + medianWordLenght);
    }

}
