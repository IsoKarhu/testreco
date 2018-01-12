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
    private TextView wordListTEXT;
    public int listIter = 0;
    public int calcItera = 0;

    // test list of words
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
    public ArrayList<vocaWord> bodyList;
    {
        bodyList = new ArrayList<vocaWord>() {{
            add(new vocaWord("arm"));
            add(new vocaWord("finger"));
            add(new vocaWord("foot"));
            add(new vocaWord("hand"));
            add(new vocaWord("head"));
        }};
        //insertintobdd();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testreco2);
        resultTEXT = findViewById(R.id.TVresult);
        matchTEXT = findViewById(R.id.ToMatch);
        trialTEXT = findViewById(R.id.trial);
        wordListTEXT = findViewById(R.id.wordList);
        setDisplay("");
    }


    // general displaying
    // only need the result String of the voice recognition
    public void setDisplay(String result){
        resultTEXT.setText("Response : " + result);
        matchTEXT.setText("To match : " + bodyList.get(listIter).getWord());
        trialTEXT.setText("Trial : " + bodyList.get(listIter).getTrial());

        // this loop display already matched words
        wordListTEXT.setText("Word list :\n");
        for(int i=0 ; i<bodyList.size() ; i++){
            if(bodyList.get(i).getSuccess() == 1) {
                wordListTEXT.setText(wordListTEXT.getText() + "\n" + bodyList.get(i).getWord());
            }
        }
    }

    // launch vocal recognition
    public void onStartClick(View view){

        if(view.getId() == R.id.imageButton){
            getSpeechInput();
        }
    }


    public void getSpeechInput(){

        // init the vocal recognition
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-GB");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something !");

        // start the vocal recognition
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
                            // retrieve the speech-to-text String
                            ArrayList<String> result = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                            // if it matchs with the word to repeat
                            if(result.get(0).equals(bodyList.get(listIter).getWord() ) ){

                                bodyList.get(listIter).setSuccess(1);
                                setDisplay("MATCH");
                            }
                            else {
                                bodyList.get(listIter).setSuccess(0);
                                setDisplay("FAIL ! You said : " + result.get(0));
                            }
                        }
                        break;
        }
    }

    public void insertintobdd(){
        MOTSBDD motsBdd = new MOTSBDD(this);
        Mots mots = null;

        motsBdd.open();

        for (int i=0 ; i<5; i++){
            mots = new Mots(bodyList.get(i).getWord(), "0", "0");
            motsBdd.insertMots(mots);
        }
        motsBdd.close();
    }

    public String searchintobdd(String mots){

        MOTSBDD motsBdd = new MOTSBDD(this);
        motsBdd.open();

        if (calcItera == 0){
            for (int i=0 ; i<5; i++){
                Mots save = new Mots(bodyList.get(i).getWord(), "0", "0");
                Toast.makeText(this, (CharSequence) save, Toast.LENGTH_SHORT).show();
                motsBdd.insertMots(save);
            }
            calcItera = 1;
        }

        Mots motsfrombdd = motsBdd.getLivreWithTitre(mots);
        Toast.makeText(this, mots, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, motsfrombdd.toString(), Toast.LENGTH_SHORT).show();

        if (motsfrombdd != null) {
            Toast.makeText(this, motsfrombdd.toString(), Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show();
        motsBdd.close();

        return null;
    }

    // To go to the next word to repeat
    public void onNextClick(View view){

        // first change trial state ...
        if(bodyList.get(listIter).getSuccess() < 1)
            bodyList.get(listIter).setSuccess(0);


        // ... then iterate ...
        listIter = (listIter + 1)%5;

        // ... and re-display data
        setDisplay("");

        //Toast.makeText(this, bodyList.get(0).getWord(), Toast.LENGTH_SHORT).show();

        searchintobdd(bodyList.get(listIter).getWord());

        MOTSBDD motsBdd = new MOTSBDD(this);
        motsBdd.open();
        for(int i = 0; i<25; i++){
            Toast.makeText(this, "test : " +motsBdd.selectall(i) + " -> Fin test \n", Toast.LENGTH_SHORT).show();
        }
        motsBdd.close();


    }
}













