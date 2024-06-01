mvn clean install

docker build . -t sibiha/stackoverkata_jm:1.0.0

docker push sibiha/stackoverkata_jm:1.0.1

docker run -ti --rm -e DATASOURCE_HOST=192.168.56.1 -p 8081:8080 sibiha/stackoverkata_jm:1.0.1
#     или в терминале выполняется команда: attrib +x build_and_push.sh
# chmod +x build_and_push.sh

# выполняем в Bash
sc query state= all | findstr "PostgreSQL"
sc stop postgresql-x64-15

sc start postgresql-x64-15
