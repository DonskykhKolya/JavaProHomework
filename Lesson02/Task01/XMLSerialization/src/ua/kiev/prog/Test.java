package ua.kiev.prog;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;


public class Test {

    public static void execute() {

        try {
            File file = new File("src\\ua\\kiev\\prog\\trains.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Trains.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Trains trains = (Trains) unmarshaller.unmarshal(file);
            System.out.println("All trains");
            System.out.println(trains);

            findTrainsByDeparture(trains, LocalTime.of(15,0), LocalTime.of(19,0));

            trains.add(new Train(3, "Kyiv", "Odessa", LocalDate.now(), LocalTime.of(15,30)));
            trains.add(new Train(4, "Lviv", "Kyiv", LocalDate.now(), LocalTime.of(18,45)));

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(trains, file);
            System.out.println("New train list");
            marshaller.marshal(trains, System.out);
        }
        catch(JAXBException e) {
            e.printStackTrace();
        }
    }

    private static void findTrainsByDeparture(Trains trains, LocalTime from, LocalTime to) {

        System.out.println("Today from " + from + " to " + to);
        trains.getTrains().stream()
                .filter(t -> t.getDate().isEqual(LocalDate.now()) && t.getDeparture().isAfter(from) && t.getDeparture().isBefore(to))
                .collect(Collectors.toList()).forEach(System.out::println);
    }
}
