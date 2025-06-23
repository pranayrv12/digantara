# Digantara
Digantara OA

### Quick Setup
```bash
# Clone and Run
mvn clean install
mvn spring-boot:run

# Access
# API: http://localhost:8080
# Swagger UI: http://localhost:8080/swagger-ui.html
# H2 Console: http://localhost:8080/h2-console
```

### Test APIs
```bash
# Retrieve All Jobs
curl http://localhost:8080/jobs

# Retrieve Job By ID
curl http://localhost:8080/jobs/1

# Create A Job
curl -X POST http://localhost:8080/jobs \
  -H "Content-Type: application/json" \
  -d '{"name": "email-notification", "description": "Daily Emails", "scheduleType": "DAILY"}'
```

### Key Features
- ✅ **Job Scheduling**: The microservice uses Spring's `@Scheduled` annotation to enable real scheduling, dynamically calculating nextRun after each execution. This supports flexible intervals like DAILY, WEEKLY, MONTHLY, and custom CRON expressions.
- ✅ **Required APIs**: `GET /jobs`, `GET /jobs/:id`, and `POST /jobs` for job management and creation with validated inputs.
- ✅ **Database**: The microservice employs Spring Data JPA for persistence, supporting H2 (demo) or PostgreSQL (production). Each job contains fields like `id`, `name`, `description`, `scheduleType`, `lastRun`, `nextRun`, and `status`.
- ✅ **Dummy Jobs**: Simulated tasks such as Email Notifications, Data Processing, and Report Generation, which can be replaced with real implementations.
- ✅ **SOLID Principles**: Modularized design with single responsibility and dependency injection for maintainability and extensibility.
- ✅ **Scalability**: Asynchronous processing ensures non-blocking job execution. The architecture supports optimized database queries and high throughput.

### Scaling Strategy
**For 10K Users + 6K Requests/Min:**
- **Horizontal Scaling**: The application can be modified to run multiple instances behind a load balancer (e.g., AWS Elastic Load Balancer or NGINX). Docker and Kubernetes (e.g., AWS EKS) can be used for orchestration and scaling based on traffic demands.
- **Database**: PostgreSQL can be employed for production with HikariCP for efficient connection pooling.
- **Caching**: Redis can be used for caching frequently accessed data like job metadata, significantly reducing database load.
- **Message Queue**: RabbitMQ or Kafka can handle distributed job execution, ensuring reliable and fault-tolerant task processing across instances.
- **Monitoring**: Prometheus and Grafana can monitor metrics like request rates and resource utilization. Spring Boot Actuator exposes health checks, and alerts are configured for anomalies like high latency or failed jobs.
