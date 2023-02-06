package nelson.tacocloud.service;

import nelson.tacocloud.model.TacoOrder;

public interface OrderMessagingService {
    void sendOrder(TacoOrder tacoOrder);
}
