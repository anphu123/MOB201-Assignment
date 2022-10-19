package hieuntph22081.fpoly.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

public class BanDoActivity extends AppCompatActivity {
//    EditText txtTimViTri;
//    Button btnTimViTri;
//    private String mUrl = "geo:0,0?q=";

        private String mUrl = "https://www.google.com/maps/place/Trường+Cao+đẳng+FPT+Polytechnic/@21.0381328,105.7425653,17z/data=!3m1!4b1!4m5!3m4!1s0x313454b991d80fd5:0x53cefc99d6b0bf6f!8m2!3d21.0381278!4d105.7467871";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_do);

//        txtTimViTri = findViewById(R.id.txtTimViTri);
//        btnTimViTri = findViewById(R.id.btnTimViTri);

        // Create a Uri from an intent string. Use the result to create an Intent.
        WebView webview = findViewById(R.id.webView);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(mUrl);
//        btnTimViTri.setOnClickListener(v -> {
//            String address = txtTimViTri.getText().toString();
//            try {
//                address = URLEncoder.encode(address, "utf-8");
////                Uri gmmIntentUri = Uri.parse(mUrl+ address);
////                Toast.makeText(this, gmmIntentUri+"", Toast.LENGTH_SHORT).show();
////
////                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
////                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
////                // Make the Intent explicit by setting the Google Maps package
////                mapIntent.setPackage("com.google.android.apps.maps");
////
////                // Attempt to start an activity that can handle the Intent
////                startActivity(mapIntent);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }


//        });

    }
}