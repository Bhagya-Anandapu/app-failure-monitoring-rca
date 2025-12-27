# RCA: ArithmeticException (/ by zero)

## Issue
Application crashed during execution.

## Symptoms
- Application terminated unexpectedly
- Error message logged in application.log

## Root Cause
Division by zero due to invalid input handling.

## Resolution
Added input validation to prevent division by zero.

## Prevention 
- Validate inputs before calculations
- Add proper exception handling