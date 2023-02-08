package nelson.tacocloud.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import nelson.tacocloud.model.TacoOrder;

@Service
public class KafkaOrderMessagingService implements OrderMessagingService {

    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaOrderMessagingService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendOrder(TacoOrder tacoOrder) {
        kafkaTemplate.send("tacocloud.orders.topic", "test", tacoOrder.getId().toString());
    }

}
