# Library API


## Steps to take to create an environment

- Use the MySQL database engine. 

- Execute the script that is in the root folder of the project called: DbScript.sql in it you will find the necessary queries to create the database, the tables and the relations between the different tables. 

- Run the script in the root folder of the project called: SeedData.sql it contains the test data with the different authors, books and pages.

- Open the project with NetBeans, run the command mvn clean install so that the specified dependencies of maven are downloaded in the pom.xml file

- Run the project by executing Run Project or by locating in the com.gbh.library.application package using the main class Startup.java and making a Run File. 

- Test the application according to the requirements indicated using the paths: http://localhost:8090/author/1/books and http://localhost:8090/book/1/pages/3/html



