# Spring AMQP Sandbox

This project is a sandbox for experimenting with Spring AMQP, which is a framework for working with Message Broker (RabbitMQ) in Java. It includes examples of how to send and receive messages using RabbitMQ, as well as how to configure the connection and exchange.


## Stack

- Java 21
- Spring Framework
  - Spring Boot
  - Spring Validation
  - Spring Data JPA
  - Spring AMQP (RabbitMQ)
  - Lombok
- Cloud AMQP (Platform to manage RabbitMQ instances)
- Docker & Docker Compose


## Microservices
- **Auth** <br>
    Microservice to handle simple user credentials registration.
- **Users** <br>
    Microservice to manage users profiles.


## How does it work?

### AMQP

AMQP stands for **Advanced Message Queuing Protocol**. It's the protocol behind RabbitMQ and its entire architecture. AMQP defines four principal components:
- **Producer** <br>
    Who sends the message.
- **Exchange** <br>
    The message router.
- **Queue** <br>
    The message buffer/storage.
- **Consumer** <br>
    Who consumes the messages from the queues.

<img src="images/AMQP.png" height="260pt">

### RabbitMQ communication

RabbitMQ works with **push-based** communication. The RabbitMQ pushes new messages to Spring, which receives, handles them, then returns a confirmation (**ACK**) that the message has already been consumed successfully.

<img src="images/rabbitMQ_communication.png" height="260pt">

### AMQP message

An AMQP message has three parts:
- **Header** <br>
    Custom attributes.
- **Attributes** <br>
    Message metadata.
- **Body** <br>
    The payload.

<img src="images/AMQP_message.png" height="260pt">
