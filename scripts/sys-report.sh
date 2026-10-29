#!/bin/bash

LOG_FILE="logs/sys-report.log"

{
  echo "===================================="
  echo "SYSTEM REPORT"
  echo "Student: Baimurat Akbiyev"
  echo "Student ID: 38150"
  echo "Group: IT2-2306"
  echo "===================================="

  echo "Date and Time:"
  date

  echo
  echo "Current Username:"
  whoami

  echo
  echo "Operating System:"
  cat /etc/os-release | grep PRETTY_NAME

  echo
  echo "Kernel Version:"
  uname -r

  echo
  echo "CPU Information:"
  lscpu | grep -E 'Architecture|CPU\(s\)|Model name' | head -n 5

  echo
  echo "Memory Information:"
  free -h

  echo
  echo "Disk Usage:"
  df -h

  echo
  echo "Available Disk Space:"
  df -h /workspace

  echo
  echo "Report completed for Akbiyev Baimurat"
  echo "===================================="
} | tee -a "$LOG_FILE"

