let count = 1;
document.querySelector('.watBifile').volume = 0.5;

document.addEventListener('DOMContentLoaded', function(){
    document.querySelector('.countNumber').textContent = count;
    //음악 총 갯수 받아오기
    fetch('http://43.201.19.255:8080/getMusicLength',{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
    .then(response => {
        if(!response.ok){
            throw new Error('http 오류: ' + response.status);
        }
        return response.text();
    })
    .then(data => {
        document.querySelector('.maxNumber').innerHTML = data;
    })
    .catch(error => {
        console.error('에러 발생:', error);
    });


    // 맨 처음 음악 이름 받아오기
    fetch('http://43.201.19.255:8080/getMusicName',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(count)
    })
    .then(response => {
        if(!response.ok){
            throw new Error('http 오류: ' + response.status);
        }
        return response.text();
    })
    .then(data => {
        const musicFile = "/music/" + data + ".mp3";
        document.querySelector('audio').src = musicFile;
    })
    .catch(error => {
        console.error('에러 발생:', error);
    });
});

function next(){
    //숫자 늘리기 O
    //음악 바꾸기 O
    //input박스 비우기 O
    //maxNumber와 값이 같아지면 버튼 없애기 O
    if(count < document.querySelector('.countNumber').textContent){
        count++;
    }
    document.querySelector('.countNumber').textContent = count; //올라간 숫자 업데이트
    document.querySelector('#userInput').value = ""; //input박스 비우기
    if(document.querySelector('.nextButton').innerHTML == "끝"){
        마지막페이지();
    }
    else{
        if(count == document.querySelector('.maxNumber').innerHTML){
            document.querySelector('.nextButton').innerHTML = "끝";
        }
        fetch('http://43.201.19.255:8080/getMusicName',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(count)
        })
        .then(response => {
            if(!response.ok){
                throw new Error('http 오류: ' + response.status);
            }
            return response.text();
        })
        .then(data => {
            const musicFile = "/music/" + data + ".mp3";
            document.querySelector('audio').src = musicFile;
        })
        .catch(error => {
            console.error('에러 발생:', erorr);
        });
    }
}

function chackAnswer(){
    //input 박스내용 백엔드로 전달하기 O
    //받아온 값이 1이면 정답, 0이면 오답으로 처리
    //정답이면 버튼 사라지기
    //오답이면 ---이벤트
    const userInput = document.querySelector('#userInput').value;
    const dataToSend = {
        number: count,
        input: userInput
    };

    fetch('http://localhost:8080/checkAnswer',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(dataToSend)
    })
    .then(response => {
        if(!response.ok){
            throw new Error('http 오류: ' + response.status);
        }
        return response.text();
    })
    .then(data => {
        if(data == 1){
            console.log('성공');
            document.querySelector('.img2').classList.add('imgAfter');
            setTimeout(function(){
                document.querySelector('.img2').classList.remove('imgAfter');
            }, 2000);
            document.querySelector('.watBifile').play();

        }else if(data == 0){
            console.log('실패');
            document.querySelector('.img1').classList.add('imgAfter');
            setTimeout(function(){
                document.querySelector('.img1').classList.remove('imgAfter');
            }, 2000);
            document.querySelector('.matakufile').play();
        }
    })
    .catch(error => {
        console.error('에러 발생:', erorr);
    });
}

function 마지막페이지(){
    window.location.href = '/endPage';
}