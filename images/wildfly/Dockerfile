FROM jboss/wildfly:15.0.1.Final

MAINTAINER Christoph Rueff <christoph.rouffsoitrueff@heig-vd.ch>

# Add the jdbc connector
ADD data/mysql-connector-java-5.1.48-bin.jar /opt/jboss/wildfly/standalone/deployments

# Deploy the .war project
ADD data/AMT-project-01.war /opt/jboss/wildfly/standalone/deployments

# Load wildfly configuration
ADD data/standalone.xml /opt/jboss/wildfly/standalone/configuration/

# Add user (admin) the get access to the management page
RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent

# Start the server
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

