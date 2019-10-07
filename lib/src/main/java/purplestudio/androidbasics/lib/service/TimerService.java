package purplestudio.androidbasics.lib.service;

import purplestudio.androidbasics.lib.Listener;
import purplestudio.androidbasics.lib.model.SharedList;

public class TimerService implements Runnable, LifeCycle
{
    public final static int MILIS_PER_SECONDS = 1000;
    public final static int MIN_FRAMES = 2;
    private boolean die;
    private boolean pause;
    private int divider;
    private int delay;
    private long sum;
    private long amount;
    private long test;
    private long savedState;
    private SharedList<Listener> listeners;

    public TimerService(){
        listeners = new SharedList<>();
    }

    @Override
    public void run() {
        savedState = System.currentTimeMillis();
        while (!die){
            if(pause) continue;

            test = System.currentTimeMillis();

            if(savedState + delay <= test){
                sum += test - savedState;
                amount++;
                savedState = test;

                for(Listener listener: listeners.getList()){
                    listener.fire();
                }

            }

        }

    }

    public void setDivider(int divider)
    {
        this.divider = divider;
        delay = MILIS_PER_SECONDS / divider;
    }

    @Override
    public void onResume() {
        pause = false;

        for(Listener listener: listeners.getList())
            listener.onResume();

    }

    @Override
    public void onPause() {
        pause = true;

        for(Listener listener: listeners.getList())
            listener.onPause();
    }

    @Override
    public void onDestroy() {
        die = true;

        for(Listener listener: listeners.getList())
            listener.onDestroy();

        listeners = null;
    }

    public void push(Listener listener){
        listeners.add(listener);
    }

    public double getAverage(){
        double average;

        if(amount <  MIN_FRAMES) return 0;

        average = sum / amount;

        return average;
    }

    public long getAmount(){
        return amount;
    }

    public long getSum(){
        return  sum;
    }

}
