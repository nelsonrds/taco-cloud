package nelson.tacocloud.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import nelson.tacocloud.model.TacoOrder;
import nelson.tacocloud.model.User;
import nelson.tacocloud.repository.OrderRepository;
import nelson.tacocloud.util.OrderProps;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderRepository orderRepository;
    private OrderProps orderProps;

    public OrderController(OrderRepository orderRepository, OrderProps orderProps) {
        this.orderRepository = orderRepository;
        this.orderProps = orderProps;
    }

    @GetMapping("/current")
    @PreAuthorize("hasRole('USER')")
    public String orderForm(@AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
        log.info("pageSize: " + orderProps.getPageSize());

        model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));

        return "orderForm";
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus,
            @AuthenticationPrincipal User user) {
        if (errors.hasErrors())
            return "orderForm";

        log.info("Order Submitted: {}", order);
        order.setUser(user);
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/design";
    }

}
