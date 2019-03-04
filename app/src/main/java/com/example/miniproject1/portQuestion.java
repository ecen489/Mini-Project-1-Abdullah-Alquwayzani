package com.example.miniproject1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONObject;

public class portQuestion extends AppCompatActivity implements question.questionInterface {
    public int score;public int highS;public String user;public question q;
    String s;String answer="testMsg";String Question;public int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_port_question);
        Intent intent= getIntent();
        user=intent.getStringExtra("user");
        score=intent.getIntExtra("score",0);
        position=intent.getIntExtra("position",0);
        q= (question) getSupportFragmentManager().findFragmentById(R.id.questionFrag);
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch(position){
            case 0:
                s="https://opentdb.com/api.php?amount=1&category=9&difficulty=easy&type=multiple";
                break;
            case 1:
                s="https://opentdb.com/api.php?amount=1&category=17&difficulty=easy&type=multiple";
                break;
            case 2:
                s="https://opentdb.com/api.php?amount=1&category=21&difficulty=easy&type=multiple";
                break;
            case 3:
                s="https://opentdb.com/api.php?amount=1&category=27&difficulty=easy&type=multiple";
                break;
        }
        switcher();
    }

    @Override
    public void onAnswer(String Answer) {
        Intent intent=new Intent();
        if(Answer.equals(q.getEditHint())){
            score=score+1;
            intent.putExtra("score",(int) (score));
            intent.putExtra("position", (int) (1));
            intent.putExtra("user",user);
            setResult(1,intent);
            finish();
        }
        intent.putExtra("score",(int) (score));
        intent.putExtra("position", (int) (0));
        intent.putExtra("user",user);
        setResult(1,intent);
        finish();
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
                            q.setTextView(Question);
                            q.setEditHint(answer);
                        }
                        catch(Exception error){
                            q.setTextView(e.getMessage());
                            q.setEditHint(error.getMessage());
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    error.getMessage(),
                                    Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("score",score);
        outState.putInt("position",position);
        outState.putString("user",user);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        score=savedInstanceState.getInt("score");
        position=savedInstanceState.getInt("position");
        user=savedInstanceState.getString("user");
    }
}
