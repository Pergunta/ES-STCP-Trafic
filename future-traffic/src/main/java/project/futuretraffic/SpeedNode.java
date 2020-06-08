package project.futuretraffic;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class SpeedNode {

    
    @Id
    private int id;
    private int y;
    private String x;
    
    protected SpeedNode() {}

    public SpeedNode(int y,String x) {
        this.x = x;
        this.y = y;
    }

    public void setX(String x) {
        this.x = x;
    }
    public void sety(int y) {
        this.y = y;
    }
    public String getX() {
        return this.x;
    }
    public int gety() {
        return this.y;
    }
  
    @Override
    public String toString() {
        String str = String.format("[yNode {x: %s,y: %d, ]", this.x, this.y);
        return str;
    }
}