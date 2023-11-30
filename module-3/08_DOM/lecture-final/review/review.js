function printAllItems(array) {
    array.forEach(element => {
        console.log(element);
    })
}

const myArray = ['Hi', '1', '2'];
printAllItems(myArray);


function doubleMe(array) {
    return array.map(element => element * 2);
}

const numbers = [1,2,3,4];
doubleMe(numbers);


function map(array, callback) {
    const newArray = [];

    array.forEach((element, index, origArray) => {
        const result = callback(element, index, origArray);
        newArray.push(result);
    });

    return newArray;
}

map(numbers, element => element * 2);

// numbers =  [1,2,3,4]
//             ^
// newArray = [2,]