package br.com.packapps.retropikerappexample;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import br.com.packapps.retropicker.callback.CallbackPicker;
import br.com.packapps.retropicker.config.Retropicker;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    private Retropicker retropicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageView = (ImageView) findViewById(R.id.imageView);

    }



    public void btActionImage(View view) {

        Retropicker.Builder builder =  new Retropicker.Builder(this)
                .setTypeAction(Retropicker.CAMERA_PICKER)
                .setImageName("first_image.jpg")
                .checkPermission(true);


        builder.enquee(new CallbackPicker() {
            @Override
            public void onSuccess(Bitmap bitmap, String imagePath) {
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onFailure(Throwable error) {
                Log.e("TAG", "error: " + error.getMessage());
                Log.e("TAG", "error toString: " + error.toString());
            }
        });

        retropicker = builder.create();
        retropicker.open();

    }




    public void openGalery(View view) {

        Retropicker.Builder builder =  new Retropicker.Builder(this)
                .setTypeAction(Retropicker.GALLERY_PICKER)
                .setImageName("first_image.jpg")
                .checkPermission(true);


        builder.enquee(new CallbackPicker() {
            @Override
            public void onSuccess(Bitmap bitmap, String imagePath) {
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onFailure(Throwable error) {
                Log.e("TAG", "error: " + error.getMessage());
                Log.e("TAG", "error toString: " + error.toString());
            }
        });

        retropicker = builder.create();
        retropicker.open();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        retropicker.onRequesPermissionResult(requestCode, permissions, grantResults);

    }
}
