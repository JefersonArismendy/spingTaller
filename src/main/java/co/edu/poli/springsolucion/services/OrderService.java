package co.edu.poli.springsolucion.services;


import co.edu.poli.springsolucion.persistence.entity.OrderEntity;
import co.edu.poli.springsolucion.services.DTO.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<OrderEntity> findAll();

    Optional<OrderEntity> getOrderById(Long orderId);

    OrderEntity create(OrderDTO orderDTO);

    OrderEntity edit(Long orderId, OrderDTO orderDTO);

    boolean delete(Long orderId);
}
