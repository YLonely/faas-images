FROM java

COPY . /

RUN javac -cp .:mysql-connector-java-8.0.15.jar Main.java

CMD ["java","-cp",".:mysql-connector-java-8.0.15.jar","Main"]