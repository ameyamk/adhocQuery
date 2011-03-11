
Setup:
In Run.java, under init() replace file paths to configure to your system

Running the program:

Run.java has main method for running the program

 
Description:

For the purpose of this project, this provides 3 various types of API's
 * 
 * Type 1: Simple API to query the data: These algorithms run over all the data
 * Type 2: Data Query API with conditions : These API's run over only subset of data using indexes. however indexes needs to be supplied.
 * Type 3: Recommendation System API: These data API's attempt at guessing what user may like given set of information. Since some of these
 *         algorithms can get very slow, all of these API's make use of precomputed data  

 * @author akanitkar
 * 
 * Extensibility:
 * Adding new columns of data is easy, since flat data is encapsulated in Record class. Also most of Type 1 and Type 2 query API's would work even on new columns without any code change.
 * Adding more complex data type such as tagclod would however need code change.
 * 
 * Data is encapsulated in singleton DBStore class. This can be easily extended for caching/ persitent storage, since all the data access is channelized through DBStore API's.
 * Once Index is built its stored globally in singleton instance. Hence no need to recompute indexes.
 * 
 *  If you have any more questions please feel free to email me at: 412-951-4913
 
 
 Design Thinking:
 Although many other design options were available, primary focus on my design remained at how flexible are things to change.
 Some of the algorithms go over all data, while some used indexes to go over all data. There are some algorithms which use precomputed
 data to create fast run time, but extra memory overhead. Design choices were made based on type of a query.
 
 