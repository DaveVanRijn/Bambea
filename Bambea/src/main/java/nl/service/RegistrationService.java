package nl.service;

import java.util.List;
import nl.model.Registration;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hugo van Rijswijk
 */
@Service
@Transactional
@Repository
public class RegistrationService {

    @Autowired
    private SessionFactory sessionFactory;
    private String hql;
    private Query query;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addRegistration(Registration reg) {
        getCurrentSession().save(reg);
    }

    public void updateRegistration(Registration reg) {
        Registration regToUpdate = getRegistration(reg.getRegLink());
        regToUpdate.setRegLink(reg.getRegLink());
        regToUpdate.setNuOfRegistrations(reg.getNuOfRegistrations());

        getCurrentSession().update(regToUpdate);
    }

    public void deleteRegistration(String regLink) {
        Registration reg = getRegistration(regLink);
        if (reg != null) {
            getCurrentSession().delete(reg);
        }
    }

    public int useRegistration(Registration reg) {
        int result = reg.useRegistration();
        if (result == -1) {
            deleteRegistration(reg.getRegLink());
            return -1;
        }else{
            return reg.getNuOfRegistrations();
        }
    }

    public int useRegistration(String regLink) {
        Registration reg = getRegistration(regLink);
        return useRegistration(reg);
    }
    
    public Registration getRegistration(String regLink){
        hql = "from Registration r where r.regLink = :regLink";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("regLink", regLink);
        List<Registration> regs = query.list();
        if(regs.size() == 0){
            return null;
        }
        
        return regs.get(0);
    }

}
