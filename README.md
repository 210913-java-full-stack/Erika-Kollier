# Eastern Train Company

### Project Description

Eastern Train Route is a kiosk inside a train station that allows passengers to register or login to perform different functionalities. This allows for easier access to view and purchase tickets instead of waiting in long lines at the train station. Passengers are allowed to ticket purchase, view available routes from multiple cities and check-in/cancel a ticket. Administrators are able to perform and execute the same tasks as a passenger, but with more permissions, such as creating/deleting train routes and viewing all the passengers who purchased tickets for specific routes.

### Features
- [x] As a user, I can see all available train routes from a city to a city.
- [x] As a user, I can purchase one or more train tickets.
- [x] As a user, I can check in for the train.
- [x] As a user, I can cancel my train ticket.
- [x] As an administrator, I can schedule a new train route.
- [x] As an administrator, I can cancel a train route.
- [x] As an administrator, I can see all users with tickets for a specific train route.

## Technologies Used
* Java 8 
* JavaScript ES6
* HTML5 & CSS3 
* Bootstrap v5.1.3
* Apache Maven for dependencies and project management
* Git & GitHub for version control
* MariaDB deployed on AWS RDS for data persistence
* Hibernate to abstract away JDBC code
* AWS EC2, ElasticBeanstalk, S3, CodeBuild, CodePipeline

## Getting Started

#### Clone the repo
> https://github.com/210913-java-full-stack/Erika-Kollier.git
> 
> After cloning this is how your hierarchy should look.

![Post Cloning](https://user-images.githubusercontent.com/42616274/140777196-4f6bc600-5c16-4aa1-9e58-55860a75fc2c.png)

#### Environment Set up Steps
1. Open project in a Java IDE, preferrably IntelliJ
2. To run application locally set up a 'hibernate.cfg.xml' file and place it in the 'src/main/resources' folder
  ```xml
  <?xml version='1.0' encoding='UTF-8'?>
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">

<hibernate-configuration>
    <session-factory>
        <!-- SQL Dialect -->
        <property name="hibernate.dialect">org.hibernate.dialect.MariaDB103Dialect</property>

        <!-- Database Connection Settings -->
        <property name="hibernate.allow_update_outside_transaction">true</property>
        <property name="hibernate.connection.driver_class">org.mariadb.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mariadb://[DB URL]</property>
        <property name="hibernate.connection.username">[DB Username]</property>
        <property name="hibernate.connection.password">[DB Password]</property>

        <!-- Connection Pool Size (built-in) -->
        <property name="hibernate.connection.pool.size">1</property>

        <!-- show all generate SQL query -->
        <property name="show_sql">true</property>

        <!-- manage automatic database creation -->
        <!-- WARNING USE AN EMPTY DATABASE -->
        <!-- On first run use create. -->
        <property name="hibernate.hbm2ddl.auto">update</property>

        <!-- Mention here all the model classes along with their package name -->
        <mapping class="Models.Train"/>
        <mapping class="Models.UserInfo"/>
        <mapping class="Models.User"/>
        <mapping class="Models.Schedule"/>
        <mapping class="Models.Ticket"/>
        <mapping class="Models.Station"/>
        <mapping class="Models.Role"/>
        <mapping class="Models.Trip"/>
    </session-factory>
</hibernate-configuration>
```
## Usage
#### -- Optional --
> If you would like to populate the DB with custom Users, you could run [UserAndUserInfo](/src/main/java/DBPopulation/UserAndUserInfo.java)

> Otherwise, start the application and open [Index](NewTrainUI/index.html)

### Start Application
![image](https://user-images.githubusercontent.com/42616274/140779196-45b9e28e-9244-4387-b3b6-5b4916745b41.png)

### Click 'Sign Up'
![image](https://user-images.githubusercontent.com/42616274/140779342-b161a78a-786d-45ac-932f-5c405845f8d0.png)

### Input Your Information
![image](https://user-images.githubusercontent.com/42616274/140784018-d1ae8ede-b37a-4209-b2b3-0b28bd1b46c3.png)

### Navigate The Basic User Interface
![image](https://user-images.githubusercontent.com/42616274/140784169-9c07cd70-ea1b-484f-b665-c6ba6cea767b.png)

## Contributors

> Kollier Martin & Erika Johnson

## License

This project uses the following license: [GNU General Public License v3.0](LICENSE).
