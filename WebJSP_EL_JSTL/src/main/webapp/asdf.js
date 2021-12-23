let myAudios = document.querySelectorAll(".audio");

function changeVolume() {
    for (let index in myAudios) {
        myAudios[index].volume = window.innerWidth * 0.00065; //볼륨 : 0~1 <- 최대창 크기 시 볼륨 최대로 맞춰놓음
        myAudios[index].playbackRate = window.innerHeight * 0.0014; //재생속도 0.25~4 <- 최대창 크기 시 재생속도 1로 맞춰놓음
    }
}

window.addEventListener("resize", changeVolume);

function onlyOnePlay(e) {
    for (let i = 0; i < myAudios.length; i++) {
        let audio = myAudios[i];
        if (audio != e.target) {
            myAudios[i].pause();
        }
    }
}

for (let i = 0; i < myAudios.length; i++) {
    myAudios[i].addEventListener("play", onlyOnePlay);
}
