count=$(docker ps -a | grep todo-db | wc -l)
echo $count
if [ $count -eq 1 ]
   then
        echo "Starting todo-db for app"
        docker start todo-db
        echo "Note to self .... create data volume for this container and learn how to back it up in case of container failure"
   else
       echo "okay .... Lets make a new one!"
       # the host mapping is REALLY the host of the docker machine that houses the postgres db. Find a way to map the docker machine ip to localhost.
       docker run -d --name=todo-db -p 192.168.99.100:5432:5432 postgres:9.1.18
       echo "Docker container running postgres db up and going .... going to wait 3 seconds before setting up db"
       sleep 3
fi
