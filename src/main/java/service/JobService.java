package service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import dto.JobRequest;
import model.Job;

public interface JobService {

	public List<Job> retrieveAllJobs();

	public void executeScheduledJobs();

	public Job createJob(JobRequest request);

	public Optional<Job> retrieveJobById(Long id);

	public LocalDateTime calculateNextRun(String scheduleType);

	public void performJobTask(Job job) throws InterruptedException;
}
