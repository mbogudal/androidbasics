package purplestudio.androidbasics.lib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import purplestudio.androidbasics.lib.constant.PERRORS;
import purplestudio.androidbasics.lib.service.ResourcesService;
import purplestudio.androidbasics.lib.service.TimerService;

public abstract class PActivity extends AppCompatActivity
{
    public static ResourcesService resourceService;
    public static TimerService timerService;
    private static PActivity instance;
    private Thread timerServiceThread;

    public PActivity() throws Exception{
        if(instance != null)
            throw new Exception(PERRORS.INSTANCE);

        instance = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        resourceService = new ResourcesService(this);
        timerService = new TimerService();
        timerServiceThread = new Thread(timerService);
        timerServiceThread.start();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        resourceService.onResume();
        timerService.onResume();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        resourceService.onPause();
        timerService.onPause();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        resourceService.onDestroy();
        timerService.onDestroy();
        instance = null;
    }
}
