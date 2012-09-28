package net.smach.halal;

import java.io.*;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import org.apache.cordova.*;

public class MainActivity extends DroidGap {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        try
        {
            String pName = this.getClass().getPackage().getName();
            this.copy("Databases.db","/data/data/"+pName+"/databases/");
            this.copy("0000000000000001.db","/data/data/"+pName+"/databases/file__0/");
        }
        catch (IOException e)
        {
        	e.printStackTrace();
        }
        
        super.loadUrl("file:///android_asset/www/index.html");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
    void copy(String file, String folder) throws IOException {
    	File CheckDirectory;
    	CheckDirectory = new File(folder);
    	if (!CheckDirectory.exists())
    	{ 
    		CheckDirectory.mkdir();
    	}

    	InputStream in = getApplicationContext().getAssets().open(file);
    	OutputStream out = new FileOutputStream(folder+file);

    	// Transfer bytes from in to out
    	byte[] buf = new byte[1024];
    	int len; while ((len = in.read(buf)) > 0) out.write(buf, 0, len);
    	in.close(); out.close();
    
    }
}
