package utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqUtils {

    private static ConnectionFactory connectionFactory;
    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("8.135.34.162");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
    }

    //定义一个创建连接对象的方法
    public static Connection getConnection(){
        try {
            Connection connection = connectionFactory.newConnection();
            return connection;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnectionAndChanel(Channel channel,Connection connection){
        try {
            if (channel != null) channel.close();
            if (connection != null)connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
