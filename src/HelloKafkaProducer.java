/**
 * Created by chetan on 8/8/16.
 */
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * Created by user on 8/4/14.
 */
public class HelloKafkaProducer {
    final static String TOPIC = "javatest";




    public static void main(String[] argv) throws IOException {
        Properties properties = new Properties();
        properties.put("metadata.broker.list","localhost:9092");
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.ByteArraySerializer");
        ProducerConfig producerConfig=new ProducerConfig(properties);
        Producer<String,byte[]> producer = new Producer<String, byte[]>(producerConfig);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        try {
            oos.writeObject("djkvbsfbvk");
        } finally {
            oos.close();
        }

        byte[] bytes = baos.toByteArray();
        SimpleDateFormat sdf = new SimpleDateFormat();
        KeyedMessage<String, byte[]> message =new KeyedMessage<String, byte[]>(TOPIC,bytes);
        producer.send(message);
        producer.close();
    }
}