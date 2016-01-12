package nl.service;

import java.util.List;
import nl.model.Rule;
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
public class RuleService {

    @Autowired
    private SessionFactory sessionFactory;
    private String hql;
    private Query query;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public List<Rule> getRules() {
        hql = "from rule";
        query = getCurrentSession().createQuery(hql);
        
        return (List<Rule>) query.list();
    }
    
    public List<Rule> getNewRules() {
        
        hql="SELECT id FROM rule t1 WHERE t1.id NOT IN ( 1, 3 );";
        query = getCurrentSession().createQuery(hql);
        
        return (List<Rule>) query.list();
    }

    public void addRule(Rule rule) {
        getCurrentSession().save(rule);
    }

    public void deleteRule(int id) {
        Rule rule = getRule(id);
        if(rule != null){
            getCurrentSession().delete(rule);
        }
    }

    public Rule getRule(int id) {
        hql = "from rule r where r.id = :id";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return (Rule) query.list().get(0);
    }
    
    public void storeAllRules(List<Rule> rules) {
        for(Rule rule : rules) {
            getCurrentSession().save(rule);
        }
    }
}

