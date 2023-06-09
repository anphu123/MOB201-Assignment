package hieuntph22081.fpoly.assignment;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import hieuntph22081.fpoly.assignment.adapter.TinTucAdapter;
import hieuntph22081.fpoly.assignment.model.TinTuc;


public class TinTucActivity extends AppCompatActivity {
    String rss = "https://vnexpress.net/rss/tin-noi-bat.rss";
    TinTucAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_tuc);

        recyclerView = findViewById(R.id.recyclerViewTinTuc);

        @SuppressLint("StaticFieldLeak")
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                return getData();
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                ArrayList<TinTuc> tinTucs = (ArrayList<TinTuc>) o;
                adapter = new TinTucAdapter(TinTucActivity.this, tinTucs);
                LinearLayoutManager manager = new LinearLayoutManager(TinTucActivity.this, RecyclerView.VERTICAL, false);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
            }
        };
        asyncTask.execute();
    }

    public ArrayList<TinTuc> getData() {
        java.util.ArrayList<TinTuc> tinTucs = new ArrayList<>();
        try {
            URL url = new URL(rss);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, "utf-8");
            int eventType = parser.getEventType();
            String text = "";

            TinTuc tinTuc = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName(); // lay ra ten the dang duoc doc
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("item")) {
                            tinTuc = new TinTuc();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText(); // lay ra gia tri giau 2 the
                        break;
                    case XmlPullParser.END_TAG:
                        if (tinTuc != null) {
//                            tinTuc.id =
                            if (tagName.equalsIgnoreCase("title")) {
                                tinTuc.setTitle(text);
                            } else if (tagName.equalsIgnoreCase("description")) {
                                tinTuc.setDescription(text);
                            } else if (tagName.equalsIgnoreCase("pubDate")) {
                                tinTuc.setPubDate(text);
                            } else if (tagName.equalsIgnoreCase("link")) {
                                tinTuc.setLink(text);
                            } else if (tagName.equalsIgnoreCase("item")) {
                                tinTucs.add(tinTuc);
                            }
                        }
                        break;
                }
                eventType = parser.next();
            }
            Log.e("data1", "" + tinTucs.size());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e("...1", e.getMessage());
//            Toast.makeText(this, "Link khong hop le!!!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("...1", e.getMessage());
//            Toast.makeText(this, "Ket noi khong kha dung!!!", Toast.LENGTH_SHORT).show();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            Log.e("...1", e.getMessage());
            Log.e("XML PARSER", e.getMessage());
        }
        return tinTucs;
    }
}