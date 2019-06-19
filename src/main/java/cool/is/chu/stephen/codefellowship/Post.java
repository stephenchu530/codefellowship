package cool.is.chu.stephen.codefellowship;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String body;
    Date createdAt;
    @ManyToOne
    AppUser user;

    public Post() {}

    public Post(String body, Date createdAt) {
        this.setBody(body);
        this.setCreatedAt(createdAt);
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
