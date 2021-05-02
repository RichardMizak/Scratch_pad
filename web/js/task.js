$(()=>{
    $.ajax({
        url:"http://localhost:3000/task/show",
        type: "get",     
        statusCode:{
            200: (result) => {
                for(var index in result){
                    const id = result[index]._id;
                    const name = result[index].name;
                    const priority = result[index].priority;
                    const date = result[index].date;
                    const done = result[index].done;
                    const price = result[index].price;
                    let text = "<b>" + name + "</b>" + " (" + date + ") <br>";
                    text = text +  "<b> Priority </b>: " + priority;
                    if(price >= 0){
                        text = text + "<b> Price: </b>" + price+"€";
                    } 
                    if(done=false){
                        text = text + "<button onClick=\'complete(\""+id+"\")'\ id='cbtn'>Complete</button>";
                    }
                    text = text + "<b> Done: </b>" + done;
                    $("#mainContainer").append("<div>" +"<div class='center'>" + text + "</div>" + "</div> <br>");
                }
            },
            400: (err) => {
                console.log('Bad request');
            },
            404: (err) => {
                console.log('Not found');
            }
        }
    })
})
