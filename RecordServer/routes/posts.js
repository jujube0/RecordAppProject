const express = require('express');
const Post = require('../schemas/post');
const controller = require('../controller/postController');
const upload = require('../modules/multer');

const router = express.Router();
router.post('/post', controller.createPdf);
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

            if(req.body.img_url!='Undefined' || ''){
                post.img_url = req.body.img_url;
            }

            const result = await Post.create(post)
            console.log(result);
            res.status(201).json({success : true});
        }catch (err){
            console.log(err);
            next(err);
        }
    });

router.route('/detail/:post_id')
    .get(async (req, res, next) => {
        try{
            const ObjectId = require('mongoose').Types.ObjectId;
            const post = await Post.findOne({_id : ObjectId(req.params.post_id)})
            res.status(200).json(post);
            console.log(post);

        }catch(err){
            console.log(err);
            next(err);
        }
    })
    .delete(async (req, res, next) => {
        try{
            const ObjectId = require('mongoose').Types.ObjectId;
            const result = await Post.deleteOne({_id : ObjectId(req.params.post_id)});
            if(result.deletedCount == 0){
                res.status(402).json({
                    success : false,
                    message : "일치하는 게시물이 존재하지 않습니다"
                });
            }else{
                res.json({
                    success : true,
                    message : "게시물 삭제 완료"
                });
            }
        }catch(err){
            console.log(err);
            next(err);
        }
    })
    .patch(async (req, res, next) => {
        try{
            const ObjectId = require('mongoose').Types.ObjectId;
            const obj = req.body
            const del_obj = {}
            for(let key in obj){
                del_obj[key] = obj[key];
            }
            const result = await Post.update({ _id : ObjectId(req.params.post_id)},  del_obj );
            console.log(del_obj);
            if(result.n == 0){
                res.status(400).json({
                    success : false,
                    message : "일치하는 게시물이 없습니다"
                })
            }else{
                res.json({
                    success : true,
                    message : "게시물 수정 완료"
                })
            }
            

        }catch(err){
            console.error(err);
            next(err);
        }
    })


module.exports = router;