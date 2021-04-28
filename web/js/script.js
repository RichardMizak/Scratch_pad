const btn=documnet.getElementById("addbutton");
btn.addEventListener("click", ()=>{
    const name=document.getElementById("name").value;
    const priority=parseInt(document.getElementById("priority").value);
    const price=parseFloat(document.getElementById("price").value);
    const object={name, priority};
    if (price>0) {
        object.price=price;
    }
    console.log(object);
    $.ajax({
        type: "POST",
        url: "http://localhost:3000/task/new",
        dataType: "json",
        contentType:"application/json",
        data: object,
        success: function (data) {
           alert('Success');
        },
        error: function () {
         alert('Error');
        }
    });
});