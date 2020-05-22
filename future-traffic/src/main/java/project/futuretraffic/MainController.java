package project.futuretraffic;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.ConsumerFactory;

import org.springframework.context.annotation.Bean;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;



@RestController // This means that this class is a Controller
@RequestMapping(path="/api") // This means that the url for all API's in this controller start with /api
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    private final Properties con_properties = new Properties();
    private Consumer<Long, String> kafkaConsumer;


    /**
     * This means to get the bean called busRepository
     * Which is auto generated by Spring, we will use it to handle the data
     */
    @Autowired 
    private BusRepository busRepository;

    // Get all buses
    @CrossOrigin(origins = "http://192.168.160.103:6030/api/all")
    @GetMapping(path="/all")
    public Iterable<Bus> getAllBuses(){
        // This returns a JSON with all the buses        
        return busRepository.findAll();
    }

}