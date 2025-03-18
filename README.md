# Hotel Services

A reactive microservice for hotel management using Spring Boot, Redis, and Kafka.

## Overview

This project demonstrates a hotel management service built with reactive programming principles. It provides a scalable and high-performance API for managing hotel data with Redis caching and Kafka event streaming for asynchronous processing.

## Technologies

- **Java 21**
- **Spring Boot 3.3.4**
- **Spring WebFlux** - Reactive web framework
- **Spring Data Redis Reactive** - Reactive Redis client
- **Spring Cloud Stream** - Event streaming with Kafka
- **Docker & Docker Compose** - Containerization and orchestration
- **Nginx** - Load balancing
- **Redis** - Caching layer
- **Kafka** - Event streaming
- **Lombok** - Reduces boilerplate code

## Architecture

The application follows a clean architecture approach with clear separation of concerns:

- **Domain Layer** - Business entities and request/response objects
- **Application Layer** - Business logic and services
- **Infrastructure Layer** - External communication, persistence, and configuration

## Features

- Create and retrieve hotel information
- Reactive REST API with WebFlux
- Redis caching for improved performance
- Event-driven architecture with Kafka
- Horizontal scaling with load balancing
- Containerization for easy deployment

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/hotel` | Create a new hotel |
| GET | `/api/hotel` | Get all hotels |

## Event Flow

1. Client sends a request to create a hotel
2. The service processes the request and sends an event to Kafka
3. A Kafka consumer processes the event and saves the hotel data to Redis
4. The client can retrieve the hotel data from the cache

## Running the Application

### Prerequisites

- Docker and Docker Compose installed
- Java 21 (for local development)
- Maven (for local development)

### Using Docker Compose

The easiest way to run the application is using Docker Compose:

```bash
# Clone the repository
git clone https://github.com/yourusername/hotel-services.git
cd hotel-services

# Start all services
docker-compose up
```

This will start:
- 3 instances of the hotel service
- Redis cache
- Kafka and Zookeeper
- Kafka UI (accessible at http://localhost:8085)
- Nginx load balancer

The service will be accessible at http://localhost:80

### Local Development

For local development:

```bash
# Clone the repository
git clone https://github.com/yourusername/hotel-services.git
cd hotel-services

# Start Redis and Kafka (you can use docker-compose for this)
docker-compose up --build

# Update application.yml to point to your local Redis and Kafka
# Build and run the application
mvn clean install
mvn spring-boot:run
```

## Project Structure

```
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/hotel_services/
│   │   │       ├── application/
│   │   │       │   ├── processors/     # Event processors
│   │   │       │   └── services/       # Business services
│   │   │       ├── domain/
│   │   │       │   └── request/        # Request objects
│   │   │       └── infrastructure/
│   │   │           ├── config/         # Configuration classes
│   │   │           ├── controller/     # REST controllers
│   │   │           ├── entities/       # Data entities
│   │   │           └── repository/     # Data access
│   │   └── resources/
│   │       └── application.yml         # Application configuration
│   └── test/
│       └── java/                       # Test classes
├── Dockerfile                          # Docker build configuration
├── docker-compose.yml                  # Docker Compose configuration
├── nginx.conf                          # Nginx load balancer configuration
└── pom.xml                             # Maven dependencies
```

## Example Usage

### Creating a Hotel

```bash
curl -X POST http://localhost/api/hotel \
  -H "Content-Type: application/json" \
  -d '{"hotelName": "Grand Hotel"}'
```

### Retrieving Hotels

```bash
curl -X GET http://localhost/api/hotel
```

## Load Balancing

The application uses Nginx for load balancing between three instances of the hotel service. The configuration is in `nginx.conf`.

## Scaling

The application is designed for horizontal scaling:

1. Update the `docker-compose.yml` file to add more instances
2. Update the `nginx.conf` to include the new instances in the upstream configuration

## Caching

Redis is used as a caching layer to improve performance. The cache is updated through Kafka events.

## Event Streaming

Kafka is used for event streaming, allowing for asynchronous processing and ensuring data consistency across services.

## Development Notes

- The application uses reactive programming with Project Reactor
- WebFlux is used instead of traditional Spring MVC for non-blocking I/O
- Redis is used with reactive operations for optimal performance

## Future Improvements

- Add authentication and authorization
- Implement more comprehensive error handling
- Add metrics and monitoring
- Implement database persistence
- Add more comprehensive testing

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the LICENSE file for details.