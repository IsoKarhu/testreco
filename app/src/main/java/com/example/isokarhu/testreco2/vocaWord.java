package com.example.isokarhu.testreco2;

/**
 * Created by tangu on 14/12/2017.
 */

/**
 * "vocaWord"
 *
 * The vocabulary class,
 * which contained the vocabulary word (String) to repeat,
 * and the state of the trials (well repeated or not...).
 *
 */
public class vocaWord{

    /***** PARAMETERS *******/

    // The vocabulary word contained
    private String p_word = "";

    // state of the trials
    // -1 = no trials,    0 = tried but failed,    1 = success
    private int p_isSuccess = -1;




    /***** CONSTRUCTORS *******/

    vocaWord(String m_word){
        if(m_word != null)
            p_word = m_word;
        else
            p_word = "";

        p_isSuccess = -1;
    }


    /***** GETTERS & SETTERS *******/

    String getWord(){ return p_word; }

    public void setWord(String m_word){
        p_word = m_word;
    }



    int getSuccess(){ return p_isSuccess; }

    public void setSuccess(int m_isSuccess){
        p_isSuccess = m_isSuccess;
    }


    /***** OTHER METHODS *******/

    // getTrial() method read the m_isSuccess flag value and
    //      return a more explicit String than the flag value.
    String getTrial(){
        switch(p_isSuccess)
        {
            case -1:
                return "never tried";
            case 0:
                return "not passed";
            case 1:
                return "already passed";
            default:
                break;
        }
        return "ERROR";
    }
}
