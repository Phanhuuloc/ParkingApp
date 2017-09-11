#### GUIDE
##### Prerequisite installed:
- Java 8
- Maven 3.3.9
- Tomcat 8
##### Build .war file:
From project directory, open terminal and type:
```cmd
mvn clean install 
OR 
mvn install 
OR 
mvn clean 
mvn install 
```
After build success, we can see 'target' dir was generated:
- open 'target' dir, 
- copy 'SungwooWebService-1.0-SNAPSHOT.war' file to tomcat webapp dir
- restart tomcat service.
- go to address:
```html
http://[host]:8080/[dir name]/swagger-ui.html
```
example:
```html
http://localhost:8080/SungwooWebService-1.0-SNAPSHOT/swagger-ui.html
```


