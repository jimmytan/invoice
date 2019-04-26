##Introduction
Simple RESTFUL web service creates and search invoices.

the project has three layers: 
Controller and service layer have its own object, validator, data converter 
1. controller: intake the http request and does data validataion
2. service: has the business logic and does business value validation
3. repository: database layer which connects to postgresql

For this simple project, these layer maybe over engineering. the loose coupling layer gives more freedom for future modification

#technical stack:
Spring + JPA

##Prerequisite
1. apache maven must be installed https://maven.apache.org/
2. postgresql must running on localhost and listenning on port 5432
3. database invoices and user zola must be added
4. certain permission must be granted to database user zola
grant all privileges on table invoices to zola;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO zola;

##Compile
under root project directory run the following command
1. mvn clean install
2. java -jar target/invoice-service-1.0-SNAPSHOT.jar

##Testing
Any http tool should be good. Here we are using curl
* to create an invoice
curl -d '{
"invoice_number": "456754",
"po_number": "ttttt",
"due_date": "2018-03-15",
"amount_cents": 2000
}' -H "Content-Type: application/json" -X POST http://localhost:8080/v1/invoice

response: {"id":11,"invoice_number":"456754","po_number":"ttttt","amount_cents":2000,"due_date":"2018-03-15","created_at":"2019-04-25T20:22:13Z"}

Note: invoice number, po number, due date and amount cents cannot be null and amount cent cannot be negative

* search invoice by invoice number or po number

only need to provide one of them. if you provide two, then all invoices which meet one criteria will be returned
if page size and page number are not provided, the default value will be 0 and 20
Sample request:
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/v1/invoices?invoiceNumber=456754&poNumber=1111&pageSize=5&pageNumber=0

Response:
[{"id":11,"invoice_number":"456754","po_number":"ttttt","amount_cents":2000,"due_date":"2018-03-15","created_at":"2019-04-25T20:22:13Z"},{"id":10,"invoice_number":"456754","po_number":"ttttt","amount_cents":2000,"due_date":"2018-03-15","created_at":"2019-04-25T20:15:08Z"},{"id":9,"invoice_number":"456754","po_number":"ttttt","amount_cents":2000,"due_date":"2018-03-15","created_at":"2019-04-25T20:15:06Z"},{"id":8,"invoice_number":"456754","po_number":"ttttt","amount_cents":2000,"due_date":"2018-03-15","created_at":"2019-04-25T20:07:58Z"},{"id":7,"invoice_number":"456754","po_number":"ttttt","amount_cents":2000,"due_date":"2018-03-15","created_at":"2019-04-25T19:32:41Z"}]

##Improvement
1. Versioning
Although the url includes version number, the system does not use it. it should have some logic behind to do the versioning.

2. More business validation on the invoice data
Maybe the invoice number and po number should be unique.

3. More junit test
the junit test coverage is not 100% yet.

