package Model;

public class User {
    private String Userid;
    private String name;
    private String email;

    // Constructor
    public User(String id, String name, String email) {
        this.Userid = id;
        this.name = name;
        this.email = email;
    }

    // Getters
    public String getId() {
        return Userid;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }


    //Setters
    public void setId(String id) {
        this.Userid = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
