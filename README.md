# Spring Boot

Complete Spring Boot,

Run the project and access and access `http://localhost:8080`

You also could use Postman to get, post, put or delete data on api by following...

Insert student: Send POST request to `http://localhost:8080/courses/create`

Get all students: Send GET request to `http://localhost:8080/api/v1/students`

Get one student: Send GET request to `http://localhost:8080/api/v1/courses`

Delete student: Send DELETE request to `/instructors/delete/{id}`

Update student: Send PUT request to `http://localhost:8080//instructors/edit`

To insert or update also send a json on the body of request like the example below

{
  "id":1,
  "name":"TestCourse123321",
  "category":"TestCategory",
  "users":752,
  "reviews":752,
  "hearts":721
}

Thanks =)
