package com.example.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobNode {
    long ID;
    @JsonProperty("Type")
    String Type;
    Status status;
    JobNode prev;
    JobNode next;

    /**
     * JobNode constructor. Auto assigns the status to queued.
     * @param type
     */
    @JsonCreator
    public JobNode(@JsonProperty("Type") String type) {
        Type = type;
        setStatus(Status.QUEUED);
    }

    /**
     * Adds the back and front links to the node in the queue.
     * @param prev
     * @param next
     */
    public void createLinks(JobNode prev, JobNode next) {
        this.prev = prev;
        this.next = next;
    }

    /**
     * TJobNode ype getter
     * @return Type as String
     */
    public String getType() {
        return Type;
    }

    /**
     * JobNode Id setter
     * @param id as long
     */
    public void setID(long id) {
        ID = id;
    }

    /**
     * JobNode status setter
     * @param status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * JobNode status getter
     * @return Status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * JobNode setter to update the previous neighbor link
     * @param prev as JobNode
     */
    public void setPrev(JobNode prev) {
        this.prev = prev;
    }
    /**
     * JobNode setter to update the next neighbor link
     * @param next as JobNode
     */
    public void setNext(JobNode next) {
        this.next = next;
    }

    /**
     * JobNode ID getter
     * @return long ID
     */
    public long getId() {
        return ID;
    }

}
