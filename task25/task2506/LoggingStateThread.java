package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    private Thread thread;
    private Thread.State currentThread;

    public LoggingStateThread(Thread thread) {
        this.thread = thread;
        currentThread = this.thread.getState();
    }

    @Override
    public void run() {
        System.out.println(currentThread);
        while (true) {
            if (this.thread.getState() != currentThread) {
                currentThread = this.thread.getState();
                System.out.println(currentThread);
            }
            if (currentThread.name().equals("TERMINATED")) {
                break;
            }
        }

    }
}
