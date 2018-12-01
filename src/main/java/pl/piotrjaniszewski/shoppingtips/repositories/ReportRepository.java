package pl.piotrjaniszewski.shoppingtips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piotrjaniszewski.shoppingtips.domain.Report;
import pl.piotrjaniszewski.shoppingtips.domain.ReportedObject;

import java.util.Set;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
    Set<Report> findAllByReportedObject(ReportedObject reportedObject);

}
