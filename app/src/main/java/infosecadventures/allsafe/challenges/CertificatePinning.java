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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import infosecadventures.allsafe.R;
import infosecadventures.allsafe.utils.SnackUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class CertificatePinning extends Fragment {

    private static final String INVALID_HASH = "sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_certificate_pinning, container, false);
        setHasOptionsMenu(true);

        //  make an intentional request with broken config
        //  to get the actual peer certificate chain public key hashes from  okhttp exception
        List<String> hashes = extractPeerCertificateChain();

        Button test = view.findViewById(R.id.execute);
        test.setOnClickListener(v -> {

            CertificatePinner.Builder certificatePinner = new CertificatePinner.Builder();
            for (String hash : hashes) {
                Log.d("ALLSAFE", hash);
                certificatePinner.add("httpbing.org", hash);
            }

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .certificatePinner(certificatePinner.build())
                    .build();

            Request request = new Request.Builder()
                    .url("https://httpbin.org/json")
                    .build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    Log.d("ALLSAFE", e.getMessage());
                    requireActivity().runOnUiThread(() -> SnackUtil.INSTANCE.simpleMessage(requireActivity(), e.getMessage()));
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    Log.d("ALLSAFE", Objects.requireNonNull(response.body()).string());
                    requireActivity().runOnUiThread(() -> {
                        if (response.isSuccessful()) {
                            SnackUtil.INSTANCE.simpleMessage(requireActivity(), "Successful connection over HTTPS!");
                        }
                    });
                }
            });
        });
        return view;
    }

    private List<String> extractPeerCertificateChain() {
        List<String> chain = new ArrayList<>();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .certificatePinner(new CertificatePinner.Builder()
                        .add("httpbin.org", INVALID_HASH)
                        .build())
                .build();

        Request request = new Request.Builder()
                .url("https://httpbin.org/json")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                requireActivity().runOnUiThread(() -> {
                    String[] lines = e.getMessage().split(System.getProperty("line.separator"));
                    for (String line : lines) {
                        if (!line.trim().equals(INVALID_HASH) && line.trim().startsWith("sha256")) {
                            String pin = line.trim().split(":")[0].trim();
                            chain.add(pin);
                        }
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {

            }
        });
        return chain;
    }
}