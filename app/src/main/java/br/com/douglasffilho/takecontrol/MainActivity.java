package br.com.douglasffilho.takecontrol;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void terminate(final View view) {
        this.getApplication().onTerminate();

        this.finish();
        System.exit(0);
    }

}
