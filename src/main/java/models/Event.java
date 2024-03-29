package models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "events")
public class Event implements Comparable<Event>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private LocalDate date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ownerid")
    private Tlguser owner;

    public Event() {
    }

    public Event(String description, LocalDate date, Tlguser owner) {
        this.description = description;
        this.date = date;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Tlguser getOwner() {
        return owner;
    }

    public void setOwner(Tlguser owner) {
        this.owner = owner;
    }

    @Override
    public int compareTo(Event o) {
        int r = 0;

        if (this.id == o.id) r = 0;
        if (this.id > o.id) r = 1;
        if (this.id < o.id) r = -1;

        return r;
    }
}
