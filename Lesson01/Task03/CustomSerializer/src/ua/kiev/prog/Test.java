package Lesson01.Task3;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Test {

    private static final String FILE_PATH = "c:\\book.txt";

    public static void execute() {

        serializeTest();
        deserializeTest();
    }

    private static void serializeTest() {

        String bookDesc = Serializer.serialize(createBook());

        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            fw.write(bookDesc);
            System.out.println("Сериализация завершена.");
            System.out.println("Информация о книге:");
            System.out.println(bookDesc);
            System.out.println("Сохранено в файл: " + FILE_PATH);
            System.out.println();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static Book createBook() {

        Book book = new Book();
        book.setAuthor("Кей Хорстманн");
        book.setTitle("Java. Библиотека профессионала");
        book.setYear(2016);
        book.setPublishingHouse("Вильямс");
        return book;
    }

    private static void deserializeTest() {

        try(FileReader fr = new FileReader(FILE_PATH)) {
            Scanner scan = new Scanner(fr);
            scan.useDelimiter(";");
            while(scan.hasNext()) {
                Book book = Serializer.deserialize(scan.next(), Book.class);
                System.out.println("Десериализация завершена.");
                System.out.println("Информация о книге:");
                System.out.println(book.toString());
            }
        }
        catch(IOException | IllegalAccessException | InstantiationException  e) {
            e.printStackTrace();
        }
    }
}
