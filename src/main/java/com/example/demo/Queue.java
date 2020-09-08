package com.example.demo;

public class Queue {

    private JobNode tail = null;
    private JobNode start = null;
    private int size = 0;

    /**
     * Adds a JobNode to the queue
     * @param jobNode as a JobNode
     */
    public void add(JobNode jobNode) {
        if(start == null) {
            start = jobNode;
            tail = jobNode;
            jobNode.setPrev(null);
            jobNode.setNext(null);
        }
        else {
            jobNode.setNext(start);
            jobNode.setPrev(null);
            start.setPrev(jobNode);
            start = jobNode;
        }
        size++;
    }

    /**
     * Removes a node from the queue
     */
    public void remove() {
        size--;
    }

    /**
     * Retrieves JobNode at start of queue
     * @return JobNode
     */
    public JobNode getStart() {
        return start;
    }

    /**
     * Retrieves JobNode at end of queue
     * @return JobNode
     */
    public JobNode getTail() {
        return tail;
    }

    /**
     * Determines if a JobNode with a specified ID exists in the queue
     * @param id as a long
     * @return boolean
     */
    public Boolean idExists(long id) {
        JobNode iterQueue = start;
        int iter = 0;
        while(iterQueue != null && iter <= size) {
            if(iterQueue.getId() == id) {
                return true;
            }
            iterQueue = iterQueue.next;
        }
        return false;
    }

    /**
     * Getter for the first JobNode in the queue (at the end of it) that is avaiable to be
     * dequeued
     * @return id as long
     */
    public long findDequeId() {
        JobNode iterQueue = tail;
        while(iterQueue != null) {
            if((iterQueue.getStatus() != Status.IN_PROGRESS) && (iterQueue.getStatus() != Status.CONCLUDED)) {
                return iterQueue.getId();
            }
            iterQueue = iterQueue.prev;
        }
        return -1;
    }

    /**
     * Returns JobNode of that id
     * @param id
     * @return JobNode
     */
    public JobNode getJobById(long id) {
        JobNode iterQueue = start;
        while(iterQueue != null) {
            if(iterQueue.getId() == id) {
                return iterQueue;
            }
            iterQueue = iterQueue.next;
        }
        return iterQueue;
    }
}
