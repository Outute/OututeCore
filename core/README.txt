AppFuse Basic Struts Archetype test
--------------------------------------------------------------------------------
Outute build and run guide
--------------------------------------------------------------------------------
1. Update the source code from the github repository.

2. Change the mysql username and password configure of the pom.xml file.(about line 734,734).

3. Make sure your mysql database server is running.(about the mysql installation and database initialization see the "mysql database setting" following)

4. Run "mvn clean package" to compile and package the project. If you have encountered tests error, please run "mvn clean package -Dmaven.test.skip=true"

5. Run "mvn jetty:run" and view the application at http://localhost:8080

6. WARNING: DO NOT run "mvn appfuse:full-source" command, because this command will copy appfuse source code 
	and override the project's business code. This command only used in first time the project is created.
--------------------------------------------------------------------------------
Outute deploy guide
--------------------------------------------------------------------------------
1. The Step 4 in "The Outute build and run guide" will generate a war file, you can find in the project directory "/core/target/core-1.0-SNAPSHOT.war"

2. Change the database connection configuration in the file "core-1.0-SNAPSHOT.war\WEB-INF\classes\jdbc.properties" by a unzip tool if the configuration
	is not the same as the configuration you configured in the pom.xml(The Step 2 in "The Outute build and run guide").
	Or you can change the pom.xml configuration and pack again.
	
3. Deploy in tomcat

4. Deploy in jboss
--------------------------------------------------------------------------------
maven memory setting
--------------------------------------------------------------------------------
1. Find the line "#set MAVEN_OPTS=-Xdebug ......" in file (Windows:%M2_HOME%\bin\mvn.bat, Linux/Unix:$M2_HOME/bin/mvn)
	add a new line after the line you found and add the jvm configure "set MAVEN_OPTS= -Xms128m -Xmx1024m".
--------------------------------------------------------------------------------
tomcat memory setting
--------------------------------------------------------------------------------
1. Find the line "# $Id: catalina.sh ......" in file (%TOMCAT_HOME%\bin\catalina.bat, Linux/Unix:$TOMCAT_HOME/bin/catalina.sh)
	add a new line after the line you found and add the jvm configure Windows:"set JAVA_OPTS=%JAVA_OPTS% -Xms128m -Xmx1024m"
	Linux "set JAVA_OPTS=$JAVA_OPTS -Xms128m -Xmx1024m".
--------------------------------------------------------------------------------
mysql database setting
--------------------------------------------------------------------------------
1. Install mysql 5.0.x, download from http://dev.mysql.com/downloads/,
	this document could help: http://dev.mysql.com/doc/refman/5.0/en/installing.html

2. Create a database such as outute,  and , and then create a database:
	A) open a terminal: Linux: bash, Window: cmd
	B) login as root by command, for example: mysql -uroot -p1234
	C) create a database by command: create database outute charset utf8;
	
3. Initialize the database by importing sql file(you can find in the project "core/help" directory):
	A) login as root
	B) choose the database by command: use outute
	C) import sql file by command, such as: source {project directory}/core/help/outute.sql
	
4. If you use the different database name and project path, change the database name and project path when you execute the command.
