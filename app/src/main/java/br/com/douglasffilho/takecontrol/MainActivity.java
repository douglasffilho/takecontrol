package br.com.douglasffilho.takecontrol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.douglasffilho.takecontrol.queues.QueueManager;
import br.com.douglasffilho.takecontrol.queues.Queues;

public class MainActivity extends AppCompatActivity {
    private TextView activityMainTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainTextView = this.findViewById(R.id.activity_main_text);
        registerUIUpdater();
    }

    public void terminate(final View view) {
        this.getApplication().onTerminate();

        this.finish();
        System.exit(0);
    }

    public void goToControlTest(final View view) {
        final Intent controlTestIntent = new Intent(this, ControlTestActivity.class);
        startActivity(controlTestIntent);
    }

    private void registerUIUpdater() {
        QueueManager.subscribe(Queues.MAIN_ACTIVITY_TEXT_UPDATE, (message) -> {
            runOnUiThread(() -> {
                this.activityMainTextView.setText((String)message);
            });
            return null;
        });
    }

}
