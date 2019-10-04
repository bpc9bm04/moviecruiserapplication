FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=moviedb
ENV MYSQL_USER_NAME=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/moviedb
ADD ./target/movie-cruiser-authentication-0.0.1-SNAPSHOT.jar /usr/src/movie-cruiser-authentication-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-Xmx200m", "-jar", "movie-cruiser-authentication-0.0.1-SNAPSHOT.jar"]
