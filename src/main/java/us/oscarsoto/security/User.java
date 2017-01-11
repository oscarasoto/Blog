package us.oscarsoto.security;

import org.hibernate.validator.constraints.NotBlank;
import us.oscarsoto.posts.Post;

import javax.persistence.*;
import java.util.List;

/**
 * @author oscarsoto on 1/10/17.
 *         There is no reasonable excuse for doing anything less than your best.
 *         - Martin, Robert C.
 */

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @NotBlank(message = "Please enter a username")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Please enter a password")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Please enter an email")
    @Column(nullable = false, unique = true)
    private String email;

    public User(){}

    public User(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(User user) {
        id = user.id;
        email = user.email;
        username = user.username;
        password = user.password;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return this.id + " " + this.username + " " + this.email + " " + this.password;
    }

}
