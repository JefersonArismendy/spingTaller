package co.edu.poli.springsolucion.services;

import co.edu.poli.springsolucion.persistence.entity.Customer;
import co.edu.poli.springsolucion.persistence.entity.Delivery;
import co.edu.poli.springsolucion.services.DTO.DeliveryDTO;
import org.apache.catalina.Service;

import java.util.List;
import java.util.Optional;

public interface DeliveryService {


    List<Delivery> findAll();


    Optional<Delivery> getDeliveryById(Long deliveryid);

    Delivery create(DeliveryDTO deliveryDTO);

    // Update (Método para actualizar un cliente existente)
    Delivery update(Long deliveryid, DeliveryDTO deliveryDTO);

    // Delete (Método para eliminar un cliente por su ID)
    boolean delete(Long customerid);
}
