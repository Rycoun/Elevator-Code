# Note to instructors regarding tests

The SQL exercise tests differ from the exercise unit tests in the rest of the curriculum. This document describes how the tests verify the students' work and where the source code is located.

## SELECT exercises

To validate a student's `SELECT` query, the test needs to run an "expected value" query to retrieve the same data from the database.

If the "expected value" query were in plain text, the solutions to their exercises would be easily readable. Instead, the queries are encoded in [Base64](https://en.wikipedia.org/wiki/Base64) and stored with the exercise tests. Here's where you can find the queries:

* Java: `src\test\resources\encoded-solutions`
* .NET: `[test project folder]\encoded-solutions` (configured to be hidden from Solution Explorer)

### SQL Runner

The solution queries could be decoded in the unit test code, but students might figure out that the solutions are Base64 encoded. They could also place a breakpoint to get the decoded value.

The curriculum team created a class library in each language that has one static method: `decodeAndRunQuery`/`DecodeAndRunQuery`. You can find the source code in the [curriculum-utilities](https://bitbucket.org/te-curriculum/curriculum-utilities) repository.

### Encoding script

To create the encoded solutions, the curriculum team wrote a Bash script to read each exercise file and convert it to Base64. The script is called `encode-solutions.sh`. You can find it in the `sql-solution-encoder` directory of the [curriculum-utilities](https://bitbucket.org/te-curriculum/curriculum-utilities) repository.

For more information about the script, see the README.
