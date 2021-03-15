package infosecadventures.allsafe;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ProxyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(getIntent().getParcelableExtra("extra_intent"));
    }
}