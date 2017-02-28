# Quickstart Hadoop Project Archetype

## Overview

The project is a Maven archetype for Apache Hadoop. This archetype allows to quickly create Maven project to develop an Hadoop application.

### Structure

```
project
|-- .gitignore
|-- deploy.json
|-- pom.xml
`-- src
    |-- main
    |   |-- java
    |   |   |-- driver
    |   |   |   `-- WordCountDriver.java
    |   |   |-- mapper
    |   |   |   `-- TokenizerMapper.java
    |   |   `-- reducer
    |   |       `-- IntSumReducer.java
    |   `-- resources
    |       `-- log4j.properties
    `-- test
        |-- java
        |   `-- WordCountMapReduceTest.java
        `-- resources  
```

### Dependencies

- JDK (v1.7)
- Apache Hadoop & YARN (v2.7.3)
- Log4j (v1.2.17)
- Lombok (v1.16.12)
- JUnit (v4.12)
- MRUnit (v1.1.0)
- [SSH Maven Plugin](https://github.com/vitalibo/ssh-maven-plugin) (v1.7.3)

## How to use

To install the archetype in your local repository please use following script

```
git clone git@github.com:vitalibo/hadoop-quickstart-archetype.git
cd hadoop-quickstart-archetype
mvn clean install
```

To generate new project based on this archetype use following command

```
mvn archetype:generate \
    -DarchetypeGroupId=com.github.vitalibo \
    -DarchetypeArtifactId=hadoop-quickstart-archetype \
    -DarchetypeVersion=0.1.0-SNAPSHOT \
    -DgroupId=your.company \
    -DartifactId=project-name \
    -Dssh-server-name=hdp2.4
```

more information about ssh-server-name please see [here](https://github.com/vitalibo/ssh-maven-plugin)
