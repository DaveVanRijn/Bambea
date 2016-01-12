package nl.service;

import java.util.List;
import nl.model.Action;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dave van Rijn, Student 500714558, Klas IS202
 */
@Service
@Repository
@Transactional
public class ActionService {

    @Autowired
    private SessionFactory sessionFactory;
    private String hql;
    private Query query;
    
    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    /**
     * Get all actions from the database
     * @return List of all actions
     */
    public List<Action> getActions() {
        hql = "from action";
        query = getCurrentSession().createQuery(hql);
        
        return (List<Action>) query.list();
    }

    /**
     * Add an action to the database
     * @param action The action to be added
     */
    public void addAction(Action action) {
        getCurrentSession().save(action);
    }

    /**
     * Delete an action from the database
     * @param id The id of the action to be deleted
     */
    public void deleteAction(int id) {
        Action action = getAction(id);
        if(action != null){
            getCurrentSession().delete(action);
        }
    }

    /**
     * Edit an action in the database
     * @param action Action with the new properties
     */
    public void editAction(Action action) {
        Action updateAction = getAction(action.getId());
        updateAction.setName(action.getName());
        updateAction.setNotification(action.getNotification());
        updateAction.setOpenApp(action.getOpenApp());
        updateAction.setUrl(action.getUrl());
        updateAction.setVibrate(action.isVibrate());
        
        getCurrentSession().update(updateAction);
    }

    /**
     * Get an action from the database by id
     * @param id id of the action
     * @return Action according to the id
     */
    public Action getAction(int id) {
        hql = "from action a where a.id = :id";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        List<Action> list = (List<Action>) query.list();
        if(list.isEmpty()) return null;
        return list.get(0);
    }
    
    public void storeAllActions(List<Action> actions){
        for(Action action : actions){
            getCurrentSession().save(action);
        }
    }
}

