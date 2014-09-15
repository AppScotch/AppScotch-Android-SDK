package com.appscotch.basicdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.appscotch.*;

import java.util.HashMap;
import java.util.Map;

public class Main extends Activity implements AppScotchEventListener {
    Handler button_text_handler;
    Runnable button_text_runnable;
    Button play_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppScotch.setEventListener(this);
        String app_id="place <APP_ID> here!";


        AppScotch.setEventListener(this);
        AppScotch.init(this, app_id);

        play_button = (Button) findViewById(R.id.play_button);
        button_text_handler = new Handler();
        button_text_runnable = new Runnable()
        {
            public void run()
            {
                play_button.setText("Show Playable Ad");
                play_button.setOnClickListener(
                        new View.OnClickListener()
                        {
                            public void onClick( View v )
                            {

                                AppScotch.playAd(Main.this);
                            }
                        });
            }
        };


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onAdAvailable( )
    {
        Log.d("AppScotch", "onAdAvailable");
        button_text_handler.post(button_text_runnable);
    }
    public void onAdUnavailable( )
    {
        Log.d("AppScotch", "onAdUnavailable");
    }
    public void onAdStart( )
    {
        Log.d("AppScotch", "onAdStart");
    }
    public void onAdEnd( )
    {
        Log.d("AppScotch", "onAdEnd");
    }

    public void onView( boolean isCompletedView)
    {
        Log.d("AppScotch", "onView");
    }
}
