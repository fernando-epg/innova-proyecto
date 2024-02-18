package dev.fernando.proyecto.services.postgresql;

import dev.fernando.proyecto.dto.DeliverableDTO;
import dev.fernando.proyecto.interfaces.IDeliverableGenericService;
import dev.fernando.proyecto.persistence.postgresql.entity.DeliverablesPostgreSQL;
import dev.fernando.proyecto.persistence.postgresql.repository.IPostgreSQLDeliverableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JpaDeliverableService implements IDeliverableGenericService<DeliverableDTO,Long> {
    
    private final IPostgreSQLDeliverableRepository repository;
    
    @Autowired
    public JpaDeliverableService(IPostgreSQLDeliverableRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public DeliverableDTO save(DeliverableDTO dto) {
        DeliverablesPostgreSQL newDeliverable = dtoConverter(dto);
        DeliverablesPostgreSQL result = repository.save(newDeliverable);
        return entityConverter(result);
    }
    
    @Override
    public Optional<DeliverableDTO> findById(Long id) {
        Optional<DeliverablesPostgreSQL> deliverable = repository.findById(id);
        if(deliverable.isPresent()) {
            return Optional.of(entityConverter(deliverable.get()));
        } else {
            return Optional.empty();
        }
    }
    
    @Override
    public List<DeliverableDTO> findAll() {
        List<DeliverablesPostgreSQL> result = repository.findAll();
        List<DeliverableDTO> resultDto = new ArrayList<>();
        for (DeliverablesPostgreSQL deliverable : result) {
            DeliverableDTO dtoEntry = entityConverter(deliverable);
            resultDto.add(dtoEntry);
        }
        return resultDto;
    }
    
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
    @Override
    public void delete(DeliverableDTO dto) {
        repository.delete(dtoConverter(dto));
    }
    
    private DeliverablesPostgreSQL dtoConverter(DeliverableDTO dto) {
        if(dto.getId() != null) {
            return new DeliverablesPostgreSQL(
                    Long.valueOf(dto.getId().toString()),
                    Long.valueOf(dto.getCourseId().toString()),
                    Long.valueOf(dto.getStudentId().toString()),
                    dto.getGrade(),
                    dto.getGradeName(),
                    dto.getPonderation()
            );
        }
        return new DeliverablesPostgreSQL(
                Long.valueOf(dto.getCourseId().toString()),
                Long.valueOf(dto.getStudentId().toString()),
                dto.getGrade(),
                dto.getGradeName(),
                dto.getPonderation()
        );
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
    
    
}
