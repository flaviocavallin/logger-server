Execute the application:
========================
chmod +rwx logger-server.sh
./logger-server.sh


This is an example to execute the endpoint:
==========================================
curl -H "Content-Type: application/json" --request POST -d '{"id":12345, "name":"value12345"}' http://localhost:8080/logfile-server/log

Note:
=====
Every 60 seconds a cronned job will run and to roll over the logger file. This job will create a new file into the /tmp directory. 

