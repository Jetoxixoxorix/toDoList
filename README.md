# To do list

## Requirements:
- mySQL,
- Java Development Kit 1.9

## Launching application:
1.  First thing to do in order to use this application is to install mySQL and JDK in version 1.9. 
2.  Then you have to create database in mySQL:
- mysql> create database todolist;
- mysql> create user 'todolistuser'@'localhost' identified by
'qwerty';
- mysql> grant all on todolist.* to 'todolistuser'@'localhost';
3. Then you have to run jar file with java machine or on command line:
- java -jar (directory where jar file is)\to-do-list-1.0.0.jar
4. Now application is running, you can launch your web browser and go to localhost:8080 address and you should see login page in application.
5. In order to use this application you have to register and log in on your account.
6. After that you can add task, edit them etc.
