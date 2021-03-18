package helloworld;

import com.rabbitmq.client.*;
import org.junit.Test;
import utils.RabbitMqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Customer {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtils.getConnection();

        Channel channel = connection.createChannel();
        channel.queueDeclare("hello",false,false,false,null);

        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println("============== " + new String(body));
            }
        });
//        channel.close();
//        connection.close();
    }
}
