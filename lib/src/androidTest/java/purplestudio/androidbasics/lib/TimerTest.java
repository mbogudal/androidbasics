package purplestudio.androidbasics.lib;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import purplestudio.androidbasics.lib.service.TimerService;

@RunWith(AndroidJUnit4.class)
public class TimerTest
{
    private TimerService timerService;
    private Thread timerServiceThread;

    @Test
    public void average() throws Exception{
        int frameRate = 0;
        timerService = new TimerService();

        timerService.setDivider(30);

        timerServiceThread = new Thread(timerService);
        timerServiceThread.start();

        while(timerService.getAmount() < 100);

        frameRate = (int) (TimerService.MILIS_PER_SECONDS / timerService.getAverage());
        Log.d("average" , String.valueOf(timerService.getAverage()));
        Log.d(
                "frame_rate" ,
                String.valueOf(frameRate)
        );

        if(frameRate != 30)
            throw new Exception("invalid frame rate");
    }

}
