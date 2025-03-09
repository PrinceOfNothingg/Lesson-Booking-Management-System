# Lesson Booking Management System

## Description
A Java-based application that enables an organization to manage group and private lessons (e.g., yoga, swimming, judo) across multiple locations and schedules. The system supports:

- Flexible Scheduling: Locations (gyms, rooms, pools) have custom time slots for lessons.
- Instructor Management: Seasonal instructors register their availability and take on lesson offerings.
- Client Bookings: Clients can browse available lessons, register, and book private or group sessions.
- Guardian Support: Minors require an adult guardian to manage their bookings.
- Administrative Control: A system administrator manages lesson offerings and user accounts.
- Concurrency Handling: Supports multiple readers with controlled writer access for data integrity.

## Members

Kristi Doce - kristidoce@gmail.com  
Mehdi Kahouache - mehdi.kahouache@gmail.com

## Getting Started

### Dependencies

1. Install Java JDK
2. Install PostgreSQL ***[Postgresql Installation Guide][postgresql-install-url]***
   - Initialize DB <br> ```pg_ctl -D /var/lib/pgsql/data initdb```
   - Replace pg_hba.conf with that of this repo i.e. project/database/pg_hba.conf
   - Ensure file permissions and ownership are correct <br> ```-rw-------. 1 postgres postgres 5469 Oct 28 00:39 /var/lib/pgsql/data/pg_hba.conf```
   - Restart postgresql
3. Download json-simple jar to lib directory and add it to your IDE project as a dependancy 
4. Download postgresql jdbc jar to lib directory add it to your IDE project as a dependancy

### Setup

#### Clone the repo

```sh
# clone repo into folder "project"
git clone git@github.com:DarkMed15/soen342-teamproject.git project
```

##### Create database

```sh
# create the database from your terminal by running the sql file
psql -U postgres -c "CREATE USER soen"
psql -U postgres -c "CREATE DATABASE soendb OWNER soen"
psql -U soen -d soendb < database/soen.sql
```

## Usage

1. Ensure ***Dependencies*** are installed.
2. Compile: "cd /project/path; javac -cp .:application/lib/json-simple-1.1.1.jar:application/lib/postgresql-42.7.4.jar application/src/*.java"
3. Run: "cd /project/path; java -cp .:application/lib/postgresql-42.7.4.jar:application/lib/json-simple-1.1.1.jar application.src.Application"

[postgresql-install-url]: https://www.postgresql.org/docs/16/index.html
