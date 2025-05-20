FROM tomcat:9.0-jdk17

# Remove default ROOT app
RUN rm -rf /usr/local/tomcat/webapps/ROOT

COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

# Run Tomcat server
CMD ["catalina.sh", "run"]
