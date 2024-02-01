package dev.fernando.proyecto.persistence.postgresql.repository;

import dev.fernando.proyecto.persistence.postgresql.entity.StudentPostgreSQL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostgreSQLStudentRepository extends JpaRepository<StudentPostgreSQL,Long> {
}
