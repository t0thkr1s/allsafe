package infosecadventures.allsafe.challenges;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

import infosecadventures.allsafe.R;
import infosecadventures.allsafe.utils.SnackUtil;

public class ObjectSerialization extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_object_serialization, container, false);

        TextInputEditText username = view.findViewById(R.id.username);
        TextInputEditText password = view.findViewById(R.id.password);

        Button save = view.findViewById(R.id.save);
        Button load = view.findViewById(R.id.load);

        String path = requireActivity().getExternalFilesDir(null) + "/user.dat";

        save.setOnClickListener(v -> {
            if (!Objects.requireNonNull(username.getText()).toString().isEmpty() && !Objects.requireNonNull(password.getText()).toString().isEmpty()) {
                User user = new User(username.getText().toString(), password.getText().toString());
                try {
                    File file = new File(path);
                    FileOutputStream fos = new FileOutputStream(file);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(user);
                    oos.close();
                    fos.close();
                } catch (IOException e) {
                    Log.d("ALLSAFE", e.getLocalizedMessage());
                }
                SnackUtil.INSTANCE.simpleMessage(requireActivity(), "User data successfully saved!");
            } else {
                SnackUtil.INSTANCE.simpleMessage(requireActivity(), "Fill out the fields!");
            }
        });

        load.setOnClickListener(v -> {
            if (new File(path).exists()) {
                try {
                    File file = new File(path);
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    User user = (User) ois.readObject();
                    ois.close();
                    fis.close();
                    if (!user.role.equals("ROLE_EDITOR")) {
                        SnackUtil.INSTANCE.simpleMessage(requireActivity(), "Sorry, only editors have access!");
                    } else {
                        SnackUtil.INSTANCE.simpleMessage(requireActivity(), "Good job!");
                        Toast.makeText(requireContext(), user.toString(), Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException | ClassNotFoundException e) {
                    Log.d("ALLSAFE", e.getLocalizedMessage());
                }
            } else {
                SnackUtil.INSTANCE.simpleMessage(requireActivity(), "File not found!");
            }
        });
        return view;
    }


    public static class User implements Serializable {

        String username;
        String password;
        String role = "ROLE_AUTHOR";

        public User() {
        }

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @NotNull
        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", role='" + role + '\'' +
                    '}';
        }


    }
}