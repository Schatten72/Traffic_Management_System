startFetching();

function startFetching() {
    fetchData();
    setInterval(fetchData, 60000);
    // alert("ok");
}

function fetchData() {
    var request = new XMLHttpRequest();

    request.onreadystatechange = function () {
        if(request.readyState == 4) {
            // alert(request.responseText);
            var obj = JSON.parse(request.responseText);


            var averageSpeed = obj.averageSpeed;
            var congestionLevel = obj.congestionLevel;
            var trafficLightStatus = obj.trafficIntersection;


            updateSpeedTable(averageSpeed);
            updateTrafficPatternTable(congestionLevel);
            updateTrafficLightStatus(trafficLightStatus);
        }
    }

    request.open("POST", "dataReceiver", true);
    request.send();
}

function updateSpeedTable(averageSpeedArr) {
    document.getElementById("r1_t1").innerHTML = averageSpeedArr['r1_t1'];
    document.getElementById("r1_t2").innerHTML = averageSpeedArr['r1_t2'];
    document.getElementById("r1_t3").innerHTML = averageSpeedArr['r1_t3'];
    document.getElementById("r1_t4").innerHTML = averageSpeedArr['r1_t4'];
    document.getElementById("r2_t1").innerHTML = averageSpeedArr['r2_t1'];
    document.getElementById("r2_t2").innerHTML = averageSpeedArr['r2_t2'];
    document.getElementById("r2_t3").innerHTML = averageSpeedArr['r2_t3'];
    document.getElementById("r2_t4").innerHTML = averageSpeedArr['r2_t4'];
}

function updateTrafficPatternTable(congestionLevel) {
    Object.entries(congestionLevel).forEach(function ([key, value]) {
        var routNo = key.charAt(1);
        var timelineNo = key.charAt(4);
        updateTrafficPatternData(routNo, timelineNo, value);
    });
}

function updateTrafficPatternData(routNo, timelineNo, level) {
    var id = "c_r" + routNo + "_t" + timelineNo;
    var td = document.getElementById(id);
    td.innerHTML = level;

    if(level=="HEAVY") {
        td.style.backgroundColor = "#c21717";
    } else if(level=="NORMAL") {
        td.style.backgroundColor = "#0440e6";
    } else if(level=="LIGHT") {
        td.style.backgroundColor = "#0ac90a";
    } else if(level=="NULL") {
        td.style.backgroundColor = "white";
    }
}

function updateTrafficLightStatus(trafficIntersection) {
    var status_t1 = trafficIntersection['t1'];
    var status_t2 = trafficIntersection['t2'];

    removeStyle();

    if(status_t1 == "RED") {
        document.getElementById("tli_1_red").classList.add("status-red");
    } else if(status_t1 == "YELLOW") {
        document.getElementById("tli_1_yellow").classList.add("status-yellow");
    } else if(status_t1 == "GREEN") {
        document.getElementById("tli_1_green").classList.add("status-green");
    }

    if(status_t2 == "RED") {
        document.getElementById("tli_2_red").classList.add("status-red");
    } else if(status_t2 == "YELLOW") {
        document.getElementById("tli_2_yellow").classList.add("status-yellow");
    } else if(status_t2 == "GREEN") {
        document.getElementById("tli_2_green").classList.add("status-green");
    }
}

function removeStyle() {
    document.getElementById("tli_1_red").classList.remove("status-red");
    document.getElementById("tli_1_yellow").classList.remove("status-yellow");
    document.getElementById("tli_1_green").classList.remove("status-green");
    document.getElementById("tli_2_red").classList.remove("status-red");
    document.getElementById("tli_2_yellow").classList.remove("status-yellow");
    document.getElementById("tli_2_green").classList.remove("status-green");
}