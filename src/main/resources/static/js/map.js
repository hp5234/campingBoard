// 카카오 맵 생성
function mapMaker(boardList) {
    console.log(boardList)
    // 컨트롤러에서 위치정보들 획득
    let container = document.getElementById('map');
    let options = {
        center: new kakao.maps.LatLng(36.350511, 127.384834),
        level: 7
    };
    console.log("지도 생성 성공 ")
    let map = new kakao.maps.Map(container, options); // 지도 생성

    // 지도에 클릭 이벤트를 등록합니다
    // 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
    kakao.maps.event.addListener(map, 'click', function(mouseEvent) {

        // 클릭한 위도, 경도 정보를 가져옵니다
        var latlng = mouseEvent.latLng;

        let latitude = latlng.getLat(); // 위도 정보
        let longitude = latlng.getLng(); // 경도 정보

        // 위도 경도 출력
        document.getElementById("spotDisplayLatitude").innerText = "위도 : " + latitude;
        document.getElementById("spotDisplayLongitude").innerText = "경도 : " + longitude;

        // 게시판 추가 버튼 보이기
        document.getElementById("btnAddBoard").setAttribute("style", "display: block");

        // 위도 경도 값 부여
        document.getElementById("inLatitude").setAttribute("value", latitude);
        document.getElementById("inLongitude").setAttribute("value", longitude);
    });

    // 지도에 마커 표기
    for ( var i = 0; i < boardList.length; i++) {
        makeMarker(map, boardList[i].id, boardList[i].latitude, boardList[i].longitude, boardList[i].title);
    }
}

// 지도에 마커 표기
function makeMarker(map, id, latitude, longitude, boardTitle){

    // 마커를 표시할 위치입니다
    let position =  new kakao.maps.LatLng(latitude, longitude);

    // 마커를 생성합니다 // id 대신 title 을 디스플레이 하도록 변경
    let marker = new kakao.maps.Marker({
        title: boardTitle,
        position: position,
        clickable: true // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
    });

    marker.setMap(map);

    // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    let iwContent = '<div><button id="boardButton' + id +'" type="button" class="markerButton" onClick={clickEvent(' + id + ',"' + boardTitle + '")}>' + boardTitle + '</button></div>',
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

    // 인포윈도우를 생성하고 지도에 표시합니다
    let infowindow = new kakao.maps.InfoWindow({
        content : iwContent,
        removable : iwRemoveable
    });

    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function() {
        // 마커 위에 인포윈도우를 표시합니다
        infowindow.open(map, marker);
    });
}

// 마커 제목 클릭 이벤트
function clickEvent(boardId, boardTitle) {

    // 게시물목록에 게시판 제목 표기
    document.getElementById("boardTitle").innerText = boardTitle;

    // 게시물 추가 버튼 값 설정
    let inBoardId = document.getElementById("inBoardId");
    inBoardId.setAttribute("value", parseInt(boardId));

    // 게시물 추가 버튼 보이기
    document.getElementById("btnAddNotice").setAttribute("style", "text-align: right; display: block");

    // 기존 목록 삭제
    let noticeSpace = document.getElementById("noticeSpace");
    while (noticeSpace.hasChildNodes()) {
        noticeSpace.removeChild(noticeSpace.firstChild);
    }

    // fetch
    const url ='/notice/list/' + boardId;
    const option ={
        method:'GET'
        /*
        header:{
            'Accept':'application/json',
            'Content-Type':'application/json;charset=UTF-8'
        }
         */
        /*
        body:JSON.stringify({
            boardId : id
        })
        */
    };
    fetch(url,option)
        .then(response => {

            response.json().then(function (noticeList){
                for ( let i = 0; i < noticeList.length; i++ ){
                    // notice list Dom 생성
                    let newNotice = document.createElement("a");
                    newNotice.setAttribute("class", "list-group-item list-group-item-action py-3 lh-tight");
                    newNotice.setAttribute("href", "/notice/" + noticeList[i].id );

                    let newNoticeDiv = document.createElement("div");
                    newNoticeDiv.setAttribute("class", "d-flex w-100 align-items-center justify-content-between");

                    let newNoticeTitle = document.createElement("strong");
                    newNoticeTitle.setAttribute("class", "mb-1");
                    newNoticeTitle.innerText = noticeList[i].title;

                    let newNoticeTime = document.createElement("small");
                    newNoticeTime.innerText = noticeList[i].contents;

                    newNoticeDiv.appendChild(newNoticeTitle);
                    newNoticeDiv.appendChild(newNoticeTime);

                    newNotice.appendChild(newNoticeDiv);

                    noticeSpace.appendChild(newNotice);
                }
            });
        })
}
