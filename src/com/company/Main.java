package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int lanes;
        int totalCustomers;
        int currentCustomer = 0;
        int choice;
        double waitTime = 0;
        double step;
        double jobMax;
        double jobMin;
        double delayMax;
        double delayMin;
        double delay;

        // Set Up Initial Variables

        System.out.println("Enter number of lanes: ");
        lanes = (int) Math.round(safeInput(Double.POSITIVE_INFINITY));
        System.out.println("Enter total number of customers: ");
        totalCustomers = (int) Math.round(safeInput(Double.POSITIVE_INFINITY));
        System.out.println("Enter maximum job time in seconds per customer: ");
        jobMax = safeInput(Double.POSITIVE_INFINITY);
        System.out.println("Enter minimum job time in seconds per customer: ");
        jobMin = safeInput(jobMax);
        System.out.println("Enter maximum delay time in seconds between customers: ");
        delayMax = safeInput(Double.POSITIVE_INFINITY);
        System.out.println("Enter minimum delay time in seconds between customers: ");
        delayMin = safeInput(delayMax);
        System.out.println("1. Fine [0.01 seconds]" +
                "\n2. Medium [0.1 seconds]" +
                "\n3. Coarse [1.0 seconds]" +
                "\nEnter gradation choice");
        choice = (int)safeInput(3);
        switch(choice) {
            case 1:
                step = 0.01;
                break;
            case 2:
                step = 0.1;
                break;
            case 3:
                step = 1;
                break;
            default:
                step = 0.001;
        }

        // Set Up Lanes
        Queue queue = new Queue(lanes);

        // Run Simulation
        delay = setRand(delayMin, delayMax);
        do {
            waitTime += step;
            if (delay <= waitTime) {
                queue.add(setRand(jobMin, jobMax));
                currentCustomer++;
                waitTime = 0;
                delay = setRand(delayMin, delayMax);
            }
            queue.waitCheck(step);
            queue.timeCheck(step);
        } while (currentCustomer < totalCustomers);

        //Return Results
        System.out.println("Average Wait Time: " + queue.getWait()/totalCustomers + " seconds");
    }

    private static double safeInput(double max) {
        Scanner sc = new Scanner(System.in);
        Double input;
        do {
            try {
                input = sc.nextDouble();
                if (input > max || input < 0) input = Double.NaN;
            } catch (Exception e) {
                input = Double.NaN;
            }
            if (input.isNaN()) System.out.println("Invalid Input");
        } while (input.isNaN());
        return input;
    }

    private static double setRand(double min, double max) {
        double temp = min;

        if (min > max) {
            min = max;
            max = temp;
        }
        return (Math.random() * (max - min) + min);
    }
}
