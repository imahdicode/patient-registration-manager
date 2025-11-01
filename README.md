# Patient Registration Manager

Java app for registration, login, and photo upload with MySQL. This repo contains Chapter 1 exercises from the SDAC course and is ready for sharing on GitHub or linking from a resume.

## Summary

- Lightweight Java application with classes for registration, user/admin login flows, photo upload, and database connectivity.
- Package: `com.chp1_project` (sources in `src/`, compiled classes may be produced in `bin/`).

## Tech stack

- Java (standard edition)
- Uses plain `javac`/`java` for compilation and execution

## Repository structure

- `src/` — Java source files and `module-info.java`.
- `bin/` — (optional) compiled classes (gitignored).
- `.gitignore`, `LICENSE`, and this `README.md` at the repository root.

## Build and run (example)

These commands assume you are in the repository root and have a JDK installed.

1. Compile sources into `bin/`:

```powershell
mkdir -Force bin
javac -d bin src\module-info.java src\com\chp1_project\*.java
```

2. Run the main class (replace `MasterPage` if another class contains `main`):

```powershell
java -cp bin com.chp1_project.MasterPage
```

If the project uses the Java module system, run with module path:

```powershell
java --module-path bin -m com.chp1_project/com.chp1_project.MasterPage
```

## Contributing / Notes

- This is a small learning/portfolio project.

  - Help initialize the repository (create initial commit and push) if you provide the remote URL.

## License

This project is licensed under the MIT License — see `LICENSE`.

---

## Detailed usage notes

Prerequisites

- Java JDK 11+ installed and on your PATH.
- MySQL server (or compatible) running locally or reachable from this machine.
- MySQL JDBC driver (Connector/J). This project expects the driver jar in `connector/` (example: `connector/mysql-connector-j-9.4.0.jar`).

## Database setup

This project expects a MySQL database named `db8` and two simple tables used by the code: `logDetails` (for login credentials) and `registration` (for registration entries). Example SQL you can adapt and run in MySQL:

```sql
CREATE DATABASE IF NOT EXISTS db8;
USE db8;

CREATE TABLE IF NOT EXISTS logDetails (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) NOT NULL,
  password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS registration (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  contact VARCHAR(255),
  mobile VARCHAR(50),
  gender VARCHAR(20),
  city VARCHAR(100),
  comment TEXT
);
```

## Configuration / credentials

For simplicity this project previously used hard-coded credentials, but that is insecure. The application now reads database credentials from environment variables first and falls back to a local `config.properties` file (which is ignored by git).

Recommended approaches (choose one):

- Environment variables (preferred):

  PowerShell example:

  ```powershell
  $env:DB_URL = "jdbc:mysql://localhost:3306/db8"
  $env:DB_USER = "root"
  $env:DB_PASS = "your_password_here"
  ```

- `config.properties` (convenient for dev machines): copy `config.properties.sample` to `config.properties` and update credentials. `config.properties` is listed in `.gitignore` so it won't be committed.

  Example `config.properties` (do not commit):

  ```ini
  db.url=jdbc:mysql://localhost:3306/db8
  db.user=root
  db.password=your_password_here
  ```

The code will try environment variables first (DB_URL, DB_USER, DB_PASS), then `config.properties`, then sensible defaults.

## Build (compile)

From the repository root run (PowerShell):

```powershell
# create output folder
mkdir -Force bin

# compile sources and place .class files into bin/
# include the connector on the classpath so classes that reference java.sql drivers compile cleanly
javac -d bin -cp "connector/mysql-connector-j-9.4.0.jar" src\module-info.java src\com\chp1_project\*.java
```

## Run (non-module style)

If you compiled with the classpath above and want to run without using the module system:

```powershell
java -cp "bin;connector/mysql-connector-j-9.4.0.jar" com.chp1_project.MasterPage
```

## Run (module-aware)

This project includes a `module-info.java`. To run the main class as a module, use:

```powershell
# make sure bin contains compiled module structure
java --module-path bin;connector/mysql-connector-j-9.4.0.jar -m Chp1_Project/com.chp1_project.MasterPage
```

## Troubleshooting

- "cannot find symbol" when compiling a single file: compile from the project root and include all sources (see Build section). Compiling only a single file inside a package folder won't resolve other classes in the same package.
- JDBC driver not found at runtime: ensure the Connector/J jar is on the classpath or module-path when running the app.
- Database connection refused / authentication failed: verify MySQL is running, the database exists, and credentials in `GetConnection.java` are correct.
