package com.company;

import java.util.LinkedList;
import java.util.ArrayList;

public class Queue {
    ArrayList<LinkedList<Customer>> q = new ArrayList<>();
    public double waitTime = 0;
    private double[] wait;

    Queue(int size) {
        wait = new double[size];

        for (int i = 0; i < size; i++) {
            q.add(new LinkedList<>());
            wait[i] = 0;
        }
    }

    public void add(double job){
        int s = 0;
        for(int i = 0; i < q.size(); i++)
            for(int j = 0; j < q.size(); j++)
                if (q.get(i).size() <= q.get(j).size())
                    s = i;
        q.get(s).add(new Customer(job));
    }

    public void timeCheck(double step) {
        for (int i = 0; i < q.size(); i++)
            if (!q.get(i).isEmpty()) {
                q.get(i).getFirst().changeJob(-step);
                if (q.get(i).getFirst().getJob() <= 0) {
                    wait[i] += q.get(i).getFirst().getWait();
                    q.get(i).removeFirst();
                }
            }
    }

    public void waitCheck(double step){
        for (int i = 0; i < q.size(); i++){
            if(q.get(i).size() > 1)
                for (int j = 1; j < q.get(i).size(); j++)
                    q.get(i).get(j).changeWait(step);
        }
    }

    public double getWait(){
        for(double w : wait)
            waitTime += w;
        return waitTime;
    }
}
