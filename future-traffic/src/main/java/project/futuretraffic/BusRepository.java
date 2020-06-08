package project.futuretraffic;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import project.futuretraffic.Bus;

public interface BusRepository extends CrudRepository<Bus, Integer>{
    
    //@Query("select node_id from buses  where node_id= ?1")
    @Query(value="SELECT * FROM buses a where a.node_id=?1",nativeQuery = true)
    List<Bus> findBus(String node_id);
    @Query(value="SELECT speed FROM buses a where a.node_id=?1",nativeQuery = true)
    int findSpeed(String node_id);
    @Query(value="SELECT node_id,speed FROM buses ",nativeQuery = true)
    List<String> findSpeedNode();
}

