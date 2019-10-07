package purplestudio.androidbasics.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import purplestudio.androidbasics.lib.PActivity;

public class IntentActivity extends PActivity
{

    public IntentActivity() throws Exception
    {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

    }

}
