## Setting up PostgreSQL

In SQL shell:
```
CREATE DATABASE app;
```

```
GRANT ALL PRIVILEGES ON DATABASE "app" TO <username>;
```

Navigate to `src/main/resources/application.properties`, change lines to appropriate values
```
spring.datasource.username=<username>
spring.datasource.password=<password>
```