package nl.service;

import nl.model.UserRole;
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
public class UserRoleService {

    @Autowired
    private SessionFactory sessionFactory;
    private String hql;
    private Query query;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    public void addUserRole(UserRole userRole) {
        
        getCurrentSession().save(userRole);
    }
}
