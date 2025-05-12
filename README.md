[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=oclay1st_vrs&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=oclay1st_vrs)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=oclay1st_vrs&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=oclay1st_vrs)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=oclay1st_vrs&metric=coverage)](https://sonarcloud.io/summary/new_code?id=oclay1st_vrs)
# Vehicle Registration System (VRS)

A system for managing vehicles in a workshop, supporting diesel, gasoline, and electric vehicles. It records general details about each vehicle along with additional attributes based on the vehicle type.

## 🚀 Features

- Registration Management: Add, and delete vehicle registrations
- Search and Filter: Quickly find vehicles based on registration details.
- User Authentication: Secure user access with authentication and registration

## ⚡️ Requirements

- Java 21+
- Maven 3.9.2
- PostgreSQL 14+
- NodeJs 20+

## 📦 Development Installation

1. Clone the repository:

```bash
git clone https://github.com/oclay1st/vrs.git
cd vrs
```

2. Define environment variables:

- `VRS_POSTGRES_DATABASE` : The postgres database name
- `VRS_POSTGRES_HOST` : The postgres database host
- `VRS_POSTGRES_PORT` : The postgres database port
- `VRS_POSTGRES_USERNAME` : The postgres database username
- `VRS_POSTGRES_PASSWORD` : The postgres database password

3. Run the backend application:

```bash
cd backend
mvn clean package
mvn spring-boot:run
```

4. Run the frontend application:

```bash
cd frontend
npm install 
npm run dev

