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