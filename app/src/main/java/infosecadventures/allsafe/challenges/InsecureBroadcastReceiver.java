package infosecadventures.allsafe.challenges;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import infosecadventures.allsafe.R;

public class InsecureBroadcastReceiver extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insecure_broadcast_receiver, container, false);
        Button broadcast = view.findViewById(R.id.broadcast);
        broadcast.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SecretReceiver.class);
            intent.putExtra("secret", "d293X3N1Y2hfYmFzZTY0");
            requireActivity().sendBroadcast(intent);
        });
        return view;
    }
}