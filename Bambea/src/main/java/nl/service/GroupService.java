/**
 *
 * @author Dave van Rijn, Student 500714558, Klas IS202
 */
package nl.service;

import java.util.List;
import nl.model.Group;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Repository
@Transactional
public class GroupService {

    @Autowired
    private SessionFactory sessionFactory;
    private String hql;
    private Query query;
    
    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
    
    public List<Group> getGroups(){
        hql="from groep";
        query = getCurrentSession().createQuery(hql);
        
        return (List<Group>) query.list();
    }

    public void addGroup(Group group) {
        getCurrentSession().save(group);
    }

    public void deleteGroup(int id) {
        Group group = getGroup(id);
        if(group != null){
            getCurrentSession().delete(group);
        }
    }

    public void editGroup(Group group) {
        Group updateGroup = getGroup(group.getId());
        updateGroup.setName(group.getName());
        
        getCurrentSession().update(updateGroup);
    }

    public Group getGroup(int id) {
        hql = "from groep g where g.id = :id";
        
        query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        List<Group> list = (List<Group>) query.list();
        if(list.isEmpty()) return null;
        return list.get(0);
    }
    
    public void storeAllGroups(List<Group> groups){
        for(Group group : groups){
            getCurrentSession().save(group);
        }
    }
}