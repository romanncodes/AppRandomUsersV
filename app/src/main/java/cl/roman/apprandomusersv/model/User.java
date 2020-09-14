package cl.roman.apprandomusersv.model;

public class User {
    public String first;
    public String last;
    public String email;
    public String phone;
    public String thumbnail;

    public User(){

    }

    public User(String first, String last, String email, String phone, String thumbnail) {
        this.first = first;
        this.last = last;
        this.email = email;
        this.phone = phone;
        this.thumbnail = thumbnail;
    }
}
