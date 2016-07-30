count=$(docker ps -a | grep todo-db | wc -l)
export DOCKER_IP=$(echo $DOCKER_HOST | egrep -o '[[:digit:]]{1,3}\.[[:digit:]]{1,3}\.[[:digit:]]{1,3}\.[[:digit:]]{1,3}')

if [ -z $DOCKER_IP ]
    then
	echo 'ERROR:  DOCKER_IP is not found!  Be sure that you could communicate with the docker-daemon first'
	exit 1
fi

if [ $count -eq 1 ]
   then
        echo "Starting todo-db for app"
        docker start todo-db
        echo "Note to self .... create data volume for this container and learn how to back it up in case of container failure"
   else
       echo "okay .... Lets make a new one!"
       #extract the ip address from the docker host for port forwarding (necessary if wanting to connect to container from within docker-machine)
       docker run -d --name=todo-db -p $DOCKER_IP:5432:5432 postgres:9.1.18
       echo "Docker container running postgres db up and going .... going to wait 3 seconds before setting up db"
       sleep 3
fi
