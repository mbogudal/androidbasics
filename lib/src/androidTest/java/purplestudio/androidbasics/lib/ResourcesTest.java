package purplestudio.androidbasics.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Scanner;

import purplestudio.androidbasics.lib.service.ResourcesService;

@RunWith(AndroidJUnit4.class)
public class ResourcesTest
{

    private Context context = InstrumentationRegistry.getTargetContext();
    private ResourcesService resourcesService = new ResourcesService(context);

    @Test
    public void bitmapDrawable() throws Exception{

        Bitmap bitmap = resourcesService.getBitmap("logo");

        if(bitmap == null) throw new Exception("unable to load file");
    }

    @Test
    public void bitmap() throws Exception{

        Bitmap bitmap = resourcesService.getBitmap("logo", ResourcesService.RAW);

        if(bitmap == null) throw new Exception("unable to load file");
    }

    @Test
    public void scanner() throws  Exception{

        Scanner scanner = resourcesService.getScanner("test_file", ResourcesService.RAW);

        if(scanner == null) throw new Exception("unable to load file");

        while(scanner.hasNextLine()){
            Log.d("scanner", scanner.nextLine());
        }

    }

    @Test
    public void drawableCompare(){
        long time1;
        long time2;
        String msg;

        time1 = System.currentTimeMillis();
        resourcesService.getBitmap("logo");
        resourcesService.getBitmap("logo");
        time1 = System.currentTimeMillis() - time1;

        time2 = System.currentTimeMillis();
        resourcesService.getBitmap("logo", ResourcesService.DRAWABLE);
        resourcesService.getBitmap("logo", ResourcesService.DRAWABLE);
        time2 = System.currentTimeMillis() - time2;

        if(time1 < time2)
            msg = "won time1, with the advantage = " + (time2 - time1);
        else
            msg = "won time1, with the advantage = " + (time1 - time2);

        Log.d("compare", msg);
        Log.d("compare", "time1 = "+time1);
        Log.d("compare", "time2 = "+time2);

    }

}
