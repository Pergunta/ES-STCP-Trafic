package project.futuretraffic;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="buses")
public class Bus {
    @Id
    private String node_id;
    private int id;
    private int location_id;
    private float head;
    private float lon;
    private float lat;
    private int speed;
    private String ts; // date and time when it was acquired (ex: 2018-10-08 00:00:00.001)
    private String write_time; // date and time when it was registered (ex: "2018-10-08 00:00:01.638819")
    
    protected Bus() {}

    public Bus(String node_id, int id, int location_id, float head, float lon, float lat, int speed, String ts, String write_time) {
        this.node_id = node_id;
        this.id = id;
        this.location_id = location_id;
        this.head = head;
        this.lon = lon;
        this.lat = lat;
        this.speed = speed;
        this.ts = ts;
        this.write_time = write_time;
    }

    

    public String getNodeId() {
        return this.node_id;
    }

    public void setNodeId(String node_id) {
        this.node_id = node_id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocation_id() {
        return this.location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public float getHead() {
        return this.head;
    }

    public void setHead(float head) {
        this.head = head;
    }

    public float getLon() {
        return this.lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return this.lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getTs() {
        return this.ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getWrite_time() {
        return this.write_time;
    }

    public void setWrite_time(String write_time) {
        this.write_time = write_time;
    }
    
    @Override
    public String toString() {
        String str = String.format("[BUS {id: %d, node_id: %s, location_id: %d, head: %f, lon: %f" +
                    "lat: %f, speed: %d, ts: %s, write_time: %s}]", this.id, this.node_id, this.location_id,
                    this.head, this.lon, this.lat, this.speed, this.ts, this.write_time);

        return str;
    }
}