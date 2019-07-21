cd userservice
source ./env-variable.sh
mvn clean package

#docker build -t user-app .

cd ..
cd favouriteservice
source ./env-variable.sh
mvn clean package

#docker build -t movie-app .
cd ..
