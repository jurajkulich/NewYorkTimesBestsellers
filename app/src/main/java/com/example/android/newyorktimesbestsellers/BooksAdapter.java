package com.example.android.newyorktimesbestsellers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Juraj on 12/25/2017.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    private ArrayList<Book> mBooks;
    private Context mContext;

    @Override
    public BooksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View bookView = layoutInflater.inflate(R.layout.item_book, parent, false);
        ViewHolder viewHolder = new ViewHolder(bookView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book = mBooks.get(position);

        TextView textView = holder.title;
        textView.setText(book.title);
        textView = holder.author;
        textView.setText(book.author);
        textView = holder.description;
        textView.setText(book.description);
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title;
        public TextView author;
        public TextView description;


        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.book_title);
            author = (TextView) itemView.findViewById(R.id.book_author);
            description = (TextView) itemView.findViewById(R.id.book_description);
        }
    }

        public BooksAdapter(Context context, ArrayList<Book> list) {
        mBooks = new ArrayList<>(list);
        mContext = context;
    }

    public void updateAdapter(ArrayList<Book> list) {
        mBooks.clear();
        mBooks.addAll(list);
        this.notifyDataSetChanged();
    }


}
