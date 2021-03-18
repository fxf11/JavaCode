package helloworld;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;
import utils.RabbitMqUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * 直连模型
 */
public class Provider {

    //生产消息
    @Test
    public void testSendMessage() throws IOException, TimeoutException {

        //首先需要先连接到rabbitmq的服务器
        //创建连接mq的连接工厂对象
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        connectionFactory.setHost("8.135.34.162");
//        connectionFactory.setPort(5672);
//        //设置连接到哪个虚拟主机
//        connectionFactory.setVirtualHost("/ems");
//        //连接虚拟主机需要权限才能连接到，所以需要设置访问的用户名和密码
//        connectionFactory.setUsername("ems");
//        connectionFactory.setPassword("123");
//        //获取连接对象
//        Connection connection = connectionFactory.newConnection();

        Connection connection = RabbitMqUtils.getConnection();
        //需要通过通道才能传递消息，所以要获取到连接中的通道对象
        Channel channel = connection.createChannel();
        //通道需要绑定到相应的消息队列
        //durable：定义队列是否需要持久化，
        //exclusive：是否独占队列，这个队列是否当前连接才能使用
        //autoDelete：是否在消息完成后自动删除消息
        //arguments：额外参数
        channel.queueDeclare("hello",false,false,false,null);//如果队列不存在，则会自动创建

        //发布消息
        channel.basicPublish("","hello", MessageProperties.PERSISTENT_BASIC,"hello rabbitmq".getBytes());

//        //关闭通道
//        channel.close();
//        //关闭连接
//        connection.close();

        RabbitMqUtils.closeConnectionAndChanel(channel,connection);
    }
}
