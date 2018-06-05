var express = require("express")
var bodyParser = require("body-parser")
var fs = require("fs")

const app = express()
app.use(bodyParser.json())
var words;

fs.readFile("../trail.json","utf-8", (err, data)=>{
    if(err){
        console.log("Error while reading file")
    }
    else{
        words = JSON.parse(data.trim())
        console.log(words)
    }
})
app.get("/" ,(req, res)=>{
    res.type('application/json')
    res.status(200)
    res.send(words)
})


const server = app.listen(4000, () =>{
    const host = server.address().address
    const port = server.address().port
    console.log(`app running on ${host}:${port}`)
})

