install apache-maven-3.9.4
    -extract the maven zip to any folder you want
    -add the bin path to the environment variables->system varialbes->Path
    -test in command line mvn -v

get the project
    -git clone https://github.com/dkasrtg/S3S5Project.git

run the project in vscode
    -open new vscode terminal
    -"mvn clean install" or "mvn clean package"
    -execute sql script base.sql in SQL SHELL (POSTGRES)
    -"mvn jetty:run"
    -open http://localhost:8181/reference in any browser
    -Ctrl+C to stop

VScode extensions recommended
    -extension pack for java
    -bootstrap, font awesome
    -eslint
    -html css support
    -intellicode
    -javaScript
    -prettier
    -material icon theme