function checkId(){

    let loginIdValue = document.getElementById("inLoginId").value;
    console.log("send data : " + loginIdValue);

    // fetch
    const url ='/members/check';
    const headers = new Headers();
    headers.append('Content-Type', 'application/json;utf8');
    // headers.append('Accept', 'application/json;utf8');

    const option ={
        method:'POST',
        headers: headers,
        body: JSON.stringify({
            loginId : loginIdValue
        })
    };

    fetch(url,option)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            if( data.result === "fail") {
                // 중복
                console.log("아이디 중복 ");
            } else {
                // 사용 가능
                console.log("아이디 사용가능 ");
            }
        });
}