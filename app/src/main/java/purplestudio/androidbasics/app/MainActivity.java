package purplestudio.androidbasics.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import purplestudio.androidbasics.lib.PActivity;

public class MainActivity extends PActivity
{

    public MainActivity() throws Exception
    {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void intent(View view){
        Intent intent = new Intent(this, IntentActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume(){
        super.onResume();
        ImageView logo = findViewById(R.id.logo);
        logo.setImageBitmap(resourceService.getBitmap("logo"));
    }

}
