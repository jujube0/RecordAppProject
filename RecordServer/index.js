const express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');
const app = express();
const connect = require('./schemas');
const postRouter = require('./routes/posts');
const indexRouter = require('./routes');
const port = 3000;

const admin = require('firebase-admin');

let serAccount = require('./config/record_movie.json');

admin.initializeApp({
  credential: admin.credential.cert(serAccount),
})


app.use(bodyParser.urlencoded({extended : true}));
app.use(bodyParser.json());

connect();

app.use('/',indexRouter);
app.use('/post', postRouter);

app.use((req, res, next) => {
    res.status(404).send('Not Found');
})

app.listen(port, () => console.log(`listening on port ${port}`));