🚦 Smart Urban Traffic Management System (TMS)
This project implements a Java EE-based enterprise solution for managing urban traffic using real-time data collected from IoT devices (vehicles and traffic intersections). The system simulates traffic behavior, stores incoming data, analyzes it to detect congestion levels, and provides insights to administrators through an analytical backend. It leverages Enterprise JavaBeans (EJB) for modularity, scalability, and asynchronous data handling.

The architecture separates concerns across multiple layers including:

IoT Device Simulation for traffic data generation

Central Server for message reception and data persistence

Analytical Server for traffic pattern analysis and congestion level calculation

Web Interface for data visualization

The solution is built to handle real-time data processing, scale with increasing traffic volume, and remain highly available through the use of EJB containers and asynchronous messaging.

🛠 Technologies Used
Java EE

Enterprise JavaBeans (EJB)

Stateless Session Beans (Data persistence)

Singleton Session Beans (Traffic analysis)

Message-Driven Beans (Asynchronous message handling)

Servlets & JSP (Web layer)

JMS (Java Message Service)

For queue-based communication between IoT devices and the central system

Hibernate

ORM for database operations

Dependency Injection (@EJB annotations)

To simplify component wiring and enhance testability

JSON

For sending analytical data to the web interface

Custom Java IoT Simulator

Simulates vehicle and traffic light data generation

