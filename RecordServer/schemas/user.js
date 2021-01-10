const mongoose = require('mongoose');

const {Schema} = mongoose;
const userSchema = new Schema({
    name : {
        type : String,
        required : true,
    },
    fb_token : {
        type : String,
        required : true,
    },
    
});

module.exports = mongoose.model('User', userSchema);