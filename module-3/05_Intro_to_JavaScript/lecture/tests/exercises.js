/*
 * Given 2 strings, return their concatenation, except omit
 * the first char of each. The strings will be at least length 1.
 *   nonStart("Hello", "There") -> "ellohere"
 */
function nonStart(word1, word2) {
  return word1.substring(1) + word2.substring(1);
}

function exclude4Words(words) {

  const myArray = [];

  for (let word of words) {
    if (word.length !== 4) {
      myArray.push(word);
      // myArray.shift(word) //beginning 

    }

  }

}

function returnLargeSum(nums) {

  if (nums == null || nums.length === 0) {
    return null;
  }

  let small = nums[0];
  let large = nums[0];

  for (let i = 0; i < nums.length; i++) {
    if (nums[i] < small) {
      small = nums[i];
    }
    if (nums[i] > large) {
      large = nums[i];
    }
  }


  return small + large;
}
