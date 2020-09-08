package com.example.demo;
/**
 * Main class that contains the controllers for the HTTP requests / responses
 */

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.atomic.AtomicLong;
import static org.springframework.boot.SpringApplication.*;

@SpringBootApplication
@RestController
public class CloudflareApplication {

	private Queue queue = new Queue();
	private final AtomicLong counter = new AtomicLong();

	/**
	 * Main function that starts the application
	 * @param args
	 */
	public static void main(String[] args) {
		run(CloudflareApplication.class, args);
	}

	/**
	 * Adds job to queue, and gives it an id.
	 * @param jobNode
	 * @return String (id)
	 */
	@PostMapping("/jobs/enqueue")
	public String saveJob(@RequestBody JobNode jobNode) {
		jobNode.setID(counter.incrementAndGet());
		queue.add(jobNode);
		return Long.toString(jobNode.getId());
	}

	/**
	 * Returns a job from the queue Jobs are
	 * considered available for Dequeue if the job has not been
	 * concluded and has not dequeued already
	 * @return ResponseEntity
	 */
	@GetMapping("/jobs/dequeue")
	public ResponseEntity<?> availableForDequeue() {
		JobNode available;
		long id = queue.findDequeId();
		if(id != -1) {
			available = queue.getJobById(id);
			available.setStatus(Status.IN_PROGRESS);
			return new ResponseEntity<JobNode>(available, HttpStatus.OK);
		}
		return new ResponseEntity<String>("No jobs ready for dequeue", HttpStatus.OK);
	}

	/**
	 * Concludes the job. Takes id passed from path, updates the status to Concluded
	 * and "removes" it from the queue
	 * @param id
	 */
	 @GetMapping("/jobs/{id}/conclude")
	 public void conclude(@PathVariable("id") String id) {
		JobNode job;
	 	Boolean found = queue.idExists(Long.parseLong(id));
	 	if(found) {
	 		job = queue.getJobById(Long.parseLong(id));
	 		job.setStatus(Status.CONCLUDED);
	 		queue.remove();
		}
	 }

	/**
	 * Returns the jobNode given the id
	 * @param id
	 * @return
	 */
	 @GetMapping("/jobs/{id}")
	 public ResponseEntity<?> findById(@PathVariable("id") String id) {

		Boolean found = queue.idExists(Long.parseLong(id));
		if(!found) {
			return new ResponseEntity<String>("Id not found", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<JobNode>(queue.getJobById(Long.parseLong(id)), HttpStatus.OK);
		}
	 }

}
