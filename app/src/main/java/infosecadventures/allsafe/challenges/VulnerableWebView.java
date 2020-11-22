package infosecadventures.allsafe.challenges;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import infosecadventures.allsafe.R;
import infosecadventures.allsafe.utils.SnackUtil;

public class VulnerableWebView extends Fragment {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vulnerable_web_view, container, false);
        TextInputEditText payload = view.findViewById(R.id.payload);
        WebView webView = view.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        view.findViewById(R.id.execute).setOnClickListener(v -> {
            if (!Objects.requireNonNull(payload.getText()).toString().isEmpty()) {
                if (URLUtil.isValidUrl(Objects.requireNonNull(payload.getText()).toString())) {
                    webView.loadUrl(payload.getText().toString());
                } else {
                    webView.setWebChromeClient(new WebChromeClient());
                    webView.loadData(payload.getText().toString(), "text/html", "UTF-8");
                }
            } else {
                SnackUtil.INSTANCE.simpleMessage(requireActivity(), "No payload provided!");
            }
        });
        return view;
    }
}