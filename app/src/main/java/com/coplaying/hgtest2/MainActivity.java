package com.coplaying.hgtest2;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView memoListRecyclerView;
    private MemoAdapter memoAdapter;
    private RecyclerView.LayoutManager memoLayoutManager;
    private ArrayList<MyData> myDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //DB setting
        MemoDbHelper memoDBHelper = new MemoDbHelper(this,MemoDbContract.MemoDb.DB_NAME,null,1);
        SQLiteDatabase memoDB = memoDBHelper.getWritableDatabase();
        ContentValues initValue1 = new ContentValues();
        initValue1.put(MemoDbContract.MemoDb.TITLE,"first memo title");
        initValue1.put(MemoDbContract.MemoDb.CONTENT,"first memo content");
        ContentValues initValue2 = new ContentValues();
        initValue2.put(MemoDbContract.MemoDb.TITLE,"second memo title");
        initValue2.put(MemoDbContract.MemoDb.CONTENT,"second memo content");

        //view setting
        memoListRecyclerView = (RecyclerView) findViewById(R.id.memo_list);

        //set up layout manager: linear
        memoLayoutManager = new LinearLayoutManager(this);
        memoListRecyclerView.setLayoutManager(memoLayoutManager);

        //set up adapter
        myDataSet = new ArrayList<>();
        memoAdapter = new MemoAdapter(myDataSet);
        memoListRecyclerView.setAdapter(memoAdapter);

        //test data set
        myDataSet.add(new MyData("First memo"));
        myDataSet.add(new MyData("Second memo"));
        myDataSet.add(new MyData("Thrid memo"));

        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                */
                Intent intent = new Intent(MainActivity.this, NewContentActivity.class);
                startActivityForResult(intent,0);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == 1){
            String content = data.getStringExtra("memo_content");
            myDataSet.add(new MyData(content));
            memoAdapter.swapData(myDataSet);
            memoAdapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, content, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {
    private ArrayList<MyData> memoSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView memoText;

        public ViewHolder(View itemView) {
            super(itemView);
            memoText = (TextView) itemView.findViewById(R.id.memo_text);
        }
    }

    public MemoAdapter(ArrayList<MyData> dataSet){
        memoSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.memo_text_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.memoText.setText(memoSet.get(position).text);
    }

    @Override
    public int getItemCount() {
        return memoSet.size();
    }

    public void swapData(ArrayList<MyData> dataSet) {
        memoSet = dataSet;
    }
}

class MyData{
    public String text;
    public MyData(String text){
        this.text = text;
    }
}

