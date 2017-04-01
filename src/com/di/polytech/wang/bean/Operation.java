package com.di.polytech.wang.bean;

/**
 * the class Operation consist of the id_Job, id_Operation, iStartTime, and iEndTime.
 * it is used to describe the operation tha be processed on the machine k.
 * Created by martinwang on 23/11/16.
 */
public class Operation {
    /**
     * the index of job.
     */
    private int id_Job;
    /**
     * the index of operation.
     */
    private int id_Operation;
    /**
     * the start time of the operation.
     */
    private int iStartTime;
    /**
     * the processing time of the operation.
     */
    private int iProcessingTime;

    /**
     * Constructor.
     * @param id_Job the index of job.
     * @param id_Operation the index of operation.
     * @param iStartTime the start time of the operation.
     * @param iProcessingTime the processing time of the operation.
     */
    public Operation(int id_Job, int id_Operation, int iStartTime, int iProcessingTime) {
        this.id_Job = id_Job;
        this.id_Operation = id_Operation;
        this.iStartTime = iStartTime;
        this.iProcessingTime = iProcessingTime;
    }

    /**
     * Getter for id_Job.
     * @return the index of the job.
     */
    public int getId_Job() {
        return id_Job;
    }

    /**
     * Getter fot id_Operation.
     * @return the index of the operation.
     */
    public int getId_Operation() {
        return id_Operation;
    }

    /**
     * Getter for iStartTime.
     * @return the start time of the operation of job.
     */
    public int getiStartTime() {
        return iStartTime;
    }

    /**
     * Getter for iProcessTime.
     * @return the processing time of the operation of job.
     */
    public int getiProcessingTime() {
        return iProcessingTime;
    }

    /**
     * Get the end time of the operation of job.
     * @return the end time of the operation of job.
     */
    public int getiEndTime(){
        return iStartTime + iProcessingTime;
    }

}
