# CSCC01 Project Quiry
--------------------
A search engine for the UTSC community.
![alt text](https://i.imgur.com/Wzyn4KE.png)

## Team Members
* Kin He - <kin.he@mail.utoronto.ca> (heweijia)
* Mak, Ewen - <ewen.mak@mail.utoronto.ca> (make2)
* Yash Masani - <yash.masani@mail.utoronto.ca> (masaniya)
* Jack Xu - <haosen.xu@mail.utoronto.ca> (xuhaosen)
* Wayne Ying - <wayne.ying@mail.utoronto.ca> (yingweiy)

## Planning Documentation
The following documentation are written for each phase of the project. They will be kept up-to-date.

### [Phase 1](docs/phase1)
* [Summary](docs/phase1/summary.md) - Project objectives as well as key users, scenarios, and principles.
* [Competition](docs/phase1/competition.md) - Existing products similar to this project.
* [Personas](docs/phase1/personas.pdf) - Detailed descriptions of the key users.
* [User Stories](docs/phase1/user_stories.md) - Tasks users are expected to perform.
* [UI/UX Mockups](docs/phase1/ui_ux.pdf) - User interface mockup and storyboard for interactions.
* [Process](docs/phase1/process.md) - Description of teamwork process.

### [Phase 2](docs/phase2)
* [JIRA] - Project planning and issue tracking for agile development.
* [CRC Cards](docs/phase2/crc.pdf) - High-level descriptions of Java classes designed for this project.

[JIRA]: https://cmsweb.utsc.utoronto.ca/jira/projects/TEAM11

## Development
The Quiry project is developed using:

Back-End Technologies:

* [Java SE Development Kit 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (update 181)
* [Eclipse IDE for Java EE Developers](https://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/photonr) (version Photon 4.8.0)
* [Maven](https://maven.apache.org/download.cgi) (version 3.5.4)
* [Apache Lucene](http://lucene.apache.org) (version 7.4.0)
* [Crawler4J](https://github.com/yasserg/crawler4j) (version 4.4.0)
* [Apache PDFBox](https://pdfbox.apache.org) (version 2.0.11)
* [JSoup](https://jsoup.org/) (version 2.0.3)
* [Spring Boot](https://spring.io/projects/spring-boot) (version 2.0.3)
* [Apache Tomcat](http://tomcat.apache.org) (Spring)
* [SQLite / JDBC](https://www.sqlite.org/index.html) (Spring)
* [Spring Mail](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-email.html) (Spring)
* [JUnit 4](https://junit.org/junit4) (Spring)
* [Mockito](http://site.mockito.org) (version 1.10.19)


Front-End Technologies:

* [Sublime Text](https://www.sublimetext.com) (version 3)
* [AngularJS](https://angularjs.org) (version 1.6.9)
* [Bootstrap CSS](https://getbootstrap.com) (version 4.1.1)
* [Angular Material](https://material.angular.io) (version 1.1.10)
* [Angular-UI](https://angular-ui.github.io) (version 2.5.0)
* [ngTagsInput](http://mbenford.github.io/ngTagsInput/) (version 2.0.11)
* [Font Awesome](https://fontawesome.com/) (version 5.1.0)
* [Google Charts](https://developers.google.com/chart)
* [File Saver](https://github.com/eligrey/FileSaver.js) (version 1.3.8)


### Build
To build the project in the [quiry](quiry/) directory, run `mvn compile`, you may clean the build files with `mvn clean`. The main function is in the class `App`.

```console
foo@bar:CSC01TeamProjectRepo11\quiry$ mvn clean
foo@bar:CSC01TeamProjectRepo11\quiry$ mvn compile
```

To populate the database with some predefined users and files, before running the program on a machine for the first time, you may execute some [test functions](quiry/src/test/java/cscc01/summer2018/team11).

```console
foo@bar:CSC01TeamProjectRepo11\quiry$ mvn test-compile
foo@bar:CSC01TeamProjectRepo11\quiry$ mvn exec:java@users
foo@bar:CSC01TeamProjectRepo11\quiry$ mvn exec:java@files
```

In addition, the [java](java/) directory contains sample test Java projects intended to test individual features before they are ready to be included in the main project.

These coding [guidelines](http://cr.openjdk.java.net/~alundblad/styleguide/index-v6.html) will be enforced.

### Run
After building the project using Maven, the project web server can be launched with one command:

```console
foo@bar:CSC01TeamProjectRepo11\quiry$ mvn spring-boot:run
```

Now open your favorite browser and go to __http://localhost:8080__. You will land on the Quiry home page.

## Contributing
Quick steps to contributing to this project:

1. Create your branch in JIRA with the sprint number followed by the issue (sub-task) ID (`s#-TEAM11-##`)
2. Include the JIRA issue ID in the commit message (`git commit -m "TEAM11-## ..."`)
3. Push changes to the branch (`git push -u origin s#-TEAM11-##`)
4. Create a [__pull request__](https://bitbucket.org/mcs2/csc01teamprojectrepo11/pull-requests/) to master by the end of the week

For more details, see:

* [Referencing issues in your development work](https://confluence.atlassian.com/jirasoftwarecloud/referencing-issues-in-your-development-work-777002789.html)
* [Using Smart Commits](https://confluence.atlassian.com/fisheye/using-smart-commits-298976812.html)

You may append a tag to the end of the branch name to indicate its purpose. For example: `feature`, `develop`, `bugfix`, `release`.

Do __NOT__ commit any binary or temporary files generated by the IDE or OS. A sample [.gitignore](quiry/.gitignore) file can be found in the quiry project.

## Marking
Please remember to [log](https://confluence.atlassian.com/jiracoreserver073/logging-work-on-issues-861257349.html) any days you worked on tasks in [JIRA].

A screenshot of the task board will be taken _three_ times a week, on Monday, Thursday, and Sunday.

The previous sprint will end at __9:00p__ every Sunday. A weekly summary of the Burndown chart will be generated. New tasks will be assigned for the next sprint during the weekly meeting.

All work-in-progress __must__ be merged to master by the end of each sprint. At least _two_ people other than the author need to approve the pull request before the branch can be merged. Create a new branch following the procedure listed above to continue working.
