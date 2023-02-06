package nelson.tacocloud.reciever;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nelson.tacocloud.model.TacoOrder;

@Component
public class RabbitOrderReciever {
    
    private RabbitTemplate rabbit;
    private MessageConverter converter;

    @Autowired
    public RabbitOrderReciever(RabbitTemplate rabbitTemplate){
        this.rabbit = rabbitTemplate;
        this.converter = rabbitTemplate.getMessageConverter();
    }

    public TacoOrder recieveOrder(){
        Message message = rabbit.receive("tacocloud.central");
        System.out.println(message);
        return message != null ? (TacoOrder) converter.fromMessage(message) : null;
    }

}
