package infosecadventures.allsafe.challenges;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import infosecadventures.allsafe.R;

public class ObjectSerialization extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_object_serialization, container, false);
    }


    public class User implements Serializable {


        public void saveUser() {
            try {
                File file = new File("LOCATION");
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(this);
                oos.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void loadUser() {
            try {
                File file = new File("LOCATION");
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                ObjectSerialization os = (ObjectSerialization) ois.readObject();
                ois.close();
                fis.close();
                //this.setProof(os.getProof());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}