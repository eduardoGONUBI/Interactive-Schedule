package pt.ubi.di.pdm;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Event {
    public static ArrayList<Event> eventsList = new ArrayList<>();
    public static ArrayList<Event> lista33 = new ArrayList<>();
    public static String lista44 = new String();
    public ArrayList<String> teste1 = new ArrayList<>();
    public static ArrayList<Event> eventsForDate(LocalDate date) {
        ArrayList<Event> events = new ArrayList<>();

        for (Event event : eventsList) {
            if (event.getDate().equals(date))
                events.add(event);
        }

        return events;
    }


    private String name,hora,UC;
    private LocalDate date;

    public Event(String name, LocalDate date,String hora,String UC) {
        this.name = name;
        this.date = date;
        this.hora = hora;
        this.UC=UC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUC() {
        return UC;
    }

    public void setUC(String UC) {
        this.UC = UC;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
