# SLIMS - Sample Laboratory Information Management System

## Overview

SLIMS (Sample Laboratory Information Management System) is a web-based application developed to manage laboratory sample registration, test assignment, result entry, status tracking, and reporting.

The system helps laboratories streamline sample processing workflows from sample receipt to final approval and report generation.

---

## Features

### Customer Management

* Create Customer
* Update Customer
* View Customer Details
* Delete Customer
* Search Customer

### Sample Registration

* Register New Samples
* Generate Unique Sample Number
* Generate Job Number
* Manage Sample Details
* Track Sample Status

### Test Management

* Create Test Master
* Manage Test Definitions
* Associate Tests with Samples

### Test Assignment

* Assign Multiple Tests to a Sample
* Track Test Progress
* Manage Test Status

### Result Management

* Enter Test Results
* Validate Results
* Mark Result Status (Pass/Fail/Pending)

### Sample Status Tracking

* Registered
* Assigned
* In Progress
* QC Review
* Approved
* Report Generated
* Closed

### Audit Trail

* Maintain Sample Status History
* Track User Actions
* Record Status Changes

---

## Technology Stack

### Backend

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* Maven

### Database

* MySQL 8+

### API Testing

* Postman

### Development Tools

* IntelliJ IDEA
* Git
* GitHub

---

## Project Structure

src/main/java/com/slims

├── controller

├── service

├── service/impl

├── repository

├── entity

├── dto

├── exception

└── config

---

## Database Schema

### customer

Stores customer information.

### sample_registration

Stores registered sample information.

### test_master

Stores laboratory test definitions.

### sample_test

Maps samples to assigned tests.

### sample_result

Stores test results.

### sample_status_history

Stores sample status change history.

---

## Database Relationships

Customer (1)
|
|----< Sample Registration (N)
|
|
Sample Registration (1)
|
|----< Sample Test (N)
|
|
Sample Test (1)
|
|----< Sample Result (N)

Test Master (1)
|
|----< Sample Test (N)

Sample Registration (1)
|
|----< Sample Status History (N)

---

## Sample Workflow

Customer
↓
Sample Registration
↓
Assign Tests
↓
Perform Testing
↓
Enter Results
↓
QC Review
↓
Approval
↓
Generate Report
↓
Close Sample

---

## Sample Number Format

SMP-YYYYMM-0001

Examples:

SMP-202606-0001

SMP-202606-0002

SMP-202606-0003

---

## Job Number Format

JOB-YYYYMM-0001

Examples:

JOB-202606-0001

JOB-202606-0002

---

## API Modules

### Customer API

POST /api/customers

GET /api/customers

GET /api/customers/{id}

PUT /api/customers/{id}

DELETE /api/customers/{id}

### Sample Registration API

POST /api/samples

GET /api/samples

GET /api/samples/{id}

PUT /api/samples/{id}

DELETE /api/samples/{id}

### Test Master API

POST /api/tests

GET /api/tests

PUT /api/tests/{id}

DELETE /api/tests/{id}

---

## Future Enhancements

* Authentication and Authorization (Spring Security + JWT)
* Dashboard and Analytics
* File Attachments
* Report Generation (PDF)
* Email Notifications
* Audit Logging
* Sample Barcode Generation
* Role-Based Access Control
* Docker Deployment
* CI/CD Pipeline

---

## Author

Muthu Gopala Krishnan M

Java Backend Developer

Spring Boot | Java | MySQL | REST APIs

