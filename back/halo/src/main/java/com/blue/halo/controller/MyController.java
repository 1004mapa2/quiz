package com.blue.halo.controller;

import com.blue.halo.Dto.IdWordDto;
import com.blue.halo.Dto.NumberInputDto;
import com.blue.halo.repository.ListStore;
import com.blue.halo.repository.NameMapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class MyController {

    private final ListStore listStore;
    private final NameMapper mapper;

    //음악 리스트 순서 섞기
    @GetMapping("/shuffleMusicList")
    public void shuffleMusicList(){
        listStore.shuffleMusicList();
    }

    //음악 갯수 보내기
    @GetMapping("/getMusicLength")
    public int getMusicLength(){
        int musicLength = listStore.getMusicLength();

        return musicLength;
    }

    //음악 이름, 힌트 초성 보내기
    @PostMapping("/getMusicName")
    public String getMusicName(@RequestBody String number){
        String musicName = listStore.getMusicName(number);
        IdWordDto id_word = mapper.getWord(Integer.parseInt(musicName));
        Gson gson = new Gson();
        String jsonData = gson.toJson(id_word);

        return jsonData;
    }

    //음악 리스트 받아오기
    @PostMapping("/saveMusicList")
    public void saveMusicList(@RequestBody String musicList){
        Gson gson = new Gson();
        String[] musicListArray = gson.fromJson(musicList, String[].class);
        listStore.saveMusicNameArray(musicListArray);
    }

    //정답 체크하기
    @PostMapping("/checkAnswer")
    public int checkAnswer(@RequestBody NumberInputDto jsonData){
        int idNumber = listStore.checkAnswer(jsonData.getNumber());
        String answer = mapper.getAnswer(idNumber).getName();

        if(jsonData.getInput().equals(answer)){
            return 1;
        }
        return 0;
    }
}
