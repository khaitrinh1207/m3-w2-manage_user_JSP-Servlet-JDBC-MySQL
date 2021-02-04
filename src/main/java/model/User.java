package model;

public class User {
    private int id;
    private String name;
    private String email;
    private String county;


    public User() {
    }

    public User(String name, String email, String county) {
        this.name = name;
        this.email = email;
        this.county = county;
    }

    public User(int id, String name, String email, String county) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.county = county;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}
