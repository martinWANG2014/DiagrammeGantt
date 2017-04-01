package com.di.polytech.wang.solution;

import com.di.polytech.wang.bean.Operation;
import com.di.polytech.wang.bean.Machine;

import java.util.ArrayList;
import java.util.List;


/**
 * the class to record the solution that the cplex solver give..
 * Created by martinwang on 23/11/16.
 */
public class Solution{
    /**
     * the number of the job that will be processed in solution.
     */
    private int iNbJob;

    /**
     * the number of the job that will be rejected in the solution.
     */
    private int iNbJobRejected;

    /**
     * the epsilon value that the upper bound of lateness of job.
     */
    private int iEpsilon;

    /**
     * the number of nodes.
     */
    private long lNbNode;

    /**
     * the function objective.
     */
    private double dFunctionObjective;

    /**
     * The solution status.
     */
    private String sSolutionStatus;

    /**
     * The resolved time.
     */
    private double dTimeResolved;


    /**
     * the solution of each machine{@link Machine}.
     */
    protected List<Machine> listMachine;


    /**
     * Constructor
     */
    public Solution() {
        listMachine = new ArrayList<>();
        iNbJobRejected = 0;
        iEpsilon = 0;
        lNbNode = 0 ;
        sSolutionStatus = "unknown";
        dFunctionObjective = 0;
        dTimeResolved = 0;
    }


    public int getNbJobProcessed() {
        return iNbJob - iNbJobRejected;
    }

    public int getiNbJobRejected() {
        return iNbJobRejected;
    }

    public void setiNbJobRejected(int iNbJobRejected) {
        this.iNbJobRejected = iNbJobRejected;
    }

    public int getiEpsilon() {
        return iEpsilon;
    }

    public void setiEpsilon(int iEpsilon) {
        this.iEpsilon = iEpsilon;
    }

    public long getlNbNode() {
        return lNbNode;
    }

    public void setlNbNode(long lNbNode) {
        this.lNbNode = lNbNode;
    }

    public double getdFunctionObjective() {
        return dFunctionObjective;
    }

    public void setdFunctionObjective(double dFunctionObjective) {
        this.dFunctionObjective = dFunctionObjective;
    }

    public String getsSolutionStatus() {
        return sSolutionStatus;
    }

    public void setsSolutionStatus(String sSolutionStatus) {
        this.sSolutionStatus = sSolutionStatus;
    }

    public double getdTimeResolved() {
        return dTimeResolved;
    }

    public void setdTimeResolved(double dTimeResolved) {
        this.dTimeResolved = dTimeResolved;
    }

    public int getiNbJob() {
        return iNbJob;
    }

    public void setiNbJob(int iNbJob) {
        this.iNbJob = iNbJob;
    }

    /**
     * Getter for listMachine
     * @return the list of Machine.
     */
    public List<Machine> getListMachine() {
        return listMachine;
    }

    /**
     * Get the number of solution.
     * @return the number of machine in solution.
     */
    public int getNbMachine(){
        return listMachine.size();
    }

    /**
     * Get the kth machine in the list of machine.
     * @param k the index of machine.
     * @return the kth machine in the list of machine.
     */
    public Machine getMachineAt_k(int k){
        return this.listMachine.get(k);
    }

    /**
     * Get the operation at jth position of kth machine in the solution.
     * @param k the index of machine.
     * @param j the index of job.
     * @return the operation at jth position of kth machine in the solution.
     */
    public Operation getOperationAt_kj(int k, int j){
        return getMachineAt_k(k).getOperationAt_j(j);
    }

    /**
     * Get the Number of the operation of kth machine.
     * @param k the index of machine.
     * @return the Number of the operation of kth machine.
     */
    public int getNbOperationAt_k(int k){
        return getMachineAt_k(k).getNbOperation();
    }

    /**
     * add a machine solution in to solution{@link Solution}.
     * @param machine
     */
    public void addMachine(Machine machine){
        this.listMachine.add(machine);
    }

    /**
     * add a operation into kth machine.
     * @param k the index of machine.
     * @param operation the operation {@link Operation}.
     */
    public void addOperationAt_k(int k, Operation operation){
        this.getMachineAt_k(k).addOperation(operation);
    }

    /**
     * Get the max compilation time for the jobs.
     * @return the max compilation time for the jobs.
     */
    public int getCmax(){
        int Cmax = -1;
        for(Machine machine : listMachine){
            machine.sort();
            int iEndTime = machine.getOperationAt_j(machine.getNbOperation() - 1).getiEndTime();
            if(Cmax < iEndTime){
                Cmax = iEndTime;
            }
        }
        return Cmax;
    }
}

