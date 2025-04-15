
# 🛠️ Job Scheduler Platform

A full-stack, Dockerized Job Scheduler platform using **Spring Boot**, **Apache Kafka**, **YugabyteDB**, **MinIO**, **pgAdmin**, and **Postman**. Supports real-time scheduling, Kafka messaging, job metadata storage, and easy API testing.

---

## 📋 Prerequisites

Before running the project, install:

- [Docker & Docker Compose](https://www.docker.com/products/docker-desktop)
- [Java 17+](https://adoptium.net/)
- [Git](https://git-scm.com/)
- [Postman](https://www.postman.com/)
- (Optional) [pgAdmin](https://www.pgadmin.org/)

---

## 🧱 Tech Stack

| Component     | Purpose                              |
|---------------|--------------------------------------|
| Spring Boot   | Java backend for job scheduling APIs |
| Apache Kafka  | Message queue                        |
| YugabyteDB    | Distributed SQL database             |
| MinIO         | S3-compatible object storage         |
| pgAdmin       | GUI client for YugabyteDB            |
| Postman       | API testing                          |
| Docker        | Container orchestration              |

---

## 🚪 Port Numbers

| Service    | Port | Description         |
|------------|------|---------------------|
| Kafka      | 9092 | Kafka broker        |
| MinIO      | 9000 | Object storage UI   |
| YugabyteDB | 5433 | PostgreSQL protocol |
| pgAdmin    | 5050 | DB GUI              |
| Spring Boot| 8080 | Backend APIs        |

---

## 🚀 How to Run the Project

### ✅ 1. Start Docker Containers

```bash
docker compose up -d
```

### ✅ 2. Verify Containers

```bash
docker ps
```

You should see containers for Kafka, YugabyteDB, MinIO, and pgAdmin running.

### ✅ 3. Build Spring Boot App

```bash
./mvnw clean install
```

### ✅ 4. Start Spring Boot App

```bash
./mvnw spring-boot:run
```

---

## 🧪 Test Kafka Messaging

### 🌀 Using `curl`

```bash
curl "http://localhost:8080/kafka/send?msg=HelloKafka"
```

Expected response:

```
Message sent to Kafka: HelloKafka
```

You’ll also see a log in the terminal:

```
📻 Received from Kafka: HelloKafka
```

### 🌐 Using Browser

Open:

```
http://localhost:8080/kafka/send?msg=HelloKafka
```

---

## 🗃️ pgAdmin Setup

- URL: [http://localhost:5050](http://localhost:5050)
- Email: `admin@admin.com`
- Password: `admin`

### ➕ Add a New Server

- **Name**: Yugabyte
- **Host**: `yugabyte`
- **Port**: `5433`
- **Username**: `yugabyte`
- **Password**: `yugabyte`

---

## 🧊 MinIO Setup

- URL: [http://localhost:9000](http://localhost:9000)
- Access Key: `minioadmin`
- Secret Key: `minioadmin`

You can upload/download files and test object storage here.

---

## 🧪 Postman Testing

Use Postman to test APIs. Example:

- **Method**: `GET`
- **URL**: `http://localhost:8080/kafka/send?msg=TestFromPostman`
- **Response**:

```json
"Message sent to Kafka: TestFromPostman"
```

---

## 📁 Sample Folder Structure

```
├── docker-compose.yml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/lemnisk/jobscheduler/
│   │   │       ├── controller/
│   │   │       │   └── KafkaTestController.java
│   │   │       └── service/
│   │   │           └── KafkaListenerService.java
│   │   └── resources/
├── .gitignore
```

---

## 🛑 Stop All Services

```bash
docker compose down
```

To stop Spring Boot: use `Ctrl + C` in the terminal.

---
