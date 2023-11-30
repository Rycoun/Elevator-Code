describe('CHALLENGE', () => {
    describe('getSumOfSubArrayValues', () => {
        it('should return a number', () => {
            getSumOfSubArrayValues([[23, 4, 33]]).should.be.a('number');
        });
        it('should return 0 if no parameter passed in', () => {
            getSumOfSubArrayValues().should.equal(0);
        });
        it('should return 0 if given an empty array', () => {
            getSumOfSubArrayValues([]).should.equal(0);
        });
        it('should add up all numbers in arrays and return sum', () => {
            let nestedArray = [[1, 2, 3], [1, 2, 3], [2, 2, 2, 2]];
            getSumOfSubArrayValues(nestedArray).should.equal(20);
        });
        it('should return 6 if given [[1, 3, 2]]', () => {
            getSumOfSubArrayValues([[1, 3, 2]]).should.equal(6);
        });
        it('should return 82 if given [[67], [34, -19]]', () => {
            getSumOfSubArrayValues([[67], [34, -19]]).should.equal(82);
        });
        it(' should return 397.4 if given [[44, 55, 66], [33.6, 22, 11], [77.8, 88]]', () => {
            getSumOfSubArrayValues([[44, 55, 66], [33.6, 22, 11], [77.8, 88]]).should.equal(397.4);
        });
    });
});