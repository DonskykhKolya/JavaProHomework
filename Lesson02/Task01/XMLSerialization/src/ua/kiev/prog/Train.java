package ua.kiev.prog;

import ua.kiev.prog.adapters.LocalDateAdapter;
import ua.kiev.prog.adapters.LocalTimeAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalTime;


@XmlRootElement(name="train")
public class Train {

    private int id;
    private String from;
    private String to;
    private LocalDate date;
    private LocalTime departure;

    public Train() {}

    public Train(int id, String from, String to, LocalDate date, LocalTime departure) {

        this.id = id;
        this.from = from;
        this.to = to;
        this.date = date;
        this.departure = departure;
    }

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    @XmlElement
    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    @XmlElement
    public void setTo(String to) {
        this.to = to;
    }

    public LocalDate getDate() {
        return date;
    }

    @XmlElement
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getDeparture() {
        return departure;
    }

    @XmlElement
    @XmlJavaTypeAdapter(value = LocalTimeAdapter.class)
    public void setDeparture(LocalTime departure) {
        this.departure = departure;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Train #").append(this.id).append("\n");
        sb.append("---------------------------------------\n");
        sb.append("From: ").append(this.from).append("\n");
        sb.append("To: ").append(this.to).append("\n");
        sb.append("Departure: ").append(this.date).append(" ").append(this.departure).append("\n");
        return sb.toString();
    }
}
