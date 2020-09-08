How to run it:
- Download code
- cd to root folder
- ./mvnw clean package
- ./mvnw spring-boot:run

Assumptions:
- Jobs would not be completely removed from the queue, they were set as "CONCLUDED" instead
- If a job given an id as a pathvariable is not found, nothing is returned.
- To find next job ready to be dequeued, it starts at tail end for "FIFO" processing

Next steps:
- Testing
- Error Handling
