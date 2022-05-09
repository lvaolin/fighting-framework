export JAVA_APP_DIR='/usr/local/server-mlife/'

export JAVA_APP_JAR='billing-service.jar'

export JAVA_APP_NAME='billing-service'

sh /deployments/run-java.sh --spring.profiles.active=${spring_profiles_active}
