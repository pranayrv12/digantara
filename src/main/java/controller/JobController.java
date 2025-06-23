package controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.JobRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import model.Job;
import service.JobServiceImplementation;

@RestController
@RequestMapping("/jobs")
public class JobController {

	@Autowired
	private JobServiceImplementation jobService;

	@GetMapping
	@Operation(description = "API For Retrieving Jobs.")
	public ResponseEntity<List<Job>> getAllJobs() {

		List<Job> jobs = jobService.retrieveAllJobs();

		return new ResponseEntity<List<Job>>(jobs, HttpStatus.ACCEPTED);
	}

	@GetMapping("/{id}")
	@Operation(description = "API For Retrieving Job with ID.")
	public ResponseEntity<Job> getJobById(@PathVariable Long id) {

		Optional<Job> job = jobService.retrieveJobById(id);

		if (!job.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(job.get(), HttpStatus.ACCEPTED);
		}
	}

	@PostMapping
	@Operation(description = "API For Creating Job.")
	public ResponseEntity<Job> createJob(@Valid @RequestBody JobRequest request) {

		Job job = jobService.createJob(request);

		return new ResponseEntity<Job>(job, HttpStatus.ACCEPTED);
	}
}
