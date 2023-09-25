const ip = '3.35.48.213';

function goToURL(){
    fetch('http://' + ip + ':8080/shuffleMusicList',{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
    .then(response => {
        if(!response.ok){
            throw new Error('http 오류: ' + response.status);
        }else{
            console.log('섞기 성공');
        }
    })
    .catch(error => {
        console.error('에러 발생:', erorr);
    });
    window.location.href = '/quiz';
}