const mongoose = require('mongoose');

const {Schema} = mongoose;
const postSchema = new Schema({
    title : {
        type : String,
        required : true,
    },
    rate_count : {
        type : Number,
        required : true,
    },
    content : {
        type : String,
        required : true
    },
    written_date : {
        type : String,
        required : false
    },
    type : {
        type : String,
        required : true
    },
    img_url : {
        type : String,
        required : false
    },
    author : {
        type : String,
        required : false,
    },
    release_date : {
        type : String,
        requied : false,
    },
});

module.exports = mongoose.model('Post', postSchema);