package com.example.isokarhu.testreco2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
//import java.util.Iterator;

public class TestReco2Activity extends AppCompatActivity {

    private TextView resultTEXT;
    private TextView matchTEXT;
    private TextView trialTEXT;
    public int listIter = 0;

    public ArrayList<vocaWord> insectsList;
    {
        insectsList = new ArrayList<vocaWord>() {{
            add(new vocaWord("butterfly"));
            add(new vocaWord("spider"));
            add(new vocaWord("bee"));   // confond avec "b"
            add(new vocaWord("ant"));   // compliqué
            add(new vocaWord("grasshopper"));
            add(new vocaWord("fly"));   // compliqué
            add(new vocaWord("mosquito"));
            add(new vocaWord("ladybird"));
            add(new vocaWord("wasp"));
            add(new vocaWord("caterpillar"));
            add(new vocaWord("earwig"));
            add(new vocaWord("cockroach"));
            add(new vocaWord("beetle"));
            add(new vocaWord("lice"));  // compliqué
            add(new vocaWord("cicada"));
        }};
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testreco2);
        resultTEXT = findViewById(R.id.TVresult);
        matchTEXT = findViewById(R.id.ToMatch);
        trialTEXT = findViewById(R.id.trial);
        matchTEXT.setText("To match : " + insectsList.get(listIter).getWord());
        trialTEXT.setText("Trial : " + insectsList.get(listIter).getTrial());
    }

    public void onStartClick(View view){

        if(view.getId() == R.id.imageButton){
            getSpeechInput();
        }
    }

    public void onNextClick(View view){
        if(insectsList.get(listIter).getSuccess() < 1)
            insectsList.get(listIter).setSuccess(0);

        listIter = (listIter + 1)%15;
        resultTEXT.setText("Response : ");
        matchTEXT.setText("To match : " + insectsList.get(listIter).getWord());
        trialTEXT.setText("Trial : " + insectsList.get(listIter).getTrial());
    }

    public void getSpeechInput(){

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-GB");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something !");

        try {
            startActivityForResult(intent, 10);
        }
        catch(ActivityNotFoundException a) {
            Toast.makeText(TestReco2Activity.this,
                    "Sorry your device doesn't support speech language",
                    Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int request_code, int result_code, Intent intent){

        super.onActivityResult(request_code, result_code, intent);

        switch(request_code)
        {
            case 10: if(result_code == RESULT_OK && intent != null)
                        {
                            ArrayList<String> result = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                            if(result.get(0).equals(insectsList.get(listIter).getWord() ) ){

                                resultTEXT.setText("MATCH");
                                insectsList.get(listIter).setSuccess(1);
                                trialTEXT.setText("Trial : " + insectsList.get(listIter).getTrial());
                            }
                            else {
                                resultTEXT.setText("FAIL ! You said : " + result.get(0));
                                insectsList.get(listIter).setSuccess(0);
                                trialTEXT.setText("Trial : " + insectsList.get(listIter).getTrial());
                            }
                        }
                        break;
        }
    }
}













