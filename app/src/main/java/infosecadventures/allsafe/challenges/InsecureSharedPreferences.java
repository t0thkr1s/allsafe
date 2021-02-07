package infosecadventures.allsafe.challenges;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import infosecadventures.allsafe.R;
import infosecadventures.allsafe.utils.SnackUtil;

public class InsecureSharedPreferences extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insecure_shared_preferences, container, false);

        EditText username = view.findViewById(R.id.username);
        EditText password = view.findViewById(R.id.password);
        EditText passwordAgain = view.findViewById(R.id.passwordAgain);
        Button register = view.findViewById(R.id.register);
        register.setOnClickListener(v -> {
            if (username.getText().toString().isEmpty() ||
                    password.getText().toString().isEmpty() ||
                    passwordAgain.getText().toString().isEmpty()) {
                SnackUtil.INSTANCE.simpleMessage(requireActivity(), "Please, fill out the form!");
            } else if (!password.getText().toString().equals(passwordAgain.getText().toString())) {
                SnackUtil.INSTANCE.simpleMessage(requireActivity(), "Passwords don't match!");
            } else {
                SharedPreferences sharedpreferences = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("username", username.getText().toString());
                editor.putString("password", password.getText().toString());
                editor.apply();
                SnackUtil.INSTANCE.simpleMessage(requireActivity(), "Successful registration!");
            }
        });
        return view;
    }
}