/*
 * Given 2 strings, return their concatenation, except omit
 * the first char of each. The strings will be at least length 1.
 * 
 *   nonStart("Hello", "There") -> "ellohere"
 */
function nonStart(x, y) {
  return x.substring(1) + y.substring(1);
}


function excludeLength4Words(words) {
  const result = [];

  for (const word of words) {

    if (typeof word === 'string' && word.length !== 4) {
      result.push(word);
      // result.shift(word); // put at the beginning
    }

  }

  return result;
}

// [ 1, 2, 3, 4, 5 ]
//               ^
function returnSumOfLargestAndSmallestNumber(nums) {

  if (nums == null || nums.length === 0) {
    return null;
  }

  let smallest = nums[0];
  let largest = nums[0];

  for (let i = 1; i < nums.length; i++) {

    if (nums[i] < smallest) {
      smallest = nums[i];
    }

    if (nums[i] > largest) {
      largest = nums[i];
    }

  }

  return smallest + largest;
}