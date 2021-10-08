<p style="text-align: center; font-size:23px">
    <span style="color: lightsteelblue">
        <b> ================= Train Ticketing App ================= </b>
    </span>
</p>

<p style="text-align: center; font-size:25px"> 
    <span 
        style="color: darkseagreen" > [ Legend ] <br/>
    </span>
</p>

<p style="text-align: center; font-size:15px">
    <i>
        () = Incomplete <br/> 
        (*) = Complete <br/> 
        ... = More Functions to be Added Pattern
    </i>
</p>

<p style="text-align: center; font-size:25px">
    <span style="color: rosybrown"> 
        <b> Order of Importance Development Pattern </b>
    </span>
</p>

##Models
````
• Train
    Every Train has a destination
    - Variables
        (String) Starting Destination
        (String) Ending Destination
        (String) Arrival Time
        (String) Departure Time
        ...

    - Methods
        (of variable type) Getters
        (void) Setters 
        (String) toString
        ...

• User (Can Login)
    Every User has basic functions and a basic view of the application (UI Things)
    Every User that is an Admin has elevated functions and a more advanced view of the application (UI Things)
    
    - POJO
        Methods
            (of variable type) Getters
            (void) Setters 
            (String) toString
            ...

    - Variables
        (String) Username
        ~ Must meet conditions (*ENTER CONDITIONS*)
        
        (String) Password
        ~ Must meet conditions (*ENTER CONDITIONS*)

        (boolean) isAdmin
        ~ if true User is an Admin (A user that can Login BUT has elevated permissions)
            Elevated Functions
                = ...
                
    - Functions
        Request a (GET) all flights where DESTINATION1 (Starting Point) travels to DESTINATION2 (Ending Point)
        ...

(Optional User) Conductor
````
##Server
````
• Tomcat (*)
• Listener (*)
• Persistence Service ()
    -

Middle Layer
    Servlets ()
        - One for each model
````
##UI (React)
````
•
````
