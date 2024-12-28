package com.lile.thread.pool;

public class MyTask implements Runnable {
    private String name;
    public MyTask(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("task :"+name+" end...");
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "name = "+name;
    }
}
