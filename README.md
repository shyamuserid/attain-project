# This is Prototype Project for Technical Demo.
# Included Technologies
    Spring RESTful Endpoints
    Spring Security for Login
    Gradle Build
    Docker Containerization
    JUnit
    Angular UI

# Build Steps
    git clone https://github.com/attain-shyam/tech-challenge.git
    Navigate to the project folder
    Run below command at terminal
      `./gradlew clean assemble`

# To Run
    Either copy the artifact to tomcat and start tomcat:
      cp build/libs/gradle-demo-0.0.1-SNAPSHOT.jar $CATALINA_HOME/webapps
      cd $CATALINA_HOME
      ./catalina.sh start
    Or execute with gradle:
      cd attain-project/
      ./gradlew bootrun
