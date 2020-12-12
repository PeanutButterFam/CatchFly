package peanutbutterfam.catchfly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button pos00 = (Button)findViewById(R.id.pos00);

        pos00.setBackgroundResource(R.mipmap.bee);
    }
}