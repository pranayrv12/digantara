package model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jobs")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	private String description;

	private LocalDateTime lastRun;

	private LocalDateTime nextRun;

	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(nullable = false)
	private String scheduleType; // DAILY, WEEKLY, MONTHLY, CRON

	private String cronExpression;

	private String status = "ACTIVE"; // ACTIVE, FAILED, COMPLETED

	public Job(String name, String description, String scheduleType) {
		this.name = name;
		this.description = description;
		this.scheduleType = scheduleType;
		this.createdAt = LocalDateTime.now();
	}
}
