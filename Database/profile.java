package Database;

public class profile {

    public String pEmail, pUser;

    public String getEmail() {
        return this.pEmail;
    }

    public String getUser() {
        return this.pUser;
    }

    profile(String email, String user) {
        pEmail = email;
        pUser = user;
    }
}   
