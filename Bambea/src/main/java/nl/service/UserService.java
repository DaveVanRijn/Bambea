package nl.service;

import java.util.List;
import nl.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Repository
public class UserService {

    @Autowired
    private SessionFactory sessionFactory;
    private String hql;
    private Query query;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    public void editUser(User user) {
        //User updateUser = getUser(user.getUsername());
        //updateUser.setName(group.getName());
        
        //getCurrentSession().update(updateUser);
    }
    
    /**
     * Select all users
     * @return 
     */
    public List<User> getUsers() {
        hql = "from User";
        query = getCurrentSession().createQuery(hql);
        
        List<User> users = query.list();
        
        return users;
    }
    
    /**
     * Get user by username
     * 
     * @param username
     * @return 
     */
    public User getUser(String username) {
        hql = "from User u where u.username = :username";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("username", username);
        
        if( query.list().isEmpty()) {
            return null;
        } else {
            return (User) query.list().get(0);
        }  
    }
    
    /**
     * Delete an user from the database
     * @param username
     */
    public void deleteUser(String username) {
        User user = getUser(username);
        if(user != null){
            getCurrentSession().delete(user);
        }
    }
    
    public void addUser(User user) {
        
        if( getUser( user.getUsername() ) == null) {
            getCurrentSession().save(user);
        }
    }
}
