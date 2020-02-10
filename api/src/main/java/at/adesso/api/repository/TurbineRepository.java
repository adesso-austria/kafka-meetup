package at.adesso.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import at.adesso.api.model.Turbine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TurbineRepository extends JpaRepository<Turbine, Long> {
}
