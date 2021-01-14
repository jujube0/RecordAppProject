const PDFDocument = require('pdfkit');
const fs = require('fs');
const upload = require('../modules/multer');
const fileName = 'userdata'
const makeFile =  content => new Promise( (resolve, reject) => {
    fs.writeFileSync(`${fileName}.txt`, 'hello' + ": " + content);
  });

// pdf 내보내기
module.exports = {
    createPdf: async (req, res) => {
        try {
            // const doc = new PDFDocument;
            const doc = fs.readFileSync('userdata.pdf'); 
            // doc.text('hello thank you for using');
            // doc.end();
    
            // makeFile('my name is gayoung');
            // upload('userdata.txt')
    
            res.download("userdata.pdf", 'userdata.pdf', function (err) {
                if (err) {
                    console.error(err);
                } else {
                }
            });
            // res.contentType('application/pdf');
            // res.send(doc);

        } catch (err) {
            console.error(err);
        }
        
    }
}