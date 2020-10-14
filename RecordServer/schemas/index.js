const mongoose = require('mongoose');
const config = require('../config/key');
const url = config.mongoURI;

const connect = () => {
    if(process.env.NODE_ENV != 'production'){
        mongoose.set('debug', true);
    }
}

mongoose.connect(url, {
    useNewUrlParser: true, useUnifiedTopology : true, useCreateIndex : true, useFindAndModify : false
})
    .then(() => console.log("mongoDB connected..."))
    .catch(err => console.log(err));

mongoose.connection.on('error', error => {
    console.error()
})

module.exports = connect;