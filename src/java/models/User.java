
package models;

public class User {
    
private String firstName;
private String lastName;
private final String PASSWORD;
private String email;
private boolean status;
private int role;

public User(){
firstName = null;
lastName = null;
PASSWORD = null;
email = null;
status = false;
role = 0;
}

public User(String email, boolean status, String firstName, String lastName,  String password, int role){
this.firstName = firstName;
this.lastName = lastName;
this.PASSWORD = password;
this.email = email;
this.role = role;
this.status = status;
}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }
    
    public int statusToInt(){
    if(status)
        return 1;
    else
        return 0;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getRole() {
        return role;
    }
    
    public String roleTitle(){
        switch(role){
            case(1):
                return "System Administrator";
            case(2):
                return "User";
            case(3):
                return "Company Administrator";
            default:
                return "Role";
        }
    }

    public void setRole(int role) {
        this.role = role;
    }
    
    public String getPassword(){
    return PASSWORD;
    }


}
