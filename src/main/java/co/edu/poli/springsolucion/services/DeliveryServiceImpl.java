package co.edu.poli.springsolucion.services;

import co.edu.poli.springsolucion.persistence.entity.Delivery;
import co.edu.poli.springsolucion.persistence.entity.DeliveryCompany;
import co.edu.poli.springsolucion.persistence.entity.DeliveryType;
import co.edu.poli.springsolucion.persistence.repository.DeliveryRepository;
import co.edu.poli.springsolucion.services.DTO.DeliveryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements  DeliveryService {


    private  final DeliveryRepository deliveryRepository;
    // Validación personalizada para el campo "type"
    private boolean isValidDeliveryType(DeliveryType type) {
        return type != null;
    }

    // Validación personalizada para el campo "company"
    private boolean isValidDeliveryCompany(DeliveryCompany company) {
        return company != null;
    }

    // Validación personalizada para la duración
    private boolean isValidDeliveryDuration(int duration) {
        return duration >= 1 && duration <= 60; // Verifica que esté en el rango especificado
    }

    @Override
    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }


    @Override
    public Optional<Delivery> getDeliveryById(Long deliveryid) {
        return deliveryRepository.findById(deliveryid);
    }


    @Override
    public Delivery create(DeliveryDTO deliveryDTO) {

        // Crear un objeto Delivery a partir de DeliveryRequest
        Delivery delivery = new Delivery();
        delivery.setCompany(deliveryDTO.getCompany());
        delivery.setDuration(deliveryDTO.getDuration());
        delivery.setType(deliveryDTO.getType());

        // Validar el campo "type"
        if (!isValidDeliveryType(delivery.getType())) {
            throw new IllegalArgumentException("El valor del campo 'type' no es válido.");
        }

        // Validar el campo "company"
        if (!isValidDeliveryCompany(delivery.getCompany())) {
            throw new IllegalArgumentException("El valor del campo 'company' no es válido.");
        }

        // Validar la duración
        if (!isValidDeliveryDuration(delivery.getDuration())) {
            throw new IllegalArgumentException("La duración no es válida.");
        }

            return deliveryRepository.save(delivery);

    }

    @Override
    // Update (Método para actualizar un cliente existente)
    public Delivery update(Long deliveryid, DeliveryDTO deliveryDTO) {
        Delivery existingDelivery = deliveryRepository.findById(deliveryid).orElse(null);
        if (existingDelivery != null) {
            // Actualizar los campos del cliente existente con los valores del cliente actualizado
            existingDelivery.setCompany(deliveryDTO.getCompany());
            existingDelivery.setDuration(deliveryDTO.getDuration());
            existingDelivery.setType(deliveryDTO.getType());

            // Validar el campo "type"
            if (!isValidDeliveryType(deliveryDTO.getType())) {
                throw new IllegalArgumentException("El valor del campo 'type' no es válido.");
            }

            // Validar el campo "company"
            if (!isValidDeliveryCompany(deliveryDTO.getCompany())) {
                throw new IllegalArgumentException("El valor del campo 'company' no es válido.");
            }

            // Validar la duración
            if (!isValidDeliveryDuration(deliveryDTO.getDuration())) {
                throw new IllegalArgumentException("La duración no es válida.");
            }


            // Guardar el cliente actualizado en la base de datos
            return deliveryRepository.save(existingDelivery);
        }
        return null; // Devolver null si el cliente no existe
    }


    @Override
    // Delete (Método para eliminar un cliente por su ID)
    public boolean delete(Long deliveryid) {
        Delivery deliveryToDelete = deliveryRepository.findById(deliveryid).orElse(null);
        if (deliveryToDelete != null) {
            deliveryRepository.delete(deliveryToDelete);
            return true; // Devolver true si se eliminó con éxito
        }
        return false; // Devolver false si el cliente no existe
    }

}
