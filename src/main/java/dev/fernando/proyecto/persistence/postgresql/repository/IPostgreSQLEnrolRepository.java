package dev.fernando.proyecto.persistence.postgresql.repository;

import dev.fernando.proyecto.persistence.postgresql.entity.EnrolPostgreSQL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostgreSQLEnrolRepository extends JpaRepository<EnrolPostgreSQL,Long> {
}
