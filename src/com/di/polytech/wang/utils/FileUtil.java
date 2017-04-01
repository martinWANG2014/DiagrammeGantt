package com.di.polytech.wang.utils;

import com.di.polytech.wang.bean.Operation;
import com.di.polytech.wang.bean.Machine;
import com.di.polytech.wang.solution.Solution;

import java.io.*;

/**
 * Created by martinwang on 15/12/16.
 */
public class FileUtil {
    /**
     * Read data from file.
     *
     * @param filename the filename
     * @return a solution {@link Solution}
     */
    public static Solution ReadSolutionFromFile(String filename) {
        /**
         * a {@link File} to open the file, and get model data.
         */
        File file = new File(filename);

        /**
         * a {@link FileReader} for a file reader.
         */
        FileReader fileReader = null;

        /**
         * a {@link BufferedReader} for a buffered reader.
         */
        BufferedReader bufferedReader = null;

        /**
         * a solution {@link Solution}
         */
        Solution solution = new Solution();

        /**
         * a machine {@link Machine}
         */
        Machine machine;

        /**
         * a operation {@link Operation}
         */
        Operation operation = null;

        /**
         * the status of a operation {@link Operation}.
         * -1, status initial.
         * 0, read the id of job successfully.
         * 1, read the id of operation successfully.
         * 2, read the start time successfully.
         * 3, read the processing time successfully.
         * 4. create a {@link Operation} successfully, and finish reading a operation.
         */
        int iOperationStatus = -1;

        /**
         * the status of a machine {@link Machine}
         * -1, status initial
         * 0, read the id of machine successfully.
         * 1, create a machine and add into the solution successfully.
         */
        int iMachineStatus = -1;

        /**
         * the id of job.
         */
        int iId_Job = -1;

        /**
         * the id of operation.
         */
        int iId_Operation = -1;

        /**
         * the start time of the operation.
         */
        int iTime_Start = -1;

        /**
         * the processing time
         */
        int iProcessingTime;

        /**
         * the id of machine
         */
        int iId_Machine = -1;

        /**
         * the number of machines
         */
        int iNbMachine = -1;

        String line;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            //while has the data, read it.
            while ((line = bufferedReader.readLine()) != null) {
                String[] objets = line.split("[\\[\\]]+");
                if (objets.length == 0) {
                    continue;
                }
                switch (objets[0]) {
                    case "SolutionMachineOperation=":
                        iOperationStatus = -1;
                        break;
                    case "Id_Job=":
                        if (iOperationStatus == -1) {
                            iId_Job = Integer.parseInt(objets[1]);
                            //System.out.println("Id_Job: " + iId_Job);
                            if (iId_Job > -1) {
                                iOperationStatus = 0;
                            }
                        }
                        break;
                    case "Id_Operation=":
                        if (iOperationStatus == 0) {
                            iId_Operation = Integer.parseInt(objets[1]);
                            //System.out.println("Id_Operation: " + iId_Operation);
                            if (iId_Operation > -1) {
                                iOperationStatus = 1;
                            }
                        }
                        break;
                    case "TimeStart=":
                        if (iOperationStatus == 1) {
                            iTime_Start = Integer.parseInt(objets[1]);
                            //System.out.println("Time_Start: " + iTime_Start);
                            if (iTime_Start > -1) {
                                iOperationStatus = 2;
                            }
                        }
                        break;
                    case "ProcessingTime=":
                        if (iOperationStatus == 2) {
                            iProcessingTime = Integer.parseInt(objets[1]);
                            //System.out.println("Processing Time: " + iProcessingTime);
                            if (iProcessingTime > -1) {
                                iOperationStatus = 3;
                            }
                            if (iOperationStatus == 3) {
                                operation = new Operation(iId_Job, iId_Operation, iTime_Start, iProcessingTime);
                            }
                            if (operation != null && iMachineStatus == 1) {
                                iOperationStatus = 4;
                                solution.addOperationAt_k(iId_Machine, operation);
                            }
                        }
                        break;
                    case "SolutionMachine=":
                        iMachineStatus = -1;
                        break;
                    case "Id_Machine=":
                        if (iMachineStatus == -1) {
                            iId_Machine = Integer.parseInt(objets[1]);
                            //System.out.println("Id_Machine:" + iId_Machine);
                            if (iId_Machine > -1) {
                                iMachineStatus = 0;
                            }
                            if (iMachineStatus == 0) {
                                machine = new Machine(iId_Machine);
                                if (machine != null) {
                                    iMachineStatus = 1;
                                    solution.addMachine(machine);
                                }
                            }
                        }
                        break;
                    case "FunctionObjective=":
                        double dFunctionObjective = Double.parseDouble(objets[1]);
                        //System.out.println("FunctionObjective: " + dFunctionObjective);
                        if (dFunctionObjective > 0) {
                            solution.setdFunctionObjective(dFunctionObjective);
                        }
                        break;
                    case "SolutionStatus=":
                        String sSolutionStatus = objets[1];
                        solution.setsSolutionStatus(sSolutionStatus);
                        break;
                    case "TimeSolved=":
                        double dTimeSolved = Double.parseDouble(objets[1]);
                        if (dTimeSolved > 0) {
                            solution.setdTimeResolved(dTimeSolved);
                        }
                        break;
                    case "NbNodes=":
                        long lNbNodes = Long.parseLong(objets[1]);
                        if (lNbNodes > -1) {
                            solution.setlNbNode(lNbNodes);
                        }
                        break;
                    case "NbMachine=":
                        iNbMachine = Integer.parseInt(objets[1]);
                        System.out.println("NbMachine: " + iNbMachine);
                        break;
                    case "NbJob=":
                        int iNbJob = Integer.parseInt(objets[1]);
                        //System.out.println("NbJob: " + iNbJob);
                        if (iNbJob > -1) {
                            solution.setiNbJob(iNbJob);
                        }
                        break;
                    case "NbJobRejected=":
                        int iNbJobRejected = Integer.parseInt(objets[1]);
                        if (iNbJobRejected > -1) {
                            solution.setiNbJobRejected(iNbJobRejected);
                        }
                        break;
                    case "Machines=":
                        break;
                    default:
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //System.out.println(solution.getNbMachine());
        if(solution.getNbMachine() != iNbMachine){
            System.out.println(solution.getNbMachine());
            System.out.println(iNbMachine);
            System.err.println("The number of machine not correct!");
            return  null;
        }
        return solution;
    }
}