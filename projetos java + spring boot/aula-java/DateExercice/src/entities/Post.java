package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private Date moment;
    private String title;
    private String content;
    private Integer likes;

    private List<Comment> comments = new ArrayList<>();

    public Post(){
    }

    public Post(Date moment, String title, String content, Integer likes){
        this.moment = moment;
        this.title = title;
        this.content = content;
        this.likes = likes;
    }

    public Date getDate(){
        return moment;
    }

    public Date setDate(Date moment){
        return this.moment = moment;
    }

    public String getTitle(){
        return title;
    }

    public String setTitle(String title){
        return this.title = title;
    }

    public String getContent(){
        return content;
    }

    public String setContent(String content){
        return this.content = content;
    }

    public Integer getLikes(){
        return likes;
    }

    public Integer setLikes(Integer likes){
        return this.likes = likes;
    }

    public List<Comment> getComments(){
        return comments;
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

    public void removeComment(Comment comment){
        comments.remove(comment);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(title + "\n");
        sb.append(likes);
        sb.append(" likes - ");
        sb.append(sdf.format(moment) + "\n");
        sb.append(content + "\n");
        sb.append("Comments: \n");

        for (Comment c: comments ){
            sb.append(c.getText() + "\n");
        }

        return sb.toString();
    }
}
