package sk.kosickaakademia.sk.scratchpad.util;

import org.bson.types.ObjectId;

import java.util.Date;

public class Tasks {
    private String title;
    private double price;
    private int priority;
    private boolean done;
    private Date date;
    private ObjectId id;

    public Tasks(String title , int priority, boolean done, Date date) {
        this.date = date;
        this.title = title;
        this.priority = priority;
        this.done = done;
    }

    public Tasks(String title, Date date, double price, int priority, boolean done) {
        this(title,priority, done,date );
        this.price=price;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isDone() {
        return done;
    }

    public Date getDate() {
        return date;
    }

    public ObjectId getId() {
        return id;
    }
}

