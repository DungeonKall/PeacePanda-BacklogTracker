# PeacePanda-BacklogTracker
Group project to use a database and gui thru github and create a simple tracker application

Requirements for this code to run:

// set these fields within the files to match your own username and passwords for the sql database: 
1. protected String url = "jdbc:mysql://localhost:3306/introsweproject"
2. protected String SQLusername = "root"
3. protected String SQLpassword = ""

mySQL connection, database, and schema named 'introsweproject'
table named 'user_table' with two values 'username' and 'password' both VARCHAR(45), not null, and username has to be UNIQUE and PRIMARY KEY

mysql-connector-j-8.0.33.jar or newer somewhere in build path libraries for the sql connection
rs2xml.jar for some simple function calls relating to retrieving swl data


The files for Login and MainPage are within the SRC folder!
