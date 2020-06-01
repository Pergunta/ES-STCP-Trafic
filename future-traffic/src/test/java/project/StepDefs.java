package project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import project.futuretraffic.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.minidev.json.JSONObject;

class checkBusesParam {

     private  Hashtable<String,LinkedList<Float>> streetCoorList = new Hashtable<String,LinkedList<Float>>();
    // JSONObject streetCoorList = new JSONObject();   
    @Autowired
    private BusHttpClient busHttpClient=new BusHttpClient();
    
    public checkBusesParam(){
        // streetCoorList.put("Rua Interior in Porto", "latMax");
        // streetCoorList.put("lat")
        LinkedList<Float> ruainteriorInPorto=new LinkedList<Float>();
        ruainteriorInPorto.add(41.16801f); //latitude minimo
        ruainteriorInPorto.add(41.17101f);//latitude maximo
        ruainteriorInPorto.add(-8.63901f);//longitude minimo
        ruainteriorInPorto.add(-8.64301f);//longitude maximo
        streetCoorList.put("Rua Interior in Porto", ruainteriorInPorto);

        LinkedList<Float> ruaSarahAfonso=new LinkedList<Float>();
        ruaSarahAfonso.add(41.17150f); //latitude minimo
        ruaSarahAfonso.add(41.17260f);//latitude maximo
        ruaSarahAfonso.add(-8.63754f);//longitude minimo
        ruaSarahAfonso.add(-8.64360f);//longitude maximo
        streetCoorList.put("Rua Sarah Afonso", ruaSarahAfonso);

    }
    public int validateID(int ID){
        if(busHttpClient.checkNodeID(ID)) return ID;
        else return -1;

    }
    public String  testEndpoint(){
        System.out.println(busHttpClient.testEndpoint());
        if(busHttpClient.testEndpoint().equals("OK")) return "valid";
        else return "wrong"; 
    }
    public  String isStreetAvailable(String street) {
       
        int b =busHttpClient.getBusInStreet((float)streetCoorList.get(street).toArray()[0], (float)streetCoorList.get(street).toArray()[1], 
                                                (float)streetCoorList.get(street).toArray()[2],(float) streetCoorList.get(street).toArray()[3]);
        
        if(b>0) return "can";
        else return "can't";
    }

    public int parkedBus(String busStation) {
     
        int b=busHttpClient.getBusInStreet((float)streetCoorList.get(busStation).toArray()[0], (float)streetCoorList.get(busStation).toArray()[1], 
                                                (float)streetCoorList.get(busStation).toArray()[2],(float) streetCoorList.get(busStation).toArray()[3]);
        
        
       return b; 
    }
    public int getLimitSpeedCounter(int speedLimit){
        return busHttpClient.getLimitSpeedCount(speedLimit);
    }
}

public class StepDefs {

    private String street ;
    private String actualAnswer;
    private int intAnswer;
    private String busStation;
    private int speedLimit;
    private int numberBuses;
    private int busID;
    private int busParked;
    private checkBusesParam checkBuses=new checkBusesParam();

    private final Logger log = LoggerFactory.getLogger(StepDefs.class);

    
    @Given("Julio is at Rua Central de Francos")
    public void julio_is_at_Rua_Central_de_Francos() {
    // Write code here that turns the phrase above into concrete actions
        String street = "Julio is at Rua Central de Francos";
    }
    @Given("he thinks the next bus is the bus {int} at {string}")    
    public void he_thinks_the_next_bus_is_the_bus_at(Integer busID, String street) {        
        this.street=street;
        this.busID=busID;
    }
    @When("he goes to {string}")    
    public void he_goes_to_(String street) {
        // Write code here that turns the phrase above into concrete actions
        intAnswer=checkBuses.validateID(busID);
//        busHttpClient.getContents();
    }

    @Then("he confirms the next Bus is {int} in {string}")
    public void he_confirms_the_next_Bus_is_in(Integer expectedAnswer, String streetName) {
         // Write code here that turns the phrase above into concrete actions
         assertEquals(expectedAnswer, intAnswer);
    }
    

    @Given("Elisa has the information that  {string} has at least {int} parked buses")   
     public void elisa_has_the_information_that_the_has_parked_buses(String busStation, int busParked) {        // this.manager=
        this.busStation=busStation;
        this.busParked=busParked;
    }
    @When("she checks how many buses are parked at the {string}")  
    public void she_checks_how_many_buses_are_parked_at_the(String string) {        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        intAnswer=checkBuses.parkedBus(busStation);
    }
    @Then("she can confirm there are at least {int} buses parked in  {string}.")    
    public void she_can_confirm_there_are_buses_parked_in_the_Bus_Station(int expectedAnswer,String street) {        // Write code here that turns the phrase above into concrete actions
    	assert(expectedAnswer<=intAnswer);
    }
    @Given("Jose has the information that there are at least {int} Buses  exceeding the speed limit of {int}")    
    public void jose_has_the_information_that_exceeding_the_speed_limit_of(Integer busNumber, Integer speedLimit) {
        // Write code here that turns the phrase above into concrete actions
        this.speedLimit=speedLimit;
        this.numberBuses=busNumber;
    }

    @When("he checks all the buses average speed")
    public void he_checks_all_the_buses_average_speed() {
        // Write code here that turns the phrase above into concrete actions
        intAnswer=checkBuses.getLimitSpeedCounter(speedLimit);
    }

    @Then("he has the confirmation there are at least {int} buses above speed {int}.")    
    public void he_has_the_confirmation_that(int expectedAnswer,int int2) {
        // Write code here that turns the phrase above into concrete actions
    	assert(expectedAnswer<=intAnswer);
    }
    @Given("I set GET buses service endpoint")
    public void i_set_GET_buses_service_endpoint() {
        // Write code here that turns the phrase above into concrete actions
       
    }
    @When("I send a request status")
    public void i_set_a_request() {
        // Write code here that turns the phrase above into concrete actions
        actualAnswer=checkBuses.testEndpoint();
        // headers.add
        // System.out.println("");
    }

    @Then("I recieve a {string} response")
    public void i_recieve_a_valid_response(String expectedAnswer) {
    
        assertEquals(expectedAnswer, actualAnswer);

    }

}
