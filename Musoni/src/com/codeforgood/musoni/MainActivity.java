package com.codeforgood.musoni;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.content.Context;

import com.codeforgood.musoni.api.APIrequest;

public class MainActivity extends Activity {
	
	private static Context context;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     
        //APIrequest.loadClientList();
        //APIrequest.loadGroupList();
        APIrequest.loadLoanList();
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
    }

    public static Context getContext()
    { return context; }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
