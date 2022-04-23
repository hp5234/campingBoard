function checkId(){

    let loginIdValue = document.getElementById("inLoginId").getAttribute("value");
    console.log(loginIdValue);

    // fetch
    const url ='/members/check';
    const option ={
        method:'POST',
        header:{
            'Accept':'application/json',
            'Content-Type':'application/json;charset=UTF-8'
        },
        body:JSON.stringify({
            "loginId" : loginIdValue
        })
    };

    fetch(url,option)
        .then(response => response.json())
        .then(response => console.log(response));
}