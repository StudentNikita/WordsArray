package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
	String [] array = {"и", "мы", "кот", "гора", "вирус", "дорога", "магазин", "карусель", "семинария", "утилизатор"};
	StringBuilder text = new StringBuilder();

	for(int i = 0;i<10000;i++){                                    // в случайном порядке заполнил строку словами из массива
        int random = (int) (Math.random()*10);
        text.append(array[random] + " ");
        }

	FileWriter writer = new FileWriter("text.txt");   // создал текстовый файл, в который перенес содержимое строки "text"
	writer.write(String.valueOf(text));


	Scanner scanner1 = new Scanner(new File("C:\\Users\\Admin\\IdeaProjects\\WordsArray\\text.txt")); // сортировка слов из файла в алфавитном порядке
	List <String> alphabeticalOrder = new ArrayList<>();
	while (scanner1.hasNext()){
	    String word = scanner1.useDelimiter("\\s+").next();
	    alphabeticalOrder.add(word);
	    Collections.sort(alphabeticalOrder, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }




	Scanner scanner2 = new Scanner(new File("C:\\Users\\Admin\\IdeaProjects\\WordsArray\\text.txt"));
	Map<String, Integer> statistics = new HashMap<>();                                                          //Считаем сколько раз каждое слово встречается в файле и выводим на экран статистику
	while (scanner2.hasNext()){
	    String word = scanner2.useDelimiter("\\s+").next();
	    Integer count = statistics.get(word);
	        if (count == null) count = 0;
	        statistics.put(word, ++count);
    }
	System.out.println(statistics);

	int maxValue=0;         //ищем слово, которое повторяется наибольшее количество раз и выводим его на экран
	String maxValueWord = null;
	for(Map.Entry<String, Integer> pair: statistics.entrySet()){

	    if (maxValue < pair.getValue()){
	        maxValue = pair.getValue();
	        maxValueWord = pair.getKey();
        }
    }
        System.out.println("Слово " + maxValueWord.toUpperCase() + " встречается чаще всех - " + maxValue + " раз");


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


    Collections.sort(alphabeticalOrder,Comparator.comparing(String::length));
    StringBuilder text2 = new StringBuilder();
        for(int i = 0;i<alphabeticalOrder.size();i++){                                    //сортируем коллекцию по длине слов

            text2.append(alphabeticalOrder.get(i) + " ");
        }

        FileWriter writer2 = new FileWriter("text2.txt");   // создал второй текстовый файл, в который перенес содержимое отсортированнйо по длине коллекции
        writer2.write(String.valueOf(text2));
        writer2.close();


    }

}
