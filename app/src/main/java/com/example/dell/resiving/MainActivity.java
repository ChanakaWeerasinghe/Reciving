package com.example.dell.resiving;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout lin = new LinearLayout(this);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        TextView tv = new TextView(this);
        lin.addView(tv);
        lin.setOrientation(LinearLayout.VERTICAL);
        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {

                tv.setText(intent.getExtras().getString(Intent.EXTRA_TEXT));
                tv.append("\n" + (intent.getExtras().getString("MyKey")));

            } else if (type.startsWith("image/")) {
                if (bitmap != null) {
                    bitmap.recycle();
                }
                ImageView img = new ImageView(this);
                Bundle bundle = intent.getExtras();
                Uri uri = (Uri) bundle.get(Intent.EXTRA_STREAM);
                img.setImageURI(uri);
                lin.addView(img);
            }

        } else {
            // Handle other intents, such as being started from the home screen
        }
        setContentView(lin);
    }
}
