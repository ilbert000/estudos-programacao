package entities;

import java.security.PublicKey;

public class Comment {

    private String text;

    public Comment(){

    }

    public Comment(String text){
        super();
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String setText() {
        return this.text = text;
    }
}
