var items=document.getElementsByClassName("item");// 图片
var points =document.getElementsByClassName("point");//点
var goPrebtn=document.getElementById("goPre");
var goNextbtn=document.getElementById("goNext");

var time=0;//定时器

var index=0;//index表示在第几张图片展示---》第index张图片有active这个类名
//第几个点在展示

var clearActive=function(){
    for (var i=0;i<items.length;i++){
        items[i].className='item';
    }
    for (var i=0;i<points.length;i++){
        points[i].className='point';
    }
}

var goIndex=function(){
    clearActive();
    points[index].className='point active'
    items[index].className='item active';
}

var goNext=function () {
    if (index<4){
        index++;
    }else{
        index=0;
    }
    goIndex();
}

var goPre=function(){
    if (index==0){
        index=4;
    }else {
        index--;
    }
    goIndex();
}

goNextbtn.addEventListener('click',function () {
    goNext();
})
goPrebtn.addEventListener('click',function(){
    goPre();
})

for(var i=0;i<points.length;i++){
    points[i].addEventListener('click',function () {
        var pointIndex=this.getAttribute('data-index');
        index=pointIndex;
        goIndex();
        time=0;
    })
}

//2000ms跳转一次

setInterval(function () {
    time++;
    if (time==20){
        goNext();
        time=0;
    }
},100)