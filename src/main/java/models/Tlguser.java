package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "tlgusers")
public class Tlguser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long chatid;
    private String state;
    @Column(nullable = true)
    private String username;
    @Column(nullable = true)
    private String firstname;
    @Column(nullable = true)
    private String lastname;
    @OneToMany (mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events;

    public Tlguser() {
    }

    public Tlguser(long chatid, String state) {
        this.chatid = chatid;
        this.state = state;
    }

    public Tlguser(long chatid, String state, String username) {
        this.chatid = chatid;
        this.state = state;
        this.username = username;
    }

    public Tlguser(long chatid, String state, String username, String firstname, String lastname) {
        this.chatid = chatid;
        this.state = state;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public long getChatid() {
        return chatid;
    }

    public void setChatid(long chatid) {
        this.chatid = chatid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
