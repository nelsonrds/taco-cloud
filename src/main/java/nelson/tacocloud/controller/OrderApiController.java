package nelson.tacocloud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import nelson.tacocloud.model.TacoOrder;
import nelson.tacocloud.reciever.RabbitOrderReciever;
import nelson.tacocloud.repository.OrderRepository;
import nelson.tacocloud.service.KafkaOrderMessagingService;
import nelson.tacocloud.service.RabbitOrderMessagingService;

@RestController
@RequestMapping("/api/orders")
public class OrderApiController {

    private OrderRepository orderRepository;
    private RabbitOrderMessagingService rabbitOrderMessagingService;
    private RabbitOrderReciever reciever;

    private KafkaOrderMessagingService kafkaOrderMessagingService;

    public OrderApiController(OrderRepository orderRepository,
            RabbitOrderMessagingService rabbitOrderMessagingService, RabbitOrderReciever reciever, KafkaOrderMessagingService kafkaOrderMessagingService) {
        this.orderRepository = orderRepository;
        this.rabbitOrderMessagingService = rabbitOrderMessagingService;
        this.reciever = reciever;
        this.kafkaOrderMessagingService = kafkaOrderMessagingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TacoOrder postOrder(@RequestBody TacoOrder taco) {
        TacoOrder taco2 = orderRepository.save(taco);
        rabbitOrderMessagingService.sendOrder(taco2);
        rabbitOrderMessagingService.directSend("test");

        kafkaOrderMessagingService.sendOrder(taco2);

        return taco2;
    }

    @GetMapping
    public TacoOrder getOrder() {
        return this.reciever.recieveOrder();
    }
}
