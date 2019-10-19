package purplestudio.androidbasics.lib.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class ResourcesService implements LifeCycle
{
    public final static String DRAWABLE = "drawable";
    public final static String RAW = "raw";
    public final static boolean DEF_INSCALED = false;
    private HashMap<String, Bitmap> bitmaps;
    private Context context;

    public ResourcesService(Context context)
    {
        this.context = context;
        bitmaps = new HashMap<>();
    }

    /*loading bitmaps is optimized for drawable resources*/
    public Bitmap getBitmap(String fileName)
    {
        int id;
        Bitmap bitmap;
        BitmapFactory.Options options;

        if (bitmaps.containsKey(fileName)) return bitmaps.get(fileName);

        id = context.getResources().getIdentifier(fileName, DRAWABLE, context.getPackageName());
        options = new BitmapFactory.Options();
        options.inScaled = DEF_INSCALED;
        bitmap = BitmapFactory.decodeResource(context.getResources(), id, options);

        bitmaps.put(fileName, bitmap);

        return bitmap;
    }

    public Bitmap getBitmap(String fileName, String resType, BitmapFactory.Options options)
    {
        int id;
        Bitmap bitmap;
        id = context.getResources().getIdentifier(fileName, resType, context.getPackageName());
        bitmap = BitmapFactory.decodeResource(context.getResources(), id, options);

        return bitmap;
    }

    public Scanner getScanner(String fileName, String resType)
    {
        int id;
        InputStream iStream;

        id = context.getResources().getIdentifier(
                fileName,
                resType,
                context.getPackageName()
        );

        iStream = context.getResources().openRawResource(id);

        return new Scanner(iStream);
    }

    public String readFile(String fileName)
    {
        String output;
        Scanner scanner = getScanner(fileName, RAW);

        output = scanner.useDelimiter("\\A").next();

        scanner.close();

        return output;

    }

    @Override
    public void onResume()
    {
    }

    @Override
    public void onPause()
    {
    }

    @Override
    public void onDestroy()
    {
        context = null;
        bitmaps = null;
    }
}
