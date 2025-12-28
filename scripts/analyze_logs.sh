#!/bin/bash

# Always resolve project root correctly
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
PROJECT_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"

LOG_FILE="$PROJECT_ROOT/logs/application.log"
OUTPUT_FILE="$PROJECT_ROOT/logs/analysis.log"

# Safety checks
if [ ! -f "$LOG_FILE" ]; then
  echo "ERROR: Log file not found at $LOG_FILE"
  exit 1
fi

mkdir -p "$PROJECT_ROOT/logs"

echo "---- ERROR COUNT BY COMPONENT ----" | tee "$OUTPUT_FILE"

grep '"level":"ERROR"' "$LOG_FILE" \
  | awk -F'"component":"' '{print $2}' \
  | awk -F'"' '{print $1}' \
  | sort | uniq -c \
  | tee -a "$OUTPUT_FILE"

echo "" | tee -a "$OUTPUT_FILE"
echo "---- LAST 5 ERRORS ----" | tee -a "$OUTPUT_FILE"

grep '"level":"ERROR"' "$LOG_FILE" | tail -5 | tee -a "$OUTPUT_FILE"
