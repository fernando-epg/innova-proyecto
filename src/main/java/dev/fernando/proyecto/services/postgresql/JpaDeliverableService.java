package dev.fernando.proyecto.services.postgresql;

import dev.fernando.proyecto.dto.DeliverableDTO;
import dev.fernando.proyecto.interfaces.IDeliverableGenericService;
import dev.fernando.proyecto.persistence.postgresql.entity.DeliverablesPostgreSQL;
import dev.fernando.proyecto.persistence.postgresql.repository.IPostgreSQLDeliverableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class JpaDeliverableService implements IDeliverableGenericService<DeliverableDTO,Long> {
    
    private final IPostgreSQLDeliverableRepository deliverableRepository;
    
    @Autowired
    public JpaDeliverableService(IPostgreSQLDeliverableRepository deliverableRepository) {
        this.deliverableRepository = deliverableRepository;
    }
    
    @Override
    public DeliverableDTO save(DeliverableDTO dto) {
        DeliverablesPostgreSQL newDeliverable = dtoConverter(dto);
        DeliverablesPostgreSQL result = deliverableRepository.save(newDeliverable);
        return entityConverter(result);
    }
    
    private DeliverableDTO entityConverter(DeliverablesPostgreSQL result) {
        return new DeliverableDTO(
                result.getId(),
                result.getCourseId(),
                result.getStudentId(),
                result.getGrade(),
                result.getGradeName(),
                result.getPonderation()
        );
    }
    
    private DeliverablesPostgreSQL dtoConverter(DeliverableDTO dto) {
        return new DeliverablesPostgreSQL(
                (Long) dto.getCourseId(),
                (Long) dto.getStudentId(),
                dto.getGrade(),
                dto.getGradeName(),
                dto.getPonderation()
        );
    }
    
    @Override
    public Optional<DeliverableDTO> findById(Long id) {
        return Optional.empty();
    }
    
    @Override
    public List<DeliverableDTO> findAll() {
        return null;
    }
    
    @Override
    public void deleteById(Long id) {
    
    }
    
    @Override
    public void delete(DeliverableDTO entity) {
    
    }
}
