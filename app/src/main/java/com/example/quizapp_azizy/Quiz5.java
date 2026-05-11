package com.example.quizapp_azizy;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz5 extends AppCompatActivity {
    RadioGroup rg;
    RadioButton rb;
    Button bNext, bAiExplain;
    TextView tvAiExplanation;
    int score;
    String RepCorrect="Non";
    String questionText = "En conduisant, je peux utiliser mon portable pour écrire un texto ou composer un numéro :";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz5);
        rg=(RadioGroup) findViewById(R.id.rg);
        bNext=(Button) findViewById(R.id.bNext);
        bAiExplain = (Button) findViewById(R.id.bAiExplain);
        tvAiExplanation = (TextView) findViewById(R.id.tvAiExplanation);

        Intent intent=getIntent();
        score=intent.getIntExtra("score",0) ;

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                bAiExplain.setVisibility(View.VISIBLE);
            }
        });

        bAiExplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvAiExplanation.setVisibility(View.VISIBLE);
                tvAiExplanation.setText("L'IA réfléchit...");
                AiHelper.getExplanation(questionText, RepCorrect, new AiHelper.AiCallback() {
                    @Override
                    public void onResponse(String explanation) {
                        tvAiExplanation.setText(explanation);
                    }

                    @Override
                    public void onError(String error) {
                        tvAiExplanation.setText("Erreur : " + error);
                    }
                });
            }
        });

        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rg.getCheckedRadioButtonId()==-1){
                    Toast.makeText(getApplicationContext(),"Merci de choisir une réponse S.V.P !",Toast.LENGTH_SHORT).show();
                }
                else {
                    rb=(RadioButton) findViewById(rg.getCheckedRadioButtonId());
                    if(rb.getText().toString().equals(RepCorrect)){
                        score+=1;
                    }
                    Intent intent=new Intent(Quiz5.this,Score.class);
                    intent.putExtra("score",score);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }
}
