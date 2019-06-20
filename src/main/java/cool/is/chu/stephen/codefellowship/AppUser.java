package cool.is.chu.stephen.codefellowship;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column(unique=true)
    String username;
    String password;
    String fName;
    String lName;
    Date birthDate;
    String bio;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Post> posts;

    @OneToMany
    Set<AppUser> follow;

    public AppUser() {}

    public AppUser(String username, String password, String fName, String lName, Date birthDate, String bio) {
        this.username = username;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
        this.birthDate = birthDate;
        this.bio = bio;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFName() {
        return this.fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return this.lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public List<Post> getPosts() {
        return new ArrayList<Post>(this.posts);
    }

    public void setFollow(AppUser user) {
        this.follow.add(user);
    }

    public ArrayList<AppUser> getFollow() {
        return new ArrayList<AppUser>(this.follow);
    }
}
