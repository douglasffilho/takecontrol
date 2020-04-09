package br.com.douglasffilho.takecontrol;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ControlTestActivity extends AppCompatActivity {

    @Override
    @SuppressLint("SourceLockedOrientationActivity")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final FreeHandCanvas canvas = new FreeHandCanvas(this, null);

        this.setContentView(canvas);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);
    }

}
