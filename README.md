# Spring Boot RESTful API for Statistics Management

## Project Description
This project involves the development of a Spring Boot RESTful API to update statistics in a database and cache responses at specific intervals (for instance, every 5 minutes).

## Project Requirements
- **Database**: MongoDB
- **Authentication**: Spring Security (JWT is a plus)
- **Caching**: Redis

The database should be initialized with data from the `test_report.json` file (a static file that is periodically updated). All statistics must be stored and attributes can be found in the [Amazon SP-API documentation](https://developer-docs.amazon.com/sp-api/docs/report-type-values-analytics#sales-and-traffic-business-report).

## Key Features
1. **User registration**
2. **User authentication**
3. **Retrieve statistics by specified date or date range**  
   Responses should be cached.
4. **Retrieve statistics by specified ASIN or list of ASINs**  
   Responses should be cached.
5. **Retrieve cumulative statistics for all dates**  
   Responses should be cached.
6. **Retrieve cumulative statistics for all ASINs**  
   Responses should be cached.
7. **Update all statistics at regular intervals**  
   Data should be fetched from the `test_report.json` file. When data changes in the file, the corresponding data in the database must be updated accordingly.
