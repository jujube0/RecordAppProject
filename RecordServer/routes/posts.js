const express = require('express');
const Post = require('../schemas/post');

const router = express.Router();

router.route('/')
    .get(async (req, res, next) => {
        try{
            const posts = await Post.find({});
            res.json(posts);
        } catch (err){
            console.log(err);
            next(err);
        }
    })
    .post(async (req, res, next) => {
        try{
            const post = {
                type : req.body.type,
                title : req.body.title,
                rate_count : req.body.rate_count,
                content : req.body.content,
            }
            if(post.type == "book"){
                post.author = req.body.author;
            }else{
                post.release_date = req.body.release_date;
            }
            console.log(req.body);
            console.log(req.body.img_url);

            if(req.body.img_url!='Undefined' || ''){
                post.img_url = req.body.img_url;
            }

            await Post.create(post)
            res.status(201).json({success : true});
        }catch (err){
            console.log(err);
            next(err);
        }
    });


module.exports = router;