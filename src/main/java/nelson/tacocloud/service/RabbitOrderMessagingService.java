package nelson.tacocloud.service;

import org.hibernate.annotations.SourceType;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nelson.tacocloud.model.TacoOrder;

@Service
public class RabbitOrderMessagingService implements OrderMessagingService {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitOrderMessagingService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOrder(TacoOrder tacoOrder) {
        rabbitTemplate.convertAndSend("tacocloud.order", tacoOrder, new MessagePostProcessor() {

            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties props = message.getMessageProperties();
                props.setHeader("X_ORDER_SOURCE", "WEB");
                return message;
            }

        });
    }

    public void directSend(String test) {
        rabbitTemplate.convertAndSend("direct", "hello", test);
    }

}
