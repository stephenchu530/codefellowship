package cool.is.chu.stephen.codefellowship;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String body;
    Timestamp createdAt;
    @ManyToOne
    AppUser user;

    public Post() {}

    public Post(String body, Timestamp createdAt, AppUser user) {
        this.setBody(body);
        this.setCreatedAt(createdAt);
        this.setUser(user);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
