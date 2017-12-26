package com.example.android.newyorktimesbestsellers;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Book> mBooks;
    private RecyclerView mRecyclerView;
    private BooksAdapter mBooksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBooks = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_books);
        mBooksAdapter = new BooksAdapter(this, mBooks);

        mRecyclerView.setAdapter(mBooksAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        RetrieveBook retrieveBook = new RetrieveBook();
        retrieveBook.execute();


    }

    class RetrieveBook extends AsyncTask<Void, Void, StringBuilder> {

        private static final String API_KEY = "9ad9fe7409c04c65b97c5d1a53357324";
        private static final String API_HARDCOVER_FICTION = "hardcover-fiction";
        // private ProgressBar = new ProgressBar(MainActivity.class, )

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
                    String title = "";
                    String author = "";
                    String description = "";
                    int rank = jsonObject.getInt("rank");
                    int rank_last_week = jsonObject.getInt("rank_last_week");
                    int weeks_on_list = jsonObject.getInt("weeks_on_list");
                    String amazon_product_url = jsonObject.getString("amazon_product_url");

                    JSONArray details = jsonObject.getJSONArray("book_details");

                    for( int j = 0; j < details.length(); j++) {
                        JSONObject detail = details.getJSONObject(j);
                        title = detail.getString("title");
                        author = detail.getString("author");
                        description = detail.getString("description");
                    }
                    mBooks.add(new Book(title, author, description, rank, rank_last_week, weeks_on_list, amazon_product_url));
                }
            } catch(Exception e) {
                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            mBooksAdapter.updateAdapter(mBooks);
        }
    }

}


