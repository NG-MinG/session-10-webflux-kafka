# Session 07 - Kafka + Spring Boot (Maven)

Demo tích hợp Apache Kafka với Spring Boot bằng Maven, gồm 2 service độc lập:

- `kafka-producer-service` (port 8081): REST API nhận đơn hàng và publish sự kiện lên topic `order-events`.
- `kafka-consumer-service` (port 8082): lắng nghe topic `order-events` và log lại sự kiện nhận được.

## 1. Khởi động Kafka

```bash
docker compose up -d
```

Kafka UI để xem topic/message: http://localhost:8080

## 2. Chạy producer service

```bash
cd kafka-producer-service
./mvnw spring-boot:run
```

## 3. Chạy consumer service

```bash
cd kafka-consumer-service
./mvnw spring-boot:run
```

## 4. Test

```bash
curl -X POST http://localhost:8081/api/orders \
  -H "Content-Type: application/json" \
  -d '{"productName":"Laptop","quantity":1,"price":1500.00}'
```

Xem log của `kafka-consumer-service` để thấy sự kiện được tiêu thụ.

## Cấu trúc

```
session-07-kafka-spring-boot/
├── docker-compose.yml          # Kafka (KRaft mode) + Kafka UI
├── kafka-producer-service/     # Maven, Spring Boot 4, spring-kafka
└── kafka-consumer-service/     # Maven, Spring Boot 4, spring-kafka
```
