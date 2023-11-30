/*

Original (Denormalized) Data
Username	Password       Name   		Address,                             		Address Type  	Tax Amount     	Biography
 walti       	12345 		Walt    	410 Scott Drive Pittsburgh, PA       		Primary            	3.0        		Instructor at Tech Elevator
 oliver        	2342		Oliver 	 	410 Scott Drive Pittsburgh, PA       		Secondary          	3.0        		Cat that interrupts class
 swifty     	abc12		Taylor    	55 House Road New York, NY    				Primary            	4.0        		Aspiring comedian


1NF - Make information discrete in separate columns; add keys
	- Separate the Address into Address_Id, Street, City, State
	- Add Person_Id and Address_Id
	
Username	Password  Person_Id    Name   Address_Id	Street			City		State         	Address Type  	Tax Amount     	Biography
 walti       	12345 	1		   Walt    	1			410 Scott Drive Pittsburgh  PA       		Primary            	3.0        		Instructor at Tech Elevator
 oliver        	2342	2		   Oliver 	1 			410 Doris Drive Pittsburgh  PA       		Secondary          	3.0        		Cat that interrupts class
 swifty     	abc12	3		   Taylor   2			55 House Road 	New York    NY    			Primary            	4.0        		Aspiring comedian

2NF - Eliminate Partial Dependencies 
	- Address Details were partially dependent on Address Id, but not Person Id. Move to another table
	- Person Details are partially dependent on Person Id, but not Address Id. Move to another table
	- We will need a person_address table after separating into person and address tables so that we can keep track of many-to-many
	
				PERSON TABLE
Username	Password  Person_Id    Name   	Biography						
 walti       	12345 	1		   Walt 	Instructor at Tech Elevator		
 oliver        	2342	2		   Oliver 	Cat that interrupts class		
 swifty     	abc12	3		   Taylor   Aspiring musician	
 
 			ADDRESS TABLE
Address_Id	Street			City		State       Tax Amount 
1			410 Scott Drive Pittsburgh  PA       	3.0 
2			55 House Road 	New York    NY    		4.0
3			123 Tech El		Philadelphia PA			10.0
 
 	PERSON_ADDRESS   TABLE
Person_Id	Address_Id		Address Type
1			1				Primary
2			1				Secondary
3			2				Primary		
 

3NF - Remove transitive dependencies
	- Tax Amount is dependent on State, which is then dependent on Address_Id. Move Tax Amount to a new table

				PERSON TABLE
Username	Password  Person_Id    Name   	Biography						
 walti       	12345 	1		   Walt 	Instructor at Tech Elevator		
 oliver        	2342	2		   Oliver 	Cat that interrupts class		
 swifty     	abc12	3		   Taylor   Aspiring musician	
 
				ADDRESS TABLE
   PK									FK
Address_Id	Street			City		State      
1			410 Scott Drive Pittsburgh  PA       	
2			55 House Road 	New York    NY    		
3			123 Tech El		Philadelphia PA			


	STATE TABLE
PK
State	Tax Amount
PA		3.0
NY		4.0


	PERSON_ADDRESS TABLE
			PK
  FK				FK			FK
Person Id		Address Id		Address Type
1					1			Primary
2					3			Primary
2					1			Secondary
3					2			Tertiary


Extra Normalization:
- Isolate Address Types so they cannot be updated to be invalid value

		PERSON_ADDRESS TABLE
  FK				FK			FK
Person Id		Address Id		Address_Type_Id
1					1			1
2					3			1
2					1			2
3					2			3

		ADDRESS_TYPE TABLE
PK
Type_Id		Type Name
1			Primary
2			Secondary
3			Tertiary


						PK
Username	Password  Person_Id    Name   	Biography						
 walti       	12345 	1		   Walt 	Instructor at Tech Elevator	
 oliver        	2342	2		   Oliver 	Cat that interrupts class	
 swifty     	abc12	3		   Taylor   Aspiring musician				




*/