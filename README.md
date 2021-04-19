# Spring Boot Rest Api Demo

Complete Rest Api developed in Spring Boot,

Run the project and access and access `http://localhost:8080/api/v1/students`

You also could use Postman to get, post, put or delete data on api by following...

Insert student: Send POST request to `http://localhost:8080/api/v1/students`

Get all students: Send GET request to `http://localhost:8080/api/v1/students`

Get one student: Send GET request to `http://localhost:8080/api/v1/students/student_id`

Delete student: Send DELETE request to `http://localhost:8080/api/v1/students/student_id`

Update student: Send PUT request to `http://localhost:8080/api/v1/students/student_id`

To insert or update also send a json on the body of request like the example below

{

  "age": 18,

  "firstName": "John",

  "lastName": "Doe",

  "course": "Computer Science"

}

Thanks =)
