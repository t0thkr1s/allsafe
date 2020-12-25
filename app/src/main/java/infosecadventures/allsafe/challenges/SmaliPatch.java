package infosecadventures.allsafe.challenges;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import infosecadventures.allsafe.R;
import infosecadventures.allsafe.utils.SnackUtil;

public class SmaliPatch extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_smali_patch, container, false);
        Firewall firewall = Firewall.INACTIVE;
        Button check = view.findViewById(R.id.check);
        check.setOnClickListener(v -> {
            if (firewall.equals(Firewall.ACTIVE)) {
                SnackUtil.INSTANCE.simpleMessage(requireActivity(), "Firewall is now activated, good job! 👍");
                Toast.makeText(requireContext(), "GOOD JOB!", Toast.LENGTH_LONG).show();
            } else {
                SnackUtil.INSTANCE.simpleMessage(requireActivity(), "Firewall is down, try harder!");
            }
        });
        return view;
    }

    public enum Firewall {
        ACTIVE,
        INACTIVE
    }
}