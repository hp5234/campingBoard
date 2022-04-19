console.log("hello!!");

function mapMaker() {
    // 컨트롤러에서 위치정보들 획득
    let places = ""; ///!*[[${places}]]*!/
    let container = document.getElementById('map');
    let options = {
        center: new kakao.maps.LatLng(36.350511, 127.384834),
        level: 5
    };
    console.log("지도 생성 성공 ")
    let map = new kakao.maps.Map(container, options); // 지도 생성

    // 지도에 마커 표기
    /*
    for ( var i = 0; i < places.length; i++) {
        makeMarker(map, places[i].id, places[i].latitude, places[i].longitude, places[i].member.userId)
    }*/
    //document.getElementById("map").setAttribute("style", "text-align:center");

    // 지도 클릭시 위도 경도 좌표 획득
    // 마커 클릭시
    //  1. 기존 목록 지우기
    //  2. 새로 조회된 게시판 목록 출력
}

// 지도에 마커 표기
function makeMarker(map, id, latitude, longitude, userId){

    // 마커를 표시할 위치입니다
    let position =  new kakao.maps.LatLng(latitude, longitude);

    // 마커를 생성합니다 // id 대신 title 을 디스플레이 하도록 변경
    let marker = new kakao.maps.Marker({
        title: id,
        position: position,
        clickable: true // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
    });

    marker.setMap(map);

    let iwContent = '<div style="padding:5px;">' + userId +'</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

    // 인포윈도우를 생성하고 지도에 표시합니다
    let infowindow = new kakao.maps.InfoWindow({
        content : iwContent,
        removable : iwRemoveable
    });

    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function(latitude, longitude) {
        // 마커 위에 인포윈도우를 표시합니다
        infowindow.open(map, marker);

        /**
         * 1. 서버로 요청
         * 2. latitude, longitude (위도, 경도) 로 디비 조회
         * 3. 게시물리스트 div 및 게시물 div 내용 지우기
         * 4. 나온 결과 목록(게시물리스트)을 div 에 출력
         */

        marker.getTitle();
        $.ajax({
            url: "/getNoticeList",
            data: {
                latitude: latitude,
                longitude: longitude
            },
            type:"POST",
        }).done(function (fragment) {
            let noticeList = document.getElementById("noticeList");

            $("#resultDiv").replaceWith(fragment);
        });

        // 게시판 리스트를 생성
        marker.getTitle();
        $.ajax({
            url: "/dataSend",
            data: messageDTO,
            type:"POST",
        }).done(function (fragment) {
            let noticeList = document.getElementById("noticeList");

            $("#resultDiv").replaceWith(fragment);
        });
    });
}

// 게시판 목록 출력
function makeNoticeList(){
    console.log("hello");
}

