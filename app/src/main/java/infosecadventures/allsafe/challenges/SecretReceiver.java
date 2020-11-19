package infosecadventures.allsafe.challenges;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.util.Arrays;

public class SecretReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            String secret = intent.getStringExtra("secret");
            byte[] data = Base64.decode(secret, Base64.DEFAULT);
            if (Arrays.equals(data, Base64.decode("YzEwZjAzNzM0YWZjYzhiOGZhMzk3YjJiODk1NGY2NzQ=", Base64.DEFAULT))) {
                Toast.makeText(context, "Congratulations!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Try harder!", Toast.LENGTH_LONG).show();
            }
        } catch (IllegalArgumentException e) {
            Log.d("ALLSAFE", "Looks like characters that we can't decode!");
        }
    }
}
