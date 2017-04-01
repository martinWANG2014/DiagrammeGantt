package com.di.polytech.wang;

import com.di.polytech.wang.draw.DrawSolution;
import com.di.polytech.wang.solution.Solution;
import com.di.polytech.wang.utils.FileUtil;

import static java.lang.System.exit;

/**
 * Created by martinwang on 18/12/16.
 */
public class Launch {

    public static void main(String[] args){
        if(args.length != 1) {
            System.err.println("Usage: DiagrammeGantt.jar filename");
            exit(1);
        }
        Solution solution = FileUtil.ReadSolutionFromFile(args[0]);
        if(solution == null){
            System.err.println("Error: Failed to read the file!");
            exit(2);
        }
        DrawSolution drawSolution = new DrawSolution(solution);
        drawSolution.saveAsImage(args[0]);
    }
}
