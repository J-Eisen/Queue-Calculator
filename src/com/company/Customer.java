package com.company;

public class Customer {
    private double job;
    private double wait = 0;

    Customer(double job){
        this.job = job;
    }
    public double getJob() {
        return job;
    }
    public double getWait() {
        return wait;
    }
    public void changeJob(double change){
        job += change;
    }
    public void changeWait(double change) {
        wait += change;
    }
}
