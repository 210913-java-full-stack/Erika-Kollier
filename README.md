# Project 1: Erika-Kollier

### Minimum Requirements
- [x] Proper use of OOP principles
- [x] Output is presented to user on a web page, and input is accepted from a web form
- [x] Webapp(backend) and UI(frontend) are served from AWS and are publically available
- [x] CRUD operations are supported for one or more domain objects via the web application's API endpoints and invoked from the frontend
- [x] Communication is done with JSON in HTTP request and response bodies.
- [x] Abstract all JDBC away with Hibernate
- [x] Documentation (all classes and methods have adequate Javadoc comments)
- [x] All Exceptions are caught and logged to a file
- [x] 80%+ Unit test coverage for service-layer classes/methods

### Bonus Features
- [ ] DevOps CI/CD pipeline to build and deploy project
- [ ] Advanced UI Framework (Angular or React)

### Minimum Viable Product
- [x] As a user, I can see all available train routes from a city to a city.
- [x] As a user, I can purchase one or more train tickets.
- [x] As a user, I can check in for the train.
- [x] As a user, I can cancel my train ticket.
- [x] As an administrator, I can schedule a new train route.
- [x] As an administrator, I can cancel a train route.
- [x] As an administrator, I can see all users with tickets for a specific train route.

### Bonus Stories
- [ ] As an administrator, I can cancel a ticket for any train.
- [ ] As a conductor, I can initiate takeoff the train leaving the station. (No more new tickets or cancellations)

## Tech Stack
 - Java 8
 - JavaScript
 - HTML & CSS
 - Apache Maven for dependencies and project management
 - Git & Github for version control
 - MariaDB deployed on AWS RDS for data persistence
 - Hibernate to abstract away JDBC code
 - AWS EC2, ElasticBeanstalk, S3, CodeBuild, CodePipeline
