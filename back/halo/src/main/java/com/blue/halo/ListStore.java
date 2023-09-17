package com.blue.halo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class ListStore {

    private List<String> musicStore;
    private int musicLength = 0;

    public void saveMusic(String[] list){
        List<String> testList = new ArrayList<>();
        for(String name : list){
            testList.add(name.replace(".mp3", ""));
        }

        Collections.shuffle(testList);
        musicStore = testList;
    }

    public void saveMusicLength(int length){
        musicLength = length;
    }

    public int getMusicLength(){
        return musicLength;
    }

    public String getMusic(String number){

        return musicStore.get(Integer.parseInt(number) - 1);
    }

    public int checkAnswer(NumberInput jsonData){
        String answer = musicStore.get(jsonData.getNumber() - 1);
        String input = jsonData.getInput();

        if(answer.equals(input)){
            return 1;
        }
        return 0;
    }
}
