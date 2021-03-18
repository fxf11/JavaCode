package workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import utils.RabbitMqUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        //获取通道对象
        Channel channel = connection.createChannel();
        //通过通道声明队列
        channel.queueDeclare("work",true,false,false,null);
        //发布消息，生产消息
        for (int i = 0; i < 10; i++){
            channel.basicPublish("","work", null,(i+"hello work Queue").getBytes());
        }


        RabbitMqUtils.closeConnectionAndChanel(channel,connection);
    }
}
