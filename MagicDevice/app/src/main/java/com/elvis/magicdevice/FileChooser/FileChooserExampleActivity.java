
package com.elvis.magicdevice.FileChooser;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.elvis.magicdevice.R;


public class FileChooserExampleActivity extends Activity {

    private static final String TAG = "FileChooserExampleActivity";

    private static final int REQUEST_CODE = 6384; // onActivityResult request
    // code

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a simple button to start the file chooser process
        Button button = new Button(this);
        button.setText(R.string.choose_file);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display the file chooser dialog
                showChooser();
            }
        });

        setContentView(button);
    }

    private void showChooser() {
        Intent target = new Intent(Intent.ACTION_GET_CONTENT);
        target.setType("*/*");
        // Create the chooser Intent
        Intent intent = Intent.createChooser(
                target, getString(R.string.chooser_title));
        try {
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            // The reason for the existence of aFileChooser
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE:
                // If the file selection was successful
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        // Get the URI of the selected file
                        final Uri uri = data.getData();
                        Log.i("FileSelector", "Uri = " + uri.toString());
                        try {
                            // Get the file path from the URI
                            final String path = uri.getPath();
                            Toast.makeText(FileChooserExampleActivity.this,
                                    "File Selected: " + path, Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Log.e("FileSelector", "File select error", e);
                        }
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
