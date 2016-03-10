package com.hackerkernel.downloadappasynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    ProgressBar progressBar;
    Button button;
    ListView listView;

    String[] songsUrl;
    NonUiFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the view
        editText = (EditText) findViewById(R.id.edittext);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        button = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listview);

        //get song url
        songsUrl = getResources().getStringArray(R.array.songs_url);

        //when list view item is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editText.setText(songsUrl[position]);
            }
        });

        /* Activity is created for the first time */
        if (savedInstanceState == null){
            fragment = new NonUiFragment();
            getSupportFragmentManager().beginTransaction().add(fragment,"task").commit();
        }else{ //activity created for subscquent time
            fragment = (NonUiFragment) getSupportFragmentManager().findFragmentByTag("task");
        }

        if (fragment != null){
            if (fragment.myTask != null && fragment.myTask.getStatus() == AsyncTask.Status.RUNNING){
                progressBar.setVisibility(View.VISIBLE);
            }
        }
    }

    //Method to download song
    public void downloadSong(View view){
        String url = editText.getText().toString().trim();
        if (!url.isEmpty() || url.length() > 0){
            fragment.beginTask(url);
        }else{
            Toast.makeText(getApplication(), "Select a songs", Toast.LENGTH_LONG).show();
        }
    }

    public void updateProgressbar(int progress){
        progressBar.setProgress(progress);
    }

    public void hideProgressbar(){
        if (fragment.myTask != null){
            if (progressBar.getVisibility() == View.VISIBLE ){
                progressBar.setVisibility(View.GONE);
            }
        }
    }

    public void showProgressbar(){
        if (fragment.myTask != null){
            if (progressBar.getVisibility() != View.VISIBLE &&
                    progressBar.getProgress() != progressBar.getMax()){
                progressBar.setVisibility(View.VISIBLE);
            }
        }
    }
}
