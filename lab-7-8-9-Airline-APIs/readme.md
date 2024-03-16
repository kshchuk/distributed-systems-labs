# Airport API

This project was created to automate airport activities, manage airlines and flights.

## Objects

1. **Airline**.
    - Unique identifier
    - Name
    - Code
    - Country
    - Flights

2. **Flight**
    - Unique identifier
    - Flight number
    - Point of departure
    - Point of arrival
    - Date and time of departure
    - Date and time of arrival

## Main functions of the program

1. Loading information about objects from a xml file or MySql database.
2. Saving information about objects to a xml file or MySql database.
3. Add a new object.
4. Change the parameters of an existing object.
5. Delete an object.
6. Search for objects by specified criteria and display information about objects.

## Technologies.

1. Working with XML.
2. DBMS (MySQL) databases.
3. Transmission using Socket.
4. Transferring using RMI.
5. Java SE 8: Creating a Web App with Bootstrap and Tomcat Embedded.
6. Web client implementation using JSP, Servlet (Glassfish, ...).
7.1. Implement using SOAP web services.
7.2. Implement using REST web services.
8. Deploy the server side in Docker, implement a REST service for communicating with the client.

## Object hierarchy

All objects in the project are hierarchically dependent. For example, each airline has its own flights, and each flight belongs to only one airline.

This project is designed with hierarchical relationships between objects in mind to ensure convenient and logical management.
