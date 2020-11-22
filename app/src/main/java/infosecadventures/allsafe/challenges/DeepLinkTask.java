package infosecadventures.allsafe.challenges;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import infosecadventures.allsafe.R;
import infosecadventures.allsafe.utils.SnackUtil;

public class DeepLinkTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link_task);

        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();

        Log.d("ALLSAFE", "Action: " + action + " Data: " + data);

        try {
            if (data.getQueryParameter("key").equals("ebfb7ff0-b2f6-41c8-bef3-4fba17be410c")) {
                findViewById(R.id.container).setVisibility(View.VISIBLE);
                SnackUtil.INSTANCE.simpleMessage(this, "Good job, you did it!");
            } else {
                SnackUtil.INSTANCE.simpleMessage(this, "Wrong key, try harder!");
            }
        } catch (Exception e) {
            SnackUtil.INSTANCE.simpleMessage(this, "No key provided!");
            Log.e("ALLSAFE", e.getMessage());
        }
    }
}