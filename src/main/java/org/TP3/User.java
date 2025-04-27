package org.TP3;

public class User {
    private long id;
    private String name;

    // Constructeurs
    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters et setters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
