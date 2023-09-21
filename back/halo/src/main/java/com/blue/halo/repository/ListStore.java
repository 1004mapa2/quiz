package com.blue.halo.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ListStore {

    private static List<String> musicStore = new ArrayList<>();
    private int musicLength = 0;

    public void saveMusicNameArray(String[] musicNameArray){

        //.mp3 확장자 없애서 List에 넣기
        for(String name : musicNameArray){
            musicStore.add(name.replace(".mp3", ""));
        }

        //음악 길이 저장
        musicLength = musicStore.size();
    }

    public int getMusicLength(){

        return musicLength;
    }

    public String getMusicName(String number){

        return musicStore.get(Integer.parseInt(number) - 1);
    }

    public int checkAnswer(int pageNumber){

        return Integer.parseInt(musicStore.get(pageNumber - 1));
    }

    public void shuffleMusicList(){
        Collections.shuffle(musicStore);
    }
}
