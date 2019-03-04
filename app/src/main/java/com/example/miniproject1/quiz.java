package com.example.miniproject1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONObject;

public class quiz extends AppCompatActivity implements topicsList.topicListInterface, question.questionInterface {
    public int score=0;public int highS=0;public String user="";public question q;public topicsList tp;
    String s="";String answer="testMsg";String Question="";
    MediaPlayer media;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Intent intent = getIntent();
        user=intent.getStringExtra("user");
        highS=getSharedPreferences(user,0).getInt("highS",0);
        tp=(topicsList) getSupportFragmentManager().findFragmentById(R.id.mainFrag);
        q=(question) getSupportFragmentManager().findFragmentById(R.id.questionFrag);
        tp.setScore(score);
        tp.setHighScore(highS);
        media = MediaPlayer.create(this,R.raw.sonic);
    }

    @Override
    public void onItemSselected(int position) {
        if (q == null) {
            Intent intent;
            intent = new Intent(quiz.this, portQuestion.class);
            intent.putExtra("score", (int) (score));
            intent.putExtra("position", (int) (position));
            intent.putExtra("user",user);
            startActivityForResult(intent, 503);
        }else{
            switch(position){
                case 0:
                    s="https://opentdb.com/api.php?amount=1&category=9&difficulty=easy&type=multiple";
                    switcher();
                    break;
                case 1:
                    s="https://opentdb.com/api.php?amount=1&category=17&difficulty=easy&type=multiple";
                    switcher();
                    break;
                case 2:
                    s="https://opentdb.com/api.php?amount=1&category=21&type=multiple";
                    switcher();
                    break;
                case 3:
                    s="https://opentdb.com/api.php?amount=1&category=27&difficulty=easy&type=multiple";
                    switcher();
                    break;
            }
        }
    }

    @Override
    public void onAnswer(String Answer) {
        if(q.getEditHint().equals(Answer)){
            score=score+1;
            if(score>highS){
                highS=score;
                tp.setHighScore(highS);
                getSharedPreferences(user,0).edit().putInt("highS",highS).commit();
                tp.setScore(score);
                tp.setHighScore(highS);
                media.start();
            }
            else {
                tp.setScore(score);
                tp.setHighScore(highS);
                media.start();
            }
        }
    }

    public void switcher(){
        Ion.with(this)
                .load(s)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String data) {
                        try {
                            JSONObject result=new JSONObject(data);
                            answer = result.getJSONArray("results")
                                    .getJSONObject(0).getString("correct_answer");
                            Question = result.getJSONArray("results")
                                    .getJSONObject(0).getString("question");
                        }
                        catch(Exception error){
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    error.getMessage(),
                                    Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                });
        q.setTextView(Question);
        q.setEditHint(answer);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        score= intent.getIntExtra("score",0);
        user= intent.getStringExtra("user");
        if(intent.getIntExtra("position",-9)==1){
            if(score>highS){
                highS=score;
                getSharedPreferences(user,0).edit().putInt("highS",highS).commit();
                media.start();
            }
            else{
                media.start();
            }
            tp.setScore(score);
            tp.setHighScore(highS);
        }
        else{
            tp.setScore(score);
            tp.setHighScore(highS);
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("score",score);
        outState.putInt("highS",highS);
        outState.putString("user",user);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        score=savedInstanceState.getInt("score");
        highS=savedInstanceState.getInt("highS");
        user=savedInstanceState.getString("user");
        tp.setScore(score);
        tp.setHighScore(highS);
    }
}
