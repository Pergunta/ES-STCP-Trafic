package project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import project.futuretraffic.Bus;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class BusHttpClient {

    private final String SERVER_URL = "http://192.168.160.103:6080/api";
    private final String THINGS_ENDPOINT = "/all";
    
    @LocalServerPort
    private int port=8888;
   
    @Autowired
    private final RestTemplate restTemplate = new RestTemplate();

    protected BusHttpClient() {}  // Default constructor to make JPA happy

    private String thingsEndpoint() {
        return SERVER_URL + THINGS_ENDPOINT;
    }
    private Bus [] getContents() {
        return restTemplate.getForEntity(thingsEndpoint(), Bus[].class).getBody();
    }
    public double getAVGSpeed(){
        double avgspeed = 0; 
        Bus  [] buses=getContents();
        for ( Bus b : buses){
            //System.out.println("Speed="+b.getSpeed());
            avgspeed+=b.getSpeed();

        }
        return avgspeed/buses.length;
    }
    public int getLimitSpeedCount( int limit){
        int counter=0;
        Bus  [] buses=getContents();
        for ( Bus b : buses){
            //System.out.println("Speed="+b.getSpeed());
            if ( b.getSpeed()>limit){
                counter ++;
            }
        }
        return counter;
    }
    public int getBusInStreet(float minLat,float maxLat ,float minLon, float maxLon){
        
        int busesNumb=0;
        Bus  [] buses=getContents();
        for ( Bus b : buses){
            // System.out.println("Lat="+b.getLat()  + "  Lon=" + b.getLon());
            // System.out.println("LatMin=" + minLat + "    latMax =" + maxLat + "    minLon=" + minLon + "    maxLon = "+maxLon);
            // System.out.println((b.getLat()>=minLat) + "  &&  " +(b.getLat()<=maxLat) +"  &&  " +(b.getLon()<=minLon) +"  && " +(b.getLat()>=maxLon)) ;
            if((b.getLat()>=minLat && b.getLat()<=maxLat) &&( b.getLon()<=minLon && b.getLon()>=maxLon )){
                // System.out.println("Entrou aqui");
                busesNumb++;
            }
        }
        System.out.println(busesNumb);
        return busesNumb;
    }
    public boolean checkNodeID(int ID){
        String nodeID="00000000-0000-0000-0000-00000000"+ID;
        Bus  [] buses=getContents();
        for ( Bus b : buses){
            if(b.getNodeId().equals(nodeID))return true;
        }
        return false;
    }
    public String testEndpoint(){
        return restTemplate.getForEntity(thingsEndpoint(), Bus[].class).getStatusCode().name();
    }
    public void clean() {
        restTemplate.delete(thingsEndpoint());
    }
}