# SOEN 342 II

## Description

## Members

Kristi Doce 40155592  
Mehdi Kahouache 40250581

## Getting Started

### Dependencies

1. Install Nodejs and NPM ***[NodeJS installation Guide][nodejs-install-url]***
2. Install PostgreSQL ***[Postgresql Installation Guide][postgresql-install-url]***
   - Initialize DB <br> ```pg_ctl -D /var/lib/pgsql/data initdb```
   - Replace pg_hba.conf with that of this repo i.e. project/database/pg_hba.conf
   - Ensure file permissions and ownership are correct <br> ```-rw-------. 1 postgres postgres 5469 Oct 28 00:39 /var/lib/pgsql/data/pg_hba.conf```
   - Restart postgresql

### Setup

#### Clone the repo

```sh
# clone repo into folder "project"
git clone git@github.com:DarkMed15/soen342-teamproject.git project
```

##### Create database

```sh
# create the database from your terminal by running the sql file
psql -U postgres

> CREATE USER soen;
> CREATE DATABASE soendb OWNER soen;

psql -U soen -d soendb < database/soen.sql
```

## Usage

1. Ensure ***Dependencies*** are installed.
2. In the ```app``` folder run:

    ```sh
    npm install
    npm run dev
    ```

[nodejs-install-url]: https://github.com/nodesource/distributions/blob/master/README.md
[postgresql-install-url]: https://www.postgresql.org/docs/16/index.html
