package com.coplaying.hgtest2;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    private RecyclerView memoList;
    private MemoAdapter memoAdapter;
    private RecyclerView.LayoutManager memoLayoutManager;
    String[] dataSet;
    int dataSetindex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataSet = new String[10];
        dataSet[0] = "Initial contents1";
        dataSet[1] = "Initial contents2";
        dataSetindex = 1;

        memoList = (RecyclerView) findViewById(R.id.memo_list);
        memoLayoutManager = new LinearLayoutManager(this);
        memoList.setLayoutManager(memoLayoutManager);
        memoAdapter = new MemoAdapter(dataSet);
        memoList.setAdapter(memoAdapter);

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
            dataSetindex++;
            dataSet[dataSetindex] = content;
            memoAdapter.swapData(dataSet);
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

    public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {
        private String[] memoSet;

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView memoText;

            public ViewHolder(View itemView) {
                super(itemView);
                memoText = (TextView) itemView.findViewById(R.id.memo_text);
            }
        }

        public MemoAdapter(String[] dataSet){
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
            holder.memoText.setText(memoSet[position]);
        }

        @Override
        public int getItemCount() {
            return memoSet.length;
        }

        public void swapData(String[] dataSet) {
            memoSet = dataSet;
        }
    }
}
