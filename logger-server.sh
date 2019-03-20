mvn install
docker build . -t logfile-server
docker run -p 8080:8080 logfile-server
