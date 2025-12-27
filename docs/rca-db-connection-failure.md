# RCA: Database Connection Failure

## Issue
Application failed while attempting to connect to the database.

## Symptoms
- Application crash
- SQLException logged during startup

##Root Cause
Incorrect database credentials or database service not reachable.

## Resolution
- Verify databse availability
- Validate credentials and connection URL

## Prevention
- Use connection validation before runtime
- Add retry and graceful failure handling