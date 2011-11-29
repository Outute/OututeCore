AppFuse Basic Struts Archetype test
--------------------------------------------------------------------------------
Outute build and run guide
--------------------------------------------------------------------------------
1. Update the source code from the github repository.

2. Change the mysql username and password configure of the pom.xml file.(line 734,734).

3. Make sure your mysql db server is running.

4. Run "mvn clean package" to compile and package the project

5. Run "mvn jetty:run" and view the application at http://localhost:8080

6. WARNING: DO NOT run "mvn appfuse:full-source" command, because this command will copy appfuse source code 
	and override the project's business code. This command only used in first time the project is created.

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