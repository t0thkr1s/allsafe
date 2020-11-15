package infosecadventures.allsafe.challenges;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import infosecadventures.allsafe.R;

public class InsecureLogging extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insecure_logging, container, false);
        requireActivity().setTitle("Insecure Logging");
        setHasOptionsMenu(true);
        TextInputEditText secret = view.findViewById(R.id.secret);
        secret.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE && !Objects.requireNonNull(secret.getText()).toString().equals("")) {
                Log.d("ALLSAFE", "allsafe{debug_messages_are_bad}");
            }
            return false;
        });
        return view;
    }
}