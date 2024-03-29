package nelson.tacocloud.reciever;

import lombok.RequiredArgsConstructor;
import nelson.tacocloud.repository.OrderRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import nelson.tacocloud.model.TacoOrder;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderListener {
    private final OrderRepository orderRepository;

    @RabbitListener(queues = "kitchens.central")
    public void recieveOrderToKitchen(TacoOrder taco) {
        log.info("From Kitchen: " + taco.getId());
    }

    @RabbitListener(queues = "tacocloud.central")
    public void recieveOrder(TacoOrder taco) {
        log.info("From Order: " + taco.getId());
    }

    @RabbitListener(queues = "direct.queue")
    public void recieveDirect(String test) {
        log.info("Message: " + test);
    }


    @KafkaListener(topics = "tacocloud.orders.topic")
    public void handle(String tacoOrderId, ConsumerRecord<String, String> record){
        log.info("Consumer Info: " + record.partition() + " - " + record.timestamp());
        log.info("kafka: " + tacoOrderId);
        Optional<TacoOrder> taco = orderRepository.findById(Long.valueOf(tacoOrderId));
        taco.ifPresent(value -> log.info("Taco: " + value.getTacos().toString()));
    }
}
