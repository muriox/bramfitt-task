Prerequist:
- IDE (with Web development, Maven and SpringBoot support)
- MongoDB

Mongo DB Setup (using Command Prompt - Windows)
- Open cmd
- Download and Install Mongo DB https://www.mongodb.com/download-center
- Create "data\db" directory anywhere (e.g. "C:\Program Files\MongoDB\Server\5.0\data\db")
- cd to C:\Program Files\MongoDB\Server\5.0\bin
- Run Mongo DB server  using -> mongod.exe --dbpath "C:\Program Files\MongoDB\Server\5.0\data"
- In the last line of the server logs you should see -> "Waiting for connections","attr":{"port":27017,"ssl":"off"}
	
----- OPTIONAL (when Spring application is ran, Database and Collection are created)
- Open another "cmd" window
- cd to C:\Program Files\MongoDB\Server\5.0\bin
- Run mongo.exe (here you interact with you Database, such as creating a Database, Collection etc)

NOTE: You use MongoDB Compass application if you want.

Run Application:
- Ensure MongoDB server is runing
- via an IDE or command line, run - BusArrivalApplication.java (java application)
