const express = require('express');
const router = express.Router();

const User = require('../schemas/user');
const admin = require('firebase-admin');

router.route('/').post(async (req, res) => {
    try {
        const { token } = req.body;
        const name = '김가영';
        
        const user = {
            name,
            fb_token : token,
        }
        const result = await User.create(user);
        console.log(result);
        return res.status(201).json({success : true});
    } catch (err) {
        console.error(err);
    }
})

// push PAGE
router.get('/push_send', function (req, res, next) {
  let target_token ="cpj87payQpGltleGUIrgTU:APA91bEDIbSB5-vyPZiW4uOes5_YsqbaOekAQcyl1sRwWGHCaLPEdEAxfASfBtDsAIKG2wK_ZKRd-0yC77T3OXks-aXX2cbNjt1FGmFAOhnDtdjqoh-LtD3fDyTJtONF5auTLwpcqoAa"
	//target_token은 푸시 메시지를 받을 디바이스의 토큰값입니다

  let message = {
    data: {
      title: '테스트 데이터 발송',
      body: '데이터가 잘 가나요?',
      hello : 'hh'
    },
    token: target_token,
  }

  admin
    .messaging()
    .send(message)
    .then(function (response) {
      console.log('Successfully sent message: : ', response)
      return res.status(200).json({success : true})
    })
    .catch(function (err) {
        console.log('Error Sending message!!! : ', err)
        return res.status(400).json({success : false})
    });

})

module.exports = router;