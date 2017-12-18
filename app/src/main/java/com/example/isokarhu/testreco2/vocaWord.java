package com.example.isokarhu.testreco2;

/**
 * Created by tangu on 14/12/2017.
 */

public class vocaWord{
    private String p_word = "";

    // -1 = no try,    0 = tried but failed,    1 = success
    private int p_isSuccess = -1;


    vocaWord(String m_word){
        if(m_word != null)
            p_word = m_word;
        else
            p_word = "";

        p_isSuccess = -1;
    }


    int getSuccess(){ return p_isSuccess; }

    public void setSuccess(int m_isSuccess){
        p_isSuccess = m_isSuccess;
    }

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

    String getWord(){ return p_word; }

    public void setWord(String m_word){
        p_word = m_word;
    }
}
