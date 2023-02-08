package nelson.tacocloud.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    static final String topicExchangeName = "tacocloud.order";
    static final String queueName = "kitchens.central";
    static final String queueOrderName = "tacocloud.central";
    static final String directQueueName = "direct.queue";

    @Bean("que")
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean("order")
    Queue queueOrder() {
        return new Queue(queueOrderName, false);
    }

    @Bean("direct")
    Queue directQueue() {
        return new Queue(directQueueName, false);
    }

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange(topicExchangeName);
        // return new TopicExchange(topicExchangeName);
    }

    // TopicExchange
    // DirectExchange

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("direct");
    }

    @Bean
    Binding binding(@Qualifier("que") Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    Binding binding2(@Qualifier("order") Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    Binding binding3(@Qualifier("direct") Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with("hello");
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;

    }

}
