package com.codeforgood.musoni;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseRegType extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_reg_type);
        
    }
    
    public void onClickClientReg(View view) {
    	
    	 Intent intent = new Intent(ChooseRegType.this, ClientReg.class);

    	 startActivity(intent);
    }
    
    public void onClickGroupReg(View view) {
    	
   	 Intent intent = new Intent(ChooseRegType.this, GroupReg.class);

   	 startActivity(intent);
   }
    
}
