##Introduction
Simple RESTFUL web service creates and search invoices.

the project has three layers: 
Controller and service layer have its own object and validator 
1. controller: intake the http request and does data validataion
2. service: has the business logic and does business value validation
3. repository: database layer which connects to postgresql

#technical stack:
Spring + JPA

##Prerequisite
1. postgresql must running on localhost and listenning on port 5432