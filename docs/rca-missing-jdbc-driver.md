# RCA: Missing JDBC Driver

## Issue
Database connection failed during application startup.

## Error
No suitable driver found for jdbc:mysql://localhost:3306/nonexistentdb

## Root Cause
MYSQL JDBC driver was not available on the classpath.

## Resolution
Add MYSQL Connector/J dependency when database access is required.

## Prevention 
Validate driver availability during application startup.