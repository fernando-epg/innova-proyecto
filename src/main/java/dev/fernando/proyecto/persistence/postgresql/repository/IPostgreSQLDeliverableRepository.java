package dev.fernando.proyecto.persistence.postgresql.repository;

import dev.fernando.proyecto.interfaces.IDeliverableGenericService;
import dev.fernando.proyecto.persistence.postgresql.entity.DeliverablesPostgreSQL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostgreSQLDeliverableRepository extends JpaRepository<DeliverablesPostgreSQL,Long> {
}
