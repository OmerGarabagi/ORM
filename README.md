# Car Management System
This is a Java application that utilizes Hibernate, an Object-Relational Mapping (ORM) library, to manage a car database. The system includes entities for cars, owners, pictures, and repair shops. The relationships between these entities are established using Hibernate annotations.
## Entities
### Car
The Car entity represents a vehicle and contains the following attributes:
-id: A unique identifier for the car
-licensePlate: The license plate number of the car
-price: The price of the car
-year: The manufacturing year of the car
-owner: The owner of the car (associated with the Owner entity)
-picture: A picture of the car (associated with the Picture entity)
-repairShops: A list of repair shops associated with the car (associated with the RepairShop entity)

### Owner
The Owner entity represents a car owner and contains the following attributes:
-id: A unique identifier for the owner
-firstName: The first name of the owner
-lastName: The last name of the owner
-email: The email address of the owner
-password: The password of the owner
-cars: A list of cars owned by the owner (associated with the Car entity)

### Picture
The Picture entity represents a picture of a car and contains the following attributes:
-id: A unique identifier for the picture
-url: The URL of the picture
-car: The car associated with the picture (associated with the Car entity)

### RepairShop
The RepairShop entity represents a repair shop and contains the following attributes:
-id: A unique identifier for the repair shop
-address: The address of the repair shop
-phoneNumber: The phone number of the repair shop
-shopOwners: A list of owners associated with the repair shop (associated with the Owner entity)
-cars: A list of cars associated with the repair shop (associated with the Car entity)

## Relationships
The relationships between the entities are defined using Hibernate annotations:
-A Car is associated with an Owner in a many-to-one relationship.
-A Car is associated with a Picture in a one-to-one relationship.
-A Car is associated with multiple RepairShop entities in a many-to-many relationship.
-An Owner is associated with multiple Car entities in a one-to-many relationship.
-A RepairShop is associated with multiple Owner entities in a many-to-many relationship.
-A RepairShop is associated with multiple Car entities in a many-to-many relationship.

## Usage
The App class contains the main method and serves as the entry point of the application. It performs the following tasks:
1. Establishes a connection with the database using Hibernate's SessionFactory.
2. Generates sample data, including owners, cars, pictures, and repair shops.
3. Prints all cars and their associated information (license plate, price, year, owner, repair shops).
4. Prints all repair shops and their associated information (address, phone number, associated cars).

To run the application, execute the main method in the App class.

## Dependencies
This application uses the following dependencies:
-Hibernate ORM
-Jakarta Persistence API

Make sure to include these dependencies in your project before running the application.
