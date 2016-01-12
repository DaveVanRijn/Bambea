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

/**
 *
 * @author Rick
 */
@Service
@Repository
@Transactional
public class DashboardUserService {
    @Autowired
    private SessionFactory sessionFactory;
    private String hql;
    private Query query;
    
    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
    
    public List<User> getUsers() {
        hql = "from User";
        query = getCurrentSession().createQuery(hql);
        
        return (List<User>) query.list();
    }
    
    public void addUser(User user) {
        getCurrentSession().save(user);
    }
    
    public void deleteUser(int id) {
        User user = getUser(id);
        if(user != null){
            getCurrentSession().delete(user);
        }
    }
    
    public User getUser(int id){
        hql = "from users u where u.id = :id";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return (User) query.list().get(0);
    }
}
