package models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Date date;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="ownerid")
    private Tlguser owner;

    public Event() {
    }

    public Event(String name, Date date, Tlguser owner) {
        this.name = name;
        this.date = date;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Tlguser getOwner() {
        return owner;
    }

    public void setOwner(Tlguser owner) {
        this.owner = owner;
    }
}
