/*
 * PACKAGE DEFINITION
 * 
 * lowercase
 * multi-word, separated by periods
 * aligned with a module or general purpose it provides
 */
package com.techelevator.draw.tool;


/*
 * IMPORTS
 * 
 * Optional, zero to many imports
 * Always below any package declaration, and above class declaration
 */
// Imports
import java.awt.Color;


/*
 * CLASS DECLARATION
 * 
 * Naming convention: singular and PascalCase
 */
public class WoodenPencil {
	
	/*
	 * CLASS VARIABLES
	 * 
	 * static modifier, 
	 * may be public or private, 
	 * often final, but not required to be
	 * 
	 * All wooden pencils have a "fixed" set of default values for length,
	 *   shape, hardness, and color. External code should be able to ask
	 *   WoodenPencil for these defaults.
	 *  
	 * All wooden pencils have a minimum length below which they are
	 *   considered a "stub" and should be discarded.
	 *   
	 * All wooden pencils have a minimum sharpness below which they are
	 *   too dull and need to be sharpened.
	 * 
	 * Note: These values belong to "ALL" wooden pencils, and thus
	 *   belong to the class of WoodenPencil, hence the static modifier.
	 */
	// constants
	public static final double DEFAULT_LENGTH = 8.0;			// default length
	public static final int DEFAULT_SHAPE = 2;					// default shape
	public static final String DEFAULT_HARDNESS = "HB";			// default hardness
	public static final Color DEFAULT_COLOR = Color.YELLOW;		// default color
	public static final double DEFAULT_STUB_LENGTH = 2.0;		// default stubLength
	public static final double DEFAULT_MAX_DULLNESS = 0.3;		// default maxDullness
	// adjustable/changeable
	private static double stubLength = DEFAULT_STUB_LENGTH;		// when pencil is a "stub", in inches
	private	static double maxDullness = DEFAULT_MAX_DULLNESS;	// when pencil needs sharpening (minimum sharpness)
	

	/*
	 * INSTANCE VARIABLES
	 * 
	 * Each instance of a wooden pencil has these individual attributes:
	 */
	private double length;		// in inches
	private final int shape;			// 1=triangular, 2=hexagonal, 3=round
	private final String hardness;	// "B", "HB", "F", "H", "2H"
	private final Color color;		// exterior, not lead
    private double sharpness;	// min 0.0, max 1.0
        

    /*
     * GETTERS and SETTERS
     * 
     * Getters and setters for instance variables, derived properties,
     * and class variables.
     * 
     * Getter
     *   starts with 'get' followed by instance variable name
     *   no parameters
     *   returns data type of the instance variable
     *   starts with 'is' rather than 'get' if return type is boolean
     *   usually public
     *   
     * Setter
     *   starts with 'set' followed by instance variable name
     *   single parameter of the same data type as instance variable
     *   return void
     *   usually public
     */
    /**
     * Standard getter for instance variable
     * 
     * @return instance length
     */
    public double getLength() {
		return length;
	}
    
    /**
     * Standard getter for instance variable
     * 
     * @return instance shape
     */
	public int getShape() {
		return shape;
	}

    /**
     * Standard getter for instance variable
     * 
     * @return instance hardness
     */
	public String getHardness() {
		return hardness;
	}

    /**
     * Standard getter for instance variable
     * 
     * @return instance color
     */
	public Color getColor() {
		return color;
	}

    /**
     * Standard getter for instance variable
     * 
     * @return instance sharpness
     */
	public double getSharpness() {
		return sharpness;
	}

	/**
	 * Derived property get
	 *   return data type doesn't need to follow instance variable(s)
	 *   starts with 'is' rather than 'get' if returns a boolean
	 * 
	 * @return true if instance length is less than or equal to stubLength, otherwise false
	 */
	public boolean isStub() {
		if (this.length <= stubLength) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Derived property get
	 *   return data type doesn't need to follow instance variable(s)
	 *   starts with 'is' rather than 'get' if returns a boolean
	 * 
	 * @return true if instance sharpness greater than maximum dullness, otherwise false
	 */
	public boolean isSharp() {
		if (this.sharpness > maxDullness) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Class variable get, note static modifier
	 * 
	 * @return class stubLength
	 */
	public static double getStubLength() {
		return stubLength;
	}

	/**
	 * Class variable set, note static modifier
	 * 
	 * @param stubLength, must >= 0.0 and <= defaultLength
	 */
	public static void setStubLength(double stubLength) {
		if (stubLength >= 0.0 && stubLength <= DEFAULT_LENGTH) {
			WoodenPencil.stubLength = stubLength;
		}
	}

	/**
	 * Class variable get, note static modifier
	 * 
	 * @return class maxDullness
	 */
	public static double getMaxDullness() {
		return maxDullness;
	}

	/**
	 * Class variable set, note static modifier
	 * 
	 * @param maxDullness, must >= 0.0 and <= 1.0
	 */
	public static void setMaxDullness(double maxDullness) {
		if (maxDullness >= 0.0 && maxDullness <= 1.0) {
			WoodenPencil.maxDullness = maxDullness;
		}
	}

	/*
	 * CONSTRUCTORS
	 * 
	 * Must match class name
	 * Does not return anything, not even void
	 */
	 /**
	  * Default constructor
	  * 
	  * Java will automatically generate an empty default constructor, or
	  * one may be written as shown below. 
	  * 
	  * Note the use of 'this()` to call the full overloaded constructor
	  * with default values.
	  */
	public WoodenPencil() {
		// standard wooden pencils are assumed to be unsharpened when first instantiated (i.e. 0.0)
	    this(DEFAULT_LENGTH, DEFAULT_SHAPE, DEFAULT_HARDNESS, DEFAULT_COLOR, 0.0);
	}
	
	/**
	 * Overload constructor
	 * 
	 * This one allows arguments/values to be passed in for all instance variables.
	 * All parameter values are checked for validity to ensure consistent state.
	 * 
	 * @param length
	 * @param shape
	 * @param hardness
	 * @param color
	 * @param sharpness
	 */
	public WoodenPencil(double length, int shape, String hardness, Color color, double sharpness) {
		if (length >= 0.0 && length <= DEFAULT_LENGTH) {
			this.length = length;
		}
		else {
			this.length = DEFAULT_LENGTH;
		}
		if (shape >= 1 && shape <= 3) {
			this.shape = shape;
		}
		else {
			this.shape = DEFAULT_SHAPE;
		}
		if (hardness.equals("B") || hardness.equals("F") || hardness.equals("H") || hardness.equals("2H")) {
			this.hardness = hardness;
		}
		else {
			this.hardness = DEFAULT_HARDNESS;
		}
		if (color != null) {
			this.color = color;
		}
		else {
			this.color = DEFAULT_COLOR;
		}
		if (sharpness >= 0.0 && sharpness <= 1.0) {
			this.sharpness = sharpness;
		}
		else {
			this.sharpness = 0.0;
		}
	}
	
	/*
	 * INSTANCE METHODS
	 * 
	 * Methods are named with verbs (i.e. GetSomething, CalculateSomething, DoSomething, AddSomething)
	 * All methods that return a value have a 'return' statement
	 * Must return the type defined in the method signature
	 */
	/**
	 * Instance method: Write
	 * 
	 * Calls overload 'write(double)` assuming 0.25 writing wear on sharpness.
	 * 
	 * @return result of calling overload 'write(double)'
	 */
    public boolean write() {
    	return write(0.25); // assume the standard 0.25 writing wear 
    }

    /**
     * Overload of instance method: write

	 * Checks sharpness of pencil to made it's not too dull to write.
	 * Assumes each writing session consumes 0.25 of sharpness and
	 *   adjusts the instance variable accordingly.
     * 
     * @param writingWear, the amount to "dull" the sharpness
     * 
     * @return true if able to write, otherwise false
     */
    public boolean write(double writingWear) {
    	if (this.sharpness > maxDullness) {
    		// OK to write, dull sharpness
    		double newSharpness = sharpness - writingWear;
    		if (newSharpness >= 0) {
    			this.sharpness = newSharpness;
    		}
    		else {
    			this.sharpness = 0.0; // writing session wore away all sharpness
    		}
    		return true;
    	}
    	else {
    		return false;
    	}
    }
	
    /**
     * Instance method: sharpen
     * 
     * Sharpening restores sharpness to 1.0, and removes 0.25 from the length of the pencil.
     * 
     * Pencil newly sharpened length may not be less than stubLength or 0.0.
     *  
     * @return true if pencil was sharpened, otherwise false
     */
    public boolean sharpen() {
    	double newLength = this.length - 0.25;
    	if (newLength >= stubLength && newLength >= 0.0) {
    		// OK to sharpen pencil
    		this.sharpness = 1.0;
    		this.length = newLength;
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    /*
     * STATIC METHODS
     * 
     * Static methods belong to the class, an "instance" isn't required to use them.
     * Static methods can't access instance members (e.g. properties or methods).
     * Static methods are good for utility functions that aren't dependent on instance variables.
     */
    /**
     * Class method: clayPercentage
     * 
     * The hardness of a pencil is dependent upon the percentage of clay mixed
     *   with the graphite. The higher the clay content, the harder the pencil.
     *   
     * @param hardness: "B", "HB", "F", "H", "2H"
     * 
     * @return percentage of clay of the hardness, or -1.0 if unknown
     */
    public static double clayPercentage(String hardness) {
    	// The following percentages are fictitious as actual clay to graphite percentages are trade secrets.
    	if (hardness.equals("B")) {
    		return 15.0;
    	}
    	else if (hardness.equals("HB")) {
    		return 20.0;
    	}
    	else if (hardness.equals("F")) {
    		return 25.0;
    	}
    	else if (hardness.equals("H")) {
    		return 30.0;
    	}
    	else if (hardness.equals("2H")) {
    		return 35.0;
    	}
    	else {
    		// hardness not valid
    		return -1.0;
    	}
    }


    public static void main(String[] args) {
    	
    	WoodenPencil pencil = new WoodenPencil();
    	System.out.println("Pencil length: " + pencil.length + " inches");
    	System.out.println("Pencil sharpness: " + pencil.sharpness);
    	System.out.println();
    	
    	pencil.sharpen();
    	System.out.println("Sharpening...");
    	System.out.println("Pencil length: " + pencil.length + " inches");
    	System.out.println("Pencil sharpness: " + pencil.sharpness);
    	System.out.println();
    	
    	pencil.write();
    	System.out.println("Writing...");
    	System.out.println("Pencil sharpness: " + pencil.sharpness);
    	System.out.println();
    	
    	pencil.sharpen();
    	System.out.println("Sharpening...");
    	System.out.println("Pencil length: " + pencil.length + " inches");
    	System.out.println("Pencil sharpness: " + pencil.sharpness);
    }
}
