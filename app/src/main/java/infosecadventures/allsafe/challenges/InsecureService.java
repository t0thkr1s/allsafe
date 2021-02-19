package infosecadventures.allsafe.challenges;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import infosecadventures.allsafe.R;


public class InsecureService extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insecure_service, container, false);
        view.findViewById(R.id.start).setOnClickListener(v -> requireActivity().startService(new Intent(requireActivity(), RecorderService.class)));
        return view;
    }
}