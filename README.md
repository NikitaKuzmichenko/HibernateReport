### This project demonstrates the usage of Hibernate, a popular ORM framework for Java.

### Introduction

This is a small-scale project aimed at showcasing the basic functionalities of Hibernate. It provides a set
of examples and code snippets to illustrate how to perform various database operations using Hibernate.

### Features

- Exhibits the configuration and setup of Hibernate.
- Illustrates the usage of Hibernate annotations for object-relational mapping through examples.
- Displays the functionality of various ID generators and guides on registering custom ID generators.
- Demonstrates Hibernate's ability to track and manage the state of entities.
- Provides guidance on determining the current state of an entity during runtime.
- Demonstrates the handling of associations and relationships between entities using Hibernate.
- Explains the concept of the "owning side" in relationships.
- Explores Hibernate's implementation of lazy loading.
- Guides on implementing different inheritance strategies using Hibernate.

### Usage

To use HibernateReport, follow these steps:

1) Clone this repository to your local machine using the following command:
    ```
    git clone https://github.com/pentagrot/HibernateReport.git
    ```

2) Set up your database and setup connection to it. H2 in memory database is used by default.

3) Runt test with Hibernate usage examples using following command :
    ```
    ./gradlew test
    ```

### Contributing

Contributions to project are welcome! If you find any bugs, issues, or have suggestions for improvements,
please feel free to open an issue or submit a pull request.
I hope you find HibernateReport useful and educational.

### License

You are free to use and distribute them as is if you promise to run tests and understand how things work under the hood.
Happy coding;)