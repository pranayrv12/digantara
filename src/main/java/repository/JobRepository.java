package repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

	@Query("SELECT j FROM Job j WHERE j.status = 'ACTIVE' AND j.nextRun <= :now")
	List<Job> retrieveJobsToExecute(LocalDateTime now);
}
