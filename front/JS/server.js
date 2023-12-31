const ip = '3.35.48.213'
const express = require('express')
const app = express()
const port = 3000
const ejs = require('ejs')
const fs = require('fs')
const fetch = require('node-fetch')

app.set('view engine', 'ejs')
app.set('views', './views')

app.use(express.static(__dirname + '/public')) //public 폴더 안에 static 파일을 넣겠다

app.listen(port, function(){
    console.log('3000 서버 열었다')
});

// mp3 파일들 이름 보내주기
fs.readdir('public/music', (err, file) =>{
    fetch('http://' + ip + ':8080/saveMusicList',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(file)
    })
    .then(response => response.status)
    .catch(error => {
        console.error('에러 발생:', err);
    });
})

//라우팅
app.get('/', function(request, response){
    // response.sendFile(__dirname + '/index.html')
    response.render('main');
});

app.get('/quiz', function(request, response){
    response.render('quiz');
});

app.get('/endPage', function(request, response){
    response.render('endPage');
});

// app.post('/backend-endpoint', function(request, response){
//     response.render('map')
// });