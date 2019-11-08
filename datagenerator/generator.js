
const fs = require('fs')
const Chance = require('chance')

var chance = new Chance(42);
var data = "";
var dataProduct = "";
var dataClientOrder = "";
var dataOrderline = "";
var ordercounter=0;

console.log("creating product :")
for (let i = 0; i < 1000000; i++) {
    if (i % 10000 == 0) {
        console.log(i / 10000 + "%")
    }
    dataProduct += "\"" + chance.word() + "\",\"" + chance.integer({ min: 0, max: 499 }) + "." + chance.integer({ min: 0, max: 99 }) + "\",\"" + chance.city() +
        "\",\"" + chance.sentence() + "\"\r\n";
}

fs.writeFile('../images/mysql/data/data-product.csv', dataProduct, (err) => {
    if (err) throw err;
})
dataProduct = null;

console.log("creating user and all data conected :")
for (let i = 0; i < 50000; i++) {
    if (i % 500 == 0) {
        console.log(i / 500 + "%")
    }
    var name = ""
    name = chance.name()
    data += "\"" + name + "\"" + ",\"" + chance.email() + "\",\"" + "$2a$10$Sc/KyCFbxbQ2uYOWi7TN/u86/znGgINgujcgownAI9/BRtwiIySCa" + "\"\r\n"


    for (let j = chance.integer({ min: 0, max: 3 }); j > 0; j--) {
        ordercounter++;
        let year = chance.integer({ min: 2000, max: 2019 })
        let mouth = chance.integer({ min: 1, max: 12 })
        let day = chance.integer({ min: 1, max: 28 });
        let ours = chance.integer({ min: 0, max: 23 });
        let min = chance.integer({ min: 0, max: 59 })
        let sec = chance.integer({ min: 0, max: 59 })
        if (mouth < 10) {
            mouth = "0" + mouth;
        }
        if (day < 10) {
            day = "0" + day;
        }

        dataClientOrder += "\"" + name + "\",\"" + year + "-" + mouth + "-" + day + " " + ours + ":"
            + min + ":" + sec + "\"\r\n";
        for (let k = chance.integer({ min: 1, max: 5 }); k > 0; k--) {
            let number = [];
            let b = true
            while (b) {
                b = false
                let num = chance.integer({ min: 1, max: 1000000 })
                number.forEach(element => {
                    if (element == num) {
                        console.log("pas de chance")
                        b = true
                    }
                });
                number[k] = num;
            }
            dataOrderline += "\"" + number[k] + "\",\"" + ordercounter + "\",\"" + chance.integer({ min: 1, max: 100 }) + "\"\r\n"
        }
    }
}


fs.writeFile('../images/mysql/data/data-client.csv', data, (err) => {
    if (err) throw err;
})


fs.writeFile('../images/mysql/data/data-clientorder.csv', dataClientOrder, (err) => {
    if (err) throw err;
})
fs.writeFile('../images/mysql/data/data-orderline.csv', dataOrderline, (err) => {
    if (err) throw err;
})
