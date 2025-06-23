package dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JobRequest {

	@NotBlank(message = "Job Name Required.")
	private String name;

	private String description;

	private String cronExpression;

	@NotBlank(message = "Schedule Type Required.")
	private String scheduleType;

	public JobRequest(String name, String description, String scheduleType) {
		this.name = name;
		this.description = description;
		this.scheduleType = scheduleType;
	}
}
