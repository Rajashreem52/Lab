##Amazon Warehouse App

#### REST APIs
1. GET: http://localhost:8080/applicants/populate
for populating applicants form

2. GET: http://localhost:8080/applicants for getting all the applicants list

3. PUT: http://localhost:8080/update/applicant/status for update the status of an applicant. In the body applicant object should be given for which status should be changed.
If status changes to INVITED then notes value added. 

#### OCL
1. context applicant
invariant: self.gender == male and self.age < 55

2. context applicant
invariant: if self.gender == female and self.age < 57

3. context applicant
invariant: self.password.size() > 9

4. context applicant
invariant: if self.gender == female then self.status = invited implies OCL 2

5. context applicant
invariant: if self.status == INVITED implies 
self.notes isNotEmpty

6. context applicant
pre: OCL1, OCL2, OCL3, OCL4
invariant: applications
if applications -> size() < 200 then self.status = ACCEPTED

### Tests
All these conditions are tested with JUnit5
