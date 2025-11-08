// 获取座位布局中的有效座位数，
// 参数seatArray是座位二维数组，-1表示此处无座位，0表示是有效座位
function getSeatNum(seatArray) {
    let seatNum = seatArray.length * seatArray[0].length;
    for (let i = 0; i < seatArray.length; i++) {
        for (let j = 0; j < seatArray[0].length; j++) {
            seatNum -= seatArray[i][j];
        }
    }
    return seatNum;
}