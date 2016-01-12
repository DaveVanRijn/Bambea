package nl.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * A user has a username, password and a role. The role can consist of ROLE_ADMIN, ROLE_MOD and ROLE_PARTNER
 * @author Hugo
 */
@Entity
@Table(name="User")
public class User implements Serializable {
    
    private int id;
    private String username;
    private String password;
    private Set<UserRole> userRole = new HashSet<UserRole>(0);

    public User() { }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Set<UserRole> userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }
    
    @Id
    @Column(name = "username", unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false, length = 100)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", targetEntity=UserRole.class)
    public Set<UserRole> getUserRole() {
        return this.userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }
}
