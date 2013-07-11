package com.example.ebookr;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.AssetManager;
import android.util.Log;
import android.view.Menu;
import com.artifex.mupdfdemo.*;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		CopyAssetsbrochure(); 
		
		try
		{
		Uri uri = Uri.parse("/sdcard/Head First C.pdf");
        
        Intent intent = new Intent(getApplicationContext(), com.artifex.mupdfdemo.MuPDFActivity.class);

		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		intent.setAction(Intent.ACTION_VIEW);

		intent.setData(uri);

		getApplicationContext().startActivity(intent); 
		}
		catch (Exception e) {
			// TODO Auto-generated catch block

						
		} 
		
}
	private void CopyAssetsbrochure() {
        AssetManager assetManager = getAssets();
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            Log.d("Test", "sdcard mounted and writable");
        }
        else
        {
        	Log.d("Test", "sdcard state: " + state);
        }
        String[] files = null;
        try 
        {
            files = assetManager.list("");
        } 
        catch (IOException e)
        {
            Log.e("tag", e.getMessage());
        }
        for(int i=0; i<files.length; i++)
        {
            String fStr = files[i];
            if(fStr.equalsIgnoreCase("Head First C.pdf"))
            {
                InputStream in = null;
                OutputStream out = null;
                try 
                {
                  in = assetManager.open(files[i]);
                  out = new FileOutputStream("/sdcard/" + files[i]);
                  copyFile(in, out);
                  in.close();
                  in = null;
                  out.flush();
                  out.close();
                  out = null;
                  break;
                } 
                catch(Exception e)
                {
                    Log.e("CopyAssests", e.getMessage());
                } 
            }
        }
    }

 private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
          out.write(buffer, 0, read);
        }
    }

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
