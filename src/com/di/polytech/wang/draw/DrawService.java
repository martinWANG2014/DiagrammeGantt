package com.di.polytech.wang.draw;

import java.awt.*;

/**
 * Created by martinwang on 15/12/16.
 */
public interface DrawService {
    /**
     * Get the y value of the kth machine.
     * @param k the index of machine.
     * @return the y value of the kth machine.
     */
    int getYAt_k(int k);

    /**
     * Get the start x value of operation at jth operation on kth machine.
     * @param k the index of machine.
     * @param j the index of position.
     * @return the start x value of operation at jth operation on kth machine.
     */
    int getXStartAt_kj(int k, int j);

    /**
     * Get the end x value of operation at jth operation on kth machine.
     * @param k the index of machine.
     * @param j the index of position.
     * @return the end x value of operation at jth operation on kth machine.
     */
    int getXEndAt_kj(int k, int j);

    /**
     * Get the width of operation at jth position on kth machine.
     * @param k the index of machine.
     * @param j the index of position.
     * @return the width of operation at jth position on kth machine.
     */
    int getWidthAt_kj(int k, int j);

    /**
     * Get the color of ith job.
     * @param i the index of job.
     * @return the color of ith job.
     */
    Color getColorAt_i(int i);

    /**
     * To draw the operation at jth position on kth machine.
     * @param k the index of machine.
     * @param j the index of position.
     */
    void drawAt_kj(int k, int j);

    /**
     * To draw the data.
     */
    void drawData();

    /**
     * To draw the header title.
     */
    void drawTitle();

    /**
     * To save the solution image into a file.
     * @param filename the file to save the solution image.
     */
    void saveAsImage(String filename);
}
