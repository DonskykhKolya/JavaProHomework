package Lesson01.Task3;


public class Book {

    @Save
    private String author;
    @Save
    private String title;
    @Save
    private int year;

    private String publishingHouse;



    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Автор: ").append(this.author).append("\n");
        sb.append("Название: ").append(this.title).append("\n");
        sb.append("Год: ").append(this.year).append("\n");
        sb.append("Издательство: ").append(this.publishingHouse != null ? this.publishingHouse : "не известно");
        return sb.toString();
    }
}
