count=$(docker ps -a | grep todo-db | wc -l)
echo $count
if [ $count -eq 1 ]
   then
        echo "Create backup for todo-db"
        docker exec todo-db pg_dump -U postgres > todo-backup.sql
   else
       echo "todo-db container does not exists.  Therefore no need to back up data"
fi