package com.di.polytech.wang.bean;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The machine class consists of a list of job that be processed on the machine and also it's id.
 * Created by martinwang on 23/11/16.
 */
public class Machine {
    /**
     * The Id of machine.
     */
    private int iId_Machine;

    /**
     * The operation that be processed on the machine.
     */
    private List<Operation> listOperation;

    /**
     * Constructor
     * @param iId_Machine  the Id of machine.
     */
    public Machine(int iId_Machine) {
        this.iId_Machine = iId_Machine;
        this.listOperation = new ArrayList<>();
    }

    /**
     * Getter for iId_Machine.
     * @return the id of machine.
     */
    public int getiId_Machine() {
        return iId_Machine;
    }

    /**
     * Add a operation into the machine.
     * @param operation the operation {@link Operation}
     */
    public void addOperation(Operation operation){
        this.listOperation.add(operation);
    }

    /**
     * Get the number of operation that be processed on the machine.
     * @return
     */
    public int getNbOperation(){
        return this.listOperation.size();
    }

    /**
     * Get the jth job in the list of job.
     * @param j the index of job in list.
     * @return the jth job in the list of job.
     */
    public Operation getOperationAt_j(int j){
        return this.listOperation.get(j);
    }

    /**
     * Getter for listOperation.
     * @return the list of Operation on the machine.
     */
    public List<Operation> getListOperation() {
        return listOperation;
    }

    /**
     * To sort the operation by it's start time on the machine.
     */
    public void sort(){
        listOperation.sort(Comparator.comparingInt(Operation::getiStartTime));
    }
}