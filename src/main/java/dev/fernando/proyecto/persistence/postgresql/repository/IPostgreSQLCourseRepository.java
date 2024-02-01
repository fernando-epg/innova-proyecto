package dev.fernando.proyecto.persistence.postgresql.repository;

import dev.fernando.proyecto.persistence.postgresql.entity.CoursePostgreSQL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostgreSQLCourseRepository extends JpaRepository<CoursePostgreSQL,Long> {
}
