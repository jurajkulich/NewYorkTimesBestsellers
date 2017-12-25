package com.example.android.newyorktimesbestsellers;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private ArrayList<Book> mBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textview);
        mBooks = new ArrayList<>();

        RetrieveBook retrieveBook = new RetrieveBook();
        retrieveBook.execute();


    }

    class RetrieveBook extends AsyncTask<Void, Void, StringBuilder> {

        private static final String API_KEY = "9ad9fe7409c04c65b97c5d1a53357324";
        private static final String API_HARDCOVER_FICTION = "hardcover-fiction";

        @Override
        protected StringBuilder doInBackground(Void... voids) {
            try {
                URL url = new URL("https://api.nytimes.com/svc/books/v3/lists.json?"+ "list-name=" + API_HARDCOVER_FICTION + "&api-key=" + API_KEY);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder;
                }
                finally {
                    httpURLConnection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(StringBuilder stringBuilder) {
            try {
                JSONArray jsonArray = new JSONObject(stringBuilder.toString()).getJSONArray("results");
                for( int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    mBooks.add(new Book(jsonObject.getString("title"), jsonObject.getString("author"), jsonObject.getString("description"), jsonObject.getInt("rank"),
                            jsonObject.getInt("rank_last_week"), jsonObject.getInt("weeks_on_list"), jsonObject.getString("amazon_product_url")));
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

}


