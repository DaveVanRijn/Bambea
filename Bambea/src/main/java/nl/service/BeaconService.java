package nl.service;

import java.util.List;
import nl.model.Beacon;
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
public class BeaconService {

    @Autowired
    private SessionFactory sessionFactory;
    private String hql;
    private Query query;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    public void addBeacon(Beacon beacon) {
        getCurrentSession().save(beacon);
    }
    
    public void updateBeacon(Beacon beacon) {
        Beacon beaconToUpdate = getBeacon(beacon.getId());
        beaconToUpdate.setTitle(beacon.getTitle());
        beaconToUpdate.setLat(beacon.getLat());
        beaconToUpdate.setLng(beacon.getLng());
        
        getCurrentSession().update(beaconToUpdate);
    }
    
    public void deleteBeacon(int id){
        Beacon beacon = getBeacon(id);
        if(beacon != null){
            getCurrentSession().delete(beacon);
        }
    }
    
    public void editBeacon(Beacon beacon, int id) {
        
        Beacon updateBeacon = getBeacon(id);
        updateBeacon.setTitle(beacon.getTitle());
        updateBeacon.setLat(beacon.getLat());
        updateBeacon.setLng(beacon.getLng());
        updateBeacon.setMajor(beacon.getMajor());
        updateBeacon.setMinor(beacon.getMinor());
        
        getCurrentSession().update(updateBeacon);
    }
    
    public List<Beacon> getBeacons() {
        hql="from beacon";
        query = getCurrentSession().createQuery(hql);
        
        return (List<Beacon>) query.list();
    }
    
    public Beacon getBeacon(int id) {
        hql="from beacon b where b.id = :id";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        
        Beacon beacon = (Beacon) query.list().get(0);
        return beacon;
    }
    
    public int getBeaconId(Beacon beacon){
        hql="from beacon b where b.title = :title "
                + "and b.major = :majid "
                + "and b.minor = :minid "
                + "and b.lng = :lng "
                + "and b.lat = :lat";
        query = getCurrentSession().createQuery(hql);
        query.setParameter("title", beacon.getTitle());
        query.setParameter("majid", beacon.getMajor());
        query.setParameter("minid", beacon.getMinor());
        query.setParameter("lat", beacon.getLat());
        query.setParameter("lng", beacon.getLng());
        
        int id = ((Beacon)query.list().get(0)).getId();
        
        return id;
    }
    
    public void storeAllBeacons(List<Beacon> beacons) {
        for(Beacon beacon: beacons){
            getCurrentSession().save(beacon);
        }
    }
}
