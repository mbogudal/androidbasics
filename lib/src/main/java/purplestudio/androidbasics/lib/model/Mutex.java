package purplestudio.androidbasics.lib.model;

import java.util.concurrent.Semaphore;

public abstract class Mutex
{
    public final static int MUTEX_PERMITS = 1;
    private Semaphore mutex;

    public Mutex(){
        mutex = new Semaphore(MUTEX_PERMITS);
    }

    protected void acquire(){
        try
        {
            mutex.acquire();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    protected void release(){
        mutex.release();
    }

}
