## Kafka Consumer Application

This is the README file for the Kafka consumer application. This application consumes messages from a Kafka topic named `"devices"` and persists them in a PostgreSQL database.

### Functionality

* Consumes messages from the `devices` topic in a Kafka instance.
* Deserializes the messages as JSON objects of type `GameEvent`.
* Validates the fields of the `GameEvent` object.
* Saves the validated `GameEvent` objects to a PostgreSQL database.

### Dependencies

* Spring Boot
* Spring Kafka
* Lombok
* Jackson ObjectMapper
* PostgreSQL Driver

### Configuration

The application configuration is located in the `application.yml` file. Here are some key configuration points:

* **Kafka:**
    * Bootstrap Servers: `localhost:9092` (Change this if your Kafka broker is running on a different host or port)
    * Consumer Group IDs: `gameEventGroup` and `gameEventStringGroup` (These define two consumer groups)
    * Topic Name: `devices`
    * Trusted Packages for JSON deserialization: `com.kafka.payload` (This ensures only classes from this package are trusted for deserialization)
* **PostgreSQL:**
    * Database URL: `jdbc:postgresql://localhost:5432/kafkadata` (Change this if your PostgreSQL database is running on a different host or port)
    * Username: `person`
    * Password: `person1234`
* **Server:**
    * Port: `8081`

### Docker Compose

A sample `docker-compose.yml` file is provided to easily run a Kafka broker and a PostgreSQL database container along with this application.

### Running the Application

1. Make sure you have Docker installed and running.
2. Update the configuration in `application.yml` if needed.
3. Open a terminal in the project directory and run `docker-compose up -d` to start the Kafka broker, PostgreSQL database, and the application.
4. The application will be running on port `8081`.

### Code Structure

* **com.kafka.config:**
    * `BeanConfig`: Creates an ObjectMapper bean.
    * `KafkaConfig`: Configures Kafka consumer factories and error handlers.
* **com.kafka.consumer:**
    * `GameEventJsonConsumer` and `GameEventStringConsumer`: Classes responsible for consuming messages and processing them.
* **com.kafka.exceptions:**
    * `FieldsAreNotValidException`: Custom exception thrown for invalid game event data.
* **com.kafka.handler:**
    * `GameEventHandler` and `GlobalErrorHandler`: Classes for handling errors during message consumption.
* **com.kafka.payload:**
    * `GameEvent`: Class representing the data structure of the Kafka messages.
* **com.kafka.repository:**
    * `GameEventRepository`: Spring Data repository for interacting with the `GameEvent` table in the database.
* **com.kafka.service:**
    * `GameEventService`: Service class responsible for deserializing, validating, and saving game events.
* **com.kafka:**
    * `KafkaConsumerApplication`: The main Spring Boot application class.

