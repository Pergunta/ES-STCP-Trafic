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

@Service
public class ConsumerData {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    private final Properties con_properties = new Properties();
    private Consumer<Long, String> kafkaConsumer;


    /**
     * This means to get the bean called busRepository
     * Which is auto generated by Spring, we will use it to handle the data
     */
    @Autowired 
    private BusRepository busRepository;

    @PostConstruct
    public void init(){
        log.info("Initializing consumer controller");
        con_properties.put("bootstrap.servers", "192.168.160.103:9092");
        con_properties.put("key.deserializer", StringDeserializer.class.getName());
        con_properties.put("value.deserializer" , StringDeserializer.class.getName());
        con_properties.put("group.id" , "p22");
    }

    @KafkaListener(topics="esp22", groupId="p22")
    public void consume(String message){
        String[] data = message.split(",");
        int id = Integer.valueOf(data[0]);
        String node_id = data[1];
        int location_id = Integer.valueOf(data[2]);
        float head = !data[3].equals("") ? Float.valueOf(data[3]) : 0;
        float lon = Float.valueOf(data[4]);
        float lat = Float.valueOf(data[5]);
        int speed = !data[6].equals("") ? Integer.valueOf(data[6]) : 0;
        String ts = data[7];
        String write_time = data[8];

        Bus b = new Bus(node_id, id, location_id, head, lon, lat, speed, ts, write_time);
        log.info(b.toString());
        busRepository.save(b);
    }

    // Overriding default boostrap server for the kafka listener callback stuff
    @Bean
    public ConsumerFactory<?, ?> kafkaConsumerFactory(KafkaProperties properties) {
        Map<String, Object> props = properties.buildConsumerProperties();
        props.put(org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "192.168.160.103:9092");
        return new DefaultKafkaConsumerFactory<>(props);
    }



}
