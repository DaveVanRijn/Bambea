package nl.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import nl.model.Beacon;
import nl.service.BeaconService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles all pages related to the main dashboard
 *
 * @author Wies Kueter & Hugo van Rijswijk
 */
@Controller
public class Dashboard {

    private List<Beacon> beaconList = new ArrayList<Beacon>();

    @Autowired
    private BeaconService beaconService;

    private JSONObject obj;
    private JSONObject beaconObj = new JSONObject();
    private JSONObject beacons = new JSONObject();
    private JSONArray company;
    JSONParser parser = new JSONParser();

    @RequestMapping(value = "/")
    public ModelAndView home() throws IOException {

        ModelAndView dashboardView = new ModelAndView("dashboard");
        String pageTitle = "Dashboard";
        dashboardView.addObject("pageTitle", pageTitle);

        beaconList = beaconService.getBeacons();
        dashboardView.addObject("beaconsList", beaconList);

        return dashboardView;
    }

    /**
     * Ajax request to add a new beacon to the database
     * @param json Beacon data in a json string
     * @throws ParseException 
     */
    @RequestMapping(value = "/beacon/add", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8", produces = "text/plain")
    public @ResponseBody String addBeacon(@RequestBody String json) throws ParseException {
 
        //Parses the json object recieved from the webpage.
        Object obj = parser.parse(json);
        JSONArray array = new JSONArray();
        array.add(obj);
        JSONObject o = (JSONObject)array.get(0);
     
        //Gets the values's from the parsed JSON string
        String title = (String)o.get("title").toString();
        String lat = (String)o.get("lat").toString();
        String lng = (String)o.get("lng").toString();
        int majid = Integer.valueOf(o.get("majid").toString());
        int minid = Integer.valueOf(o.get("minid").toString());
      
        //Creates a beacon and adds it to the DB
        Beacon beacon = new Beacon(title, lat, lng, majid, minid);
        beaconService.addBeacon(beacon);
        // return response.getStatus();
        return String.valueOf(beaconService.getBeaconId(beacon));
    }

    /**
     * Ajax request to edit a beacon already in the database, similar to addBeacon()
     * @param json The beacon to be edited in a JSON string
     * @throws ParseException 
     */
    @RequestMapping(value = "/beacon/edit", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8", produces = "text/plain")
    public @ResponseBody void editBeacon(@RequestBody String json) throws ParseException {
 
        Object obj = parser.parse(json);
        JSONArray array = new JSONArray();
        array.add(obj);
        JSONObject o = (JSONObject)array.get(0);
     
        String title = (String)o.get("title").toString();
        String lat = (String)o.get("lat").toString();
        String lng = (String)o.get("lng").toString();
        int majid = Integer.valueOf(o.get("majid").toString());
        int minid = Integer.valueOf(o.get("minid").toString());
        int id = Integer.valueOf(o.get("id").toString());
      
        Beacon beacon = new Beacon(title, lat, lng, majid, minid);
        
        beaconService.editBeacon(beacon, id);
    }
    
    /**
     * Deletes a beacon
     * @param id The id of the beacon to be deleted
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "/beacon/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteBeacon(@PathVariable Integer id) throws IOException {
        beaconService.deleteBeacon(id);

        return new ModelAndView("redirect:/");
    }
    
    /**
     * To delete a beacon, similar to addBeacon(). Used in the ajax calls on the map on the dashboard
     * @param json
     * @throws ParseException 
     */
    @RequestMapping(value = "/beacon/delete", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8", produces = "text/plain")
    public @ResponseBody void deleteBeaconAjax(@RequestBody String json) throws ParseException {
        Object obj = parser.parse(json);
        JSONArray array = new JSONArray();
        array.add(obj);
        JSONObject o = (JSONObject)array.get(0);
        int id = Integer.valueOf(o.get("id").toString());
        
        beaconService.deleteBeacon(id);
    }

    @RequestMapping(value = "/api")
    public ModelAndView api() throws IOException {

        ModelAndView apiView = new ModelAndView("beacons/api");

        for (Beacon beacon : beaconList) {

            System.out.println(beacon.getTitle());

            obj = new JSONObject();
            company = new JSONArray();
            obj.put(beacon.getTitle(), company);

            beacons.put("lat", beacon.getLat());
            beacons.put("lng", beacon.getLng());

            beaconObj.put(beacon.getTitle(), company);
        }

        apiView.addObject("beacons", obj);
        return apiView;
    }
    
}
