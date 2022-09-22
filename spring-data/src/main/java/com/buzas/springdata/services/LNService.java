package com.buzas.springdata.services;

import com.buzas.springdata.orders.LNRepository;
import com.buzas.springdata.orders.LineItem;
import com.buzas.springdata.products.Product;
import com.buzas.springdata.products.ProductDto;
import com.buzas.springdata.products.ProductDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LNService {

    private final LNRepository lnRepo;
    private final ProductDtoMapper mapper;

    public List<LineItem> showAll() {
        return lnRepo.findAll();
    }

    public List<LineItem> showAllByOrderId(Long id) {
        return lnRepo.findAllByOrderId(id);
    }

    public LineItem findById(Long id) {
        return lnRepo.findById(id).orElseThrow(() -> new FindException("No such item"));
    }

//    Не смог заставить этот метод на стороне репозитория. Хотелось бы знать, можно ли как-то запустить
//    методы removeItemByIdFromOrderById и addItemByIdToOrderById. Ведь, я так понял, задача в том,
//    чтобы не удалить предмет или добавить его(как будет сделано чуть далее), а удалить или установить связь между
//    предметом и заказом, т.к. данный предмет может находиться в нескольких заказах. Сделал методы в OrderService на
//    замену, но они так же не выглядят верными на мой взгляд
    public void removeItemByIdFromOrderById(Long itemId) {
        lnRepo.removeLineItemById(itemId);
    }

    public void addItemByIdToOrderById(Long itemId, Long orderId) {
        lnRepo.addByIdToOrderId(itemId, orderId);
    }

    public LineItem createLN(ProductDto productDto) {
        LineItem item = new LineItem(mapper.map(productDto));
        lnRepo.save(item);
        return item;
    }

    public LineItem createLN(Product product) {
        LineItem item = new LineItem(product);
        lnRepo.save(item);
        return item;
    }

    public void deleteById(Long id) {
        lnRepo.deleteById(id);
    }
}
