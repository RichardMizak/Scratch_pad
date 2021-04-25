const express=require('express');
const mongodb=require('mongodb');
const URL='mongodb://localhost:27017';
const name='TaskDB';
const mongoClient=mongodb.MongoClient;
const app=express();
app.get('',(req,res)=> {res.send("Hello")})

app.get('/about',(req,res)=> {res.send("Node.js Server")})

app.get('/author',(req,res)=> {res.send({"firstname":"Richard"})})

app.get('/task',(req,res)=> {
    mongoClient.connect(URL,(error, client)=>{
    if(error){
        return console.log('Unable to connect to TaskDB');
    }else{
        let filter='{}';
        if (req.query.done=='true') {
            filter={done:true};
        }else{
            filter={done:true}; 
        }
    
        const db=client.db(name);
        const result = db.collection('Tasks').find().toArray((error,result)=>{
            if (error) throw error;
            console.log(result);    
            res.send(result);
        })
    }
    })
})
app.get('/task',(req,res)=> {
    MongoClient.connect(URL,(error, client)=>{
    if(error){
        return console.log('Unable to connect to TaskDB');
    }else{
        let filter='{}';
            if (req.query.priority=='1'){
            filter={priority:1};
        }else
            if (req.query.priority=='2'){
            filter={priority:2}; 
        }else
            if (req.query.priority=='3'){
            filter={priority:3}; 
        }else
        res.send('wrong priority')
        const db=client.db(name);
        const result = db.collection('Tasks').find().toArray((error,result)=>{
            if (error) throw error;
            console.log(result);    
            res.send(result);
        })
    }
    })
})
app.post('/inserttask',(req,res)=> {
    const data= req.body;
    const name=data.name;
    const priority=data.priority;
    let price;
    if (data.price) {
        price=data.price;
    }
    var newTask = {name: name, priority: priority, price: price, done: 'false', date: currentDate};
        const db=client.db(databaseName);
        db.collection("Tasks").insertOne(newTask, function(err, res) {
            if(err){
                return console.log('Unable to add new task');
            } else{
                console.log("1 document inserted");
            } 
        })
    })
app.listen(3000, ()=>{
    console.log('3000');
})
