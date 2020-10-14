const express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');
const app = express();
const connect = require('./schemas');
const postRouter = require('./routes/posts');
const port = 3000;


app.use(bodyParser.urlencoded({extended : true}));
app.use(bodyParser.json());

connect();

app.use('/post', postRouter);

app.use((req, res, next) => {
    res.status(404).send('Not Found');
})

app.listen(port, () => console.log(`listening on port ${port}`));