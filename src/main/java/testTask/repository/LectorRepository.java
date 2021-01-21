package testTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import testTask.entity.Lector;

import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {
    @Query("SELECT l FROM Lector l WHERE l.firstName LIKE %:template% OR l.lastName LIKE %:template%")
    List<Lector> findAllByTemplate(@Param("template") String template);
}
