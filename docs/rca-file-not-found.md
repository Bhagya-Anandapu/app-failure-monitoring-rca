# RCA: FileNotFoundException

## Issue
Application failed while trying to read an input file.

## Symptioms
- Application crashed
- Error logged as FileNotFoundException

## Root Cause
Required file was missing or incorrect file path configured.

## Resolution
Ensure file exists at expected path before reading.

## Prevention
- Validate file existence
- Add fallback or default handling