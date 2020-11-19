package infosecadventures.allsafe.challenges;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import infosecadventures.allsafe.R;
import infosecadventures.allsafe.utils.SnackUtil;

public class WeakCrypto extends Fragment {

    public static final String KEY = "1nf053c4dv3n7ur3";

    public static String encrypt(String value) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return new String(encrypted);
        } catch (
                NoSuchPaddingException |
                        NoSuchAlgorithmException |
                        InvalidKeyException |
                        BadPaddingException |
                        IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weak_crypto, container, false);
        final EditText secret = view.findViewById(R.id.secret);
        view.findViewById(R.id.encrypt).setOnClickListener(v -> {
            String plain_text = secret.getText().toString();
            if (!plain_text.isEmpty()) {
                SnackUtil.INSTANCE.simpleMessage(requireActivity(), "Result: " + encrypt(plain_text));
            } else {
                SnackUtil.INSTANCE.simpleMessage(requireActivity(), "First, you have to enter your secrets!");
            }
        });
        return view;
    }
}