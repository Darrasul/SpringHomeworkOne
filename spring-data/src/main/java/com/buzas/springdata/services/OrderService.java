package com.buzas.springdata.services;

import com.buzas.springdata.orders.LineItem;
import com.buzas.springdata.orders.Order;
import com.buzas.springdata.orders.OrderRepository;
import com.buzas.springdata.users.UserDto;
import com.buzas.springdata.users.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final PasswordEncoder encoder;

    private final OrderRepository orderRepo;
    private final UserDtoMapper mapper;
    @Autowired
    private final UserService userService;

    public List<Order> showAll() {
        return orderRepo.findAll();
    }

    public List<Order> showAllByUserId(Long id) {
        return orderRepo.showAllByCustomerId(id);
    }

    public void createOrder(Set<LineItem> items, UserDto userDto) {
        orderRepo.save(new Order(items, mapper.map(userDto, encoder)));
    }

    public void addToOrder(LineItem item, Long id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new FindException("No such order"));
        order.addToOrder(item);
        userService.removeOrder(orderRepo.findById(id).get());
        userService.addOrder(new Order(order.getOrderedItems(), order.getCustomer()));
    }

    public void removeFromOrder(LineItem item, Long id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new FindException("No such order"));
        order.removeFromOrder(item);
        userService.removeOrder(orderRepo.findById(id).get());
        userService.addOrder(new Order(order.getOrderedItems(), order.getCustomer()));
    }

    public void deleteOrderById(Long id) {
        orderRepo.deleteAllById(id);
    }
}
