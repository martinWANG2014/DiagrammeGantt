package com.di.polytech.wang.draw;

import com.di.polytech.wang.solution.Solution;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by martinwang on 15/12/16.
 */
public class DrawSolution implements DrawService {
    /**
     * the solution {@link Solution}.
     */
    protected Solution solution;

    /**
     * The Width of canvas.
     */
    private final int WIDTH;

    /**
     * The Height of canvas.
     */
    private final int HEIGHT;

    /**
     * The height of each machine on the canvas.
     */
    private final int HEIGHT_MACHINE = 50;

    /**
     * The height of each operation on the canvas.
     */
    private final int HEIGHT_OPERATION = 30;

    /**
     * The increment width of operation.
     */
    private final int WIDTH_OPERATION_INC = 10;

    /**
     * The height of header.
     */
    private final int HEIGHT_HEADER = HEIGHT_MACHINE * 6;

    private java.util.List<Color> colorList;

    private BufferedImage image;
    private Graphics2D graphics;

    public DrawSolution(Solution solution) {
        this.solution = solution;
        HEIGHT = (solution.getNbMachine()+1) * HEIGHT_MACHINE + HEIGHT_HEADER;
        WIDTH = solution.getCmax() * WIDTH_OPERATION_INC + (WIDTH_OPERATION_INC << 4);
        System.err.println(solution.getCmax());
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        graphics = image.createGraphics();
        graphics.setBackground(Color.WHITE);
        colorList = new ArrayList<>();
        initColor();
    }

    private void initColor(){
        for(int i = 0, nbJob = solution.getiNbJob(); i < nbJob; i++){
            colorList.add(new Color(
                    (ThreadLocalRandom.current().nextInt(0,256) + (i << 1)) % 256,
                    (ThreadLocalRandom.current().nextInt(0,256) + (i << 2)) % 256,
                    (ThreadLocalRandom.current().nextInt(0,256) + (i >> 1)) % 256
            ));
        }
    }
    @Override
    public int getYAt_k(int k) {
        return HEIGHT_HEADER + (k + 1)* HEIGHT_MACHINE;
    }

    @Override
    public int getXStartAt_kj(int k, int j) {
        return solution.getOperationAt_kj(k, j).getiStartTime() * WIDTH_OPERATION_INC + WIDTH_OPERATION_INC;
    }

    @Override
    public int getXEndAt_kj(int k, int j) {
        return solution.getOperationAt_kj(k, j).getiEndTime() * WIDTH_OPERATION_INC + WIDTH_OPERATION_INC;
    }

    @Override
    public int getWidthAt_kj(int k, int j) {
        return solution.getOperationAt_kj(k, j).getiProcessingTime() * WIDTH_OPERATION_INC;
    }

    @Override
    public Color getColorAt_i(int i) {
        return colorList.get(i);
    }

    @Override
    public void drawAt_kj(int k, int j){
        int i = solution.getOperationAt_kj(k, j).getId_Job();
        int u = solution.getOperationAt_kj(k, j).getId_Operation();
        graphics.setColor(getColorAt_i(i));
        graphics.drawRect(getXStartAt_kj(k, j), getYAt_k(k), getWidthAt_kj(k, j), HEIGHT_OPERATION);
        Font font = new Font("TimesRoman", Font.BOLD, 9);
        graphics.setFont(font);
        graphics.drawString("O(" + (i + 1) + ","+ (u + 1) + ")",
                getXStartAt_kj(k, j), getYAt_k(k) + HEIGHT_OPERATION);
        graphics.drawString("" + solution.getOperationAt_kj(k, j).getiStartTime(),
                getXStartAt_kj(k, j), getYAt_k(k));
        graphics.drawString("" + solution.getOperationAt_kj(k, j).getiProcessingTime(),
                (getXStartAt_kj(k, j) + getXEndAt_kj(k,j)) >> 1, getYAt_k(k) + (HEIGHT_OPERATION >> 1));
        graphics.drawString("" + solution.getOperationAt_kj(k,j).getiEndTime(),
                getXEndAt_kj(k, j), getYAt_k(k));
    }

    @Override
    public void drawData() {
        Font font = new Font("TimesRoman", Font.BOLD, 18);
        for(int k = 0, nbMachine = solution.getNbMachine(); k < nbMachine; k++){
            graphics.setColor(Color.BLUE);
            graphics.drawLine(WIDTH_OPERATION_INC, getYAt_k(k) + HEIGHT_OPERATION,
                    WIDTH, getYAt_k(k) + HEIGHT_OPERATION);
            graphics.setFont(font);
            graphics.drawString("" + (k + 1), 0, getYAt_k(k) + (HEIGHT_OPERATION >> 1)) ;
            for(int j = 0, nbOperation = solution.getNbOperationAt_k(k); j < nbOperation; j++){
                drawAt_kj(k, j);
            }
        }
    }

    @Override
    public void drawTitle() {
        graphics.drawString("Obj:" + solution.getdFunctionObjective() + ", eps:" + solution.getiEpsilon(),
                WIDTH >> 2, HEIGHT_MACHINE << 1);
        graphics.drawString("M: " + solution.getNbMachine() +
                ", J: " + solution.getiNbJob()+ ", R:" + solution.getiNbJobRejected(),
                WIDTH >> 2, HEIGHT_MACHINE * 3);
        graphics.drawString("Status: " + solution.getsSolutionStatus() + ",Time: " + solution.getdTimeResolved(),
                WIDTH  >> 2, HEIGHT_MACHINE << 2);
        graphics.drawString("Node: " + solution.getlNbNode(),
                WIDTH  >> 2, HEIGHT_MACHINE * 5);
    }

    @Override
    public void saveAsImage(String filename) {
        File file = new File(filename);
        Font font = new Font("TimesRoman", Font.BOLD, 18);
        graphics.setFont(font);
        graphics.drawString(file.getName(), WIDTH >> 2, HEIGHT_MACHINE);
        drawTitle();
        drawData();
        File dir = new File("SolutionImage");
        if(!dir.exists()){
            dir.mkdir();
        }
        try {
            ImageIO.write(image, "JPEG", new File(dir, file.getName() + ".jpg" ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the graphic2d.
     * @return the graphic2d.
     */
    public Graphics2D getGraphics(){
        return graphics;
    }
}




