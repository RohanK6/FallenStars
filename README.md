# FallenStars
A program that parses NASA's Meteorite Landing data set and allows for users through the command line to search for Meteorites based on requested masses, years, or locations

Every year there are numerous meteorites entering Earth's atmosphere. In this project, I designed a tool for finding these fallen stars (or rather finding locations at which they were found for the first time). 

The goal of this programming project is for me to become more familiar with:

- working with multi-file programs
- reading data from input files
- using and understanding command line arguments
- working with large data sets
- using the ArrayList class
- writing classes
- implementing your own linked list
- working with existing code
- extending existing classes (inheritance)
- parsing data
- working with exception handling

The data set can be found on NASA's website: https://data.nasa.gov/Space-Science/Meteorite-Landings/gh4g-9sfh

## Program Details

The program should run in a loop that allows the user to issue different queries. The three types of queries are:
```
location LATITUDE LONGITUDE
year YEAR
mass MASS
```
In the above, the words in uppercase indicate keywords that will be replaced by actual values, for example mass 200 or location 50.7 6.08.

In the first case, location LATITUDE LONGITUDE, the program should display the meteorite whose landing site location is closest to the one specified by the user.

In the second case, year YEAR, the program should display the list of all meteorites that have landed during that year. The results should be displayed in alphanumeric order of the names.

In the third case, mass MASS, the program should display the list of all meteorites whose mass is within 10 grams of the user specified MASS (in grams). The results should be displayed in alphanumeric order of the names.

On each iteration, the user should be prompted to enter a new query (for which the program computes the results) or the word 'quit' to indicate the termination of the program.

If the query entered by the user is invalid, the program should display an error message and allow the user to provide an alternative query.:
```
This is not a valid query. Try again.
```
If the query entered by the user does not return any results, the program should print a message
```
No matches found. Try again.
```
If the query entered by the user matches one or more meteorite, the information should be displayed in the following format:
```
NAME ID YEAR MASS LATITUDE LONGITUDE
```
