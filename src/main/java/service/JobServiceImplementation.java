package service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import dto.JobRequest;
import model.Job;
import repository.JobRepository;

@Service
public class JobServiceImplementation implements JobService {

	@Autowired
	private JobRepository jobRepository;

	public List<Job> retrieveAllJobs() {
		return jobRepository.findAll();
	}

	public Optional<Job> retrieveJobById(Long id) {
		return jobRepository.findById(id);
	}

	public LocalDateTime calculateNextRun(String scheduleType) {
		LocalDateTime now = LocalDateTime.now();
		return switch (scheduleType.toUpperCase()) {
		case "DAILY" -> now.plusDays(1);
		case "WEEKLY" -> now.plusWeeks(1);
		case "MONTHLY" -> now.plusMonths(1);
		default -> now.plusHours(1);
		};
	}

	@Scheduled(fixedRate = 60000)
	public void executeScheduledJobs() {

		LocalDateTime now = LocalDateTime.now();
		List<Job> jobsToExecute = jobRepository.retrieveJobsToExecute(now);

		for (Job job : jobsToExecute) {
			executeJobAsync(job);
		}
	}

	public Job createJob(JobRequest request) {

		Job job = new Job(request.getName(), request.getDescription(), request.getScheduleType());

		if (request.getCronExpression() != null) {
			job.setCronExpression(request.getCronExpression());
		}
		job.setNextRun(calculateNextRun(job.getScheduleType()));

		return jobRepository.save(job);
	}

	private void executeJobAsync(Job job) {

		CompletableFuture.runAsync(() -> {
			try {
				// Update Last Run Time.
				job.setLastRun(LocalDateTime.now());

				// Execute The Job.
				performJobTask(job);

				// Calculate Next Run Time.
				job.setNextRun(calculateNextRun(job.getScheduleType()));
				jobRepository.save(job);

				System.out.println("Job Executed Successfully: " + job.getName());

			} catch (Exception e) {
				job.setStatus("FAILED");
				jobRepository.save(job);
				System.err.println("Job Execution Failed: " + job.getName() + " - " + e.getMessage());
			}
		});
	}

	// Dummy Job Implementation.
	public void performJobTask(Job job) throws InterruptedException {
		switch (job.getName().toLowerCase()) {
		case "email-notification":
			Thread.sleep(1000); // Simulate Email Notification.
			System.out.println("Email Notification Sent.");
			break;
		case "data-processing":
			Thread.sleep(2000); // Simulate Data Processing.
			System.out.println("Data Processing completed.");
			break;
		case "report-generation":
			Thread.sleep(1500); // Simulate Report Generation.
			System.out.println("Report Generated.");
			break;
		default:
			Thread.sleep(500);
			System.out.println("Default Job Executed: " + job.getName());
		}
	}
}
