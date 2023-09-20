package com.blue.halo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "3.34.139.112")
public class MyController {

    private final ListStore listStore;

    //음악 갯수 보내기
    @GetMapping("/getMusicLength")
    public int getMusicLength(){
        int musicLength = listStore.getMusicLength();

        return musicLength;
    }

    //음악 이름 보내기
    @PostMapping("/getMusicName")
    public String getMusic(@RequestBody String number){
        String musicName = listStore.getMusic(number);

        return musicName;
    }
    
    //음악 리스트 받아오기
    @PostMapping("/saveMusicList")
    public void saveMusicList(@RequestBody MusicFolderDto musicJson){
        listStore.saveMusicLength(musicJson.getCount());
        listStore.saveMusic(musicJson.getFileNameList());
    }

    //정답 체크하기
    @PostMapping("/checkAnswer")
    public int checkAnswer(@RequestBody NumberInput jsonData){
        int result = listStore.checkAnswer(jsonData);

        return result;
    }
}
