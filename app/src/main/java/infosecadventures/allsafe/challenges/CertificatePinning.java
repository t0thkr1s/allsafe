package infosecadventures.allsafe.challenges;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import infosecadventures.allsafe.R;
import infosecadventures.allsafe.utils.DialogUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class CertificatePinning extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_certificate_pinning, container, false);
        setHasOptionsMenu(true);
        Button test = view.findViewById(R.id.execute);
        test.setOnClickListener(v -> {
            CertificatePinner certificatePinner = new CertificatePinner.Builder()
                    .add("httpbin.org",
                            "sha256/flUN9mYPmbQZ0jCTHMo2iEhYuYQrJ3iKdeRV7x+8s50=")
                    .add("example.com",
                            "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
                    .build();

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .certificatePinner(certificatePinner)
                    .build();

            Request request = new Request.Builder()
                    .url("https://httpbin.org/json")
                    .build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    requireActivity().runOnUiThread(() -> DialogUtil.INSTANCE.simpleAlert(requireActivity(), "Certificate Unknown", "The client failed to negotiate a TLS connection!"));
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    Log.d("ALLSAFE", Objects.requireNonNull(response.body()).string());
                    requireActivity().runOnUiThread(() -> {
                        if (response.isSuccessful()) {
                            DialogUtil.INSTANCE.simpleAlert(requireActivity(), "Connection Successful", "Check your proxy!");
                        }
                    });
                }
            });
        });
        return view;
    }
}