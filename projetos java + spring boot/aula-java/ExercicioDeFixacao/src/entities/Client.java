package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {

    private String name;
    private String email;
    private Date birthDate;

    public Client(){

    }

    public Client(String name, String email, Date birthDate){
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getName(){
        return name;
    }

    public String setName(String name){
        return this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public String setEmail(String email){
        return this.email = email;
    }

    public Date getBirthDate(){
        return birthDate;
    }

    public Date setBirthDate(Date birthDate){
        return this.birthDate = birthDate;
    }
}
