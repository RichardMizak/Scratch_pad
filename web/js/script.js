const btn = document.getElementById("addbutton");
btn.addEventListener("click", ()=>{
    const name = document.getElementById("name").value;
    const priority = parseInt(document.getElementById("priority").value);
    const price = parseFloat(document.getElementById("price").value);
    const task = {name, priority};
    if(price>0){
        task.price=price;
    }   
    console.log(task);
    $.ajax({
        url:"http://localhost:3000/task/new",
        type: "post",
        dataType: "json",
        data: task,
        success: (result)=>{
            console.log(result);
        },
        error: (err)=>{
            console.log(err);
        }
    })
    alert("Task has been added");
});
