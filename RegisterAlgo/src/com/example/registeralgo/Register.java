package com.example.registeralgo;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.*;
import android.net.ConnectivityManager;
import android.os.Bundle;
public class Register extends Activity{
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.register);
	        
	       final EditText etGNO=(EditText)findViewById(R.id.etGNO);
	       final EditText etName=(EditText)findViewById(R.id.etName);
	       final EditText etRNO=(EditText)findViewById(R.id.etRNO);
	       final EditText etClg=(EditText)findViewById(R.id.etClg);
	       final EditText etYear=(EditText)findViewById(R.id.etYear);
	       final EditText etCon=(EditText)findViewById(R.id.etCon);
	       final EditText etEmail=(EditText)findViewById(R.id.etEmail);
	       final EditText etAmntP=(EditText)findViewById(R.id.etAmntP);
	       final EditText etAmntR=(EditText)findViewById(R.id.etAmntR);
	       final EditText etMem=(EditText)findViewById(R.id.etMem);
	        Button btnRegister=(Button)findViewById(R.id.btnRegister);
	        
	        final DBAdapter db=new DBAdapter(Register.this);
	        
	        btnRegister.setOnClickListener(new View.OnClickListener()
	        {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					try
					{
					ConnectivityManager conn=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
					
					if(conn.getActiveNetworkInfo()!=null && conn.getActiveNetworkInfo().isAvailable() && conn.getActiveNetworkInfo().isConnectedOrConnecting())
					{
						 Toast.makeText(getBaseContext(), "Internet Connection is available", Toast.LENGTH_SHORT).show();

					}
					else {
						db.open();
		                db.insertEntry(etGNO.getText().toString(), etName.getText().toString(),etRNO.getText().toString(),etClg.getText().toString(),etYear.getText().toString(),etCon.getText().toString(),etEmail.getText().toString(),Integer.parseInt(etAmntP.getText().toString()),Integer.parseInt(etAmntR.getText().toString()),Integer.parseInt(etMem.getText().toString()));
		                db.close();
		                Toast.makeText(getBaseContext(), "No Internet Connection....\nInserted into SQLite", Toast.LENGTH_SHORT).show();
					}
					}catch(Exception e)
					{
						 Toast.makeText(getBaseContext(), "Please Fill All the details properly", Toast.LENGTH_SHORT).show();
					}
					
				}
	        });    
	 }

}
