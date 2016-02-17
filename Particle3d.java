/**
 *Computer Modelling, Exercise 3: Particle3D class.
 *
 * @auther M. OSHEA
 * @auther C. JOHN
 * @version "11/2014"
 *
 */

import java.util.Scanner;

import java.lang.Math;

public class Particle3d {

    /* ******************************************
     * Properties
     ********************************************/

    private double mass;
    private Vector3d position;
    private Vector3d velocity; 
    private String label;  

    // Setters and Getters

 /** Get the position of a particle.
     *
     * @return a Vector3d representing the position.
     */

    public Vector3d getPosition() { return position; }

 /** Get the velocity of a particle.
     *
     * @return a Vector3d representing the velocity.
     */

    public Vector3d getVelocity() { return velocity; }
    
    /** Get the mass of a particle.
     *
     * @return a double representing the mass.
     */
    
    public double getMass() { return mass; }
    
    /** Get the label of a particle.
     *
     *@return a string representing the label.
     */

    public String getLabel() {return label;}
       

    /** Set the position of a particle.
     *
     * @param p a Vector3d representing the position.
     */
    
    public void setPosition(Vector3d p) { this.position = p;}

    /** Set the velocity of a particle.
     *
     * @param v a Vector3d representing the velocity.
     */
    
    public void setVelocity(Vector3d v) { this.velocity = v;}
    
    /** Set the mass of a particle.
     *
     * @param m a double representing the mass.
     */
    
    public void setMass(double m) { this.mass = m; } 


    /** Set the label of a particle.
     *
     *@param l a string representing the label.
     */

    public void setLabel(String l){this.label= l;}


    
    /* ******************************************
     * Constructors
     ********************************************/
    
    /** Default constructor. Sets all properties to a value
     * of zero.
     */
    
    public Particle3d() {
	this.setMass(0); 
	this.setPosition(new Vector3d());
	this.setVelocity(new Vector3d());
	this.setLabel("na");
    }
    /** Explicit constructor. Constructs a new Particle1D with
     * explicitly given position, velocity, and mass.
     *
     * @param m a double that defines the mass.
     * @param p a double that defines the position.
     * @param v a double that defines the velocity.
     * @param l a String that names the particle  
     */
    
    public Particle3d(double m, Vector3d p,  Vector3d v, String l) {
	this.setMass(m);
	this.setPosition(p);
	this.setVelocity(v);
	this.setLabel(l);
    }
    
    /* ******************************************
     * toString Method
     ********************************************/
    
    
    /** Returns a String representation of Particle3d.
     * Used to print a Particle3d instance using the "%s"
     * format identifier.
     */
    public String toString() {
	return "<" +  this.getLabel() + " >" +  this.getPosition() ;
    }
    
    /* ******************************************
     * Instance Methods
     ********************************************/
    
    
    /** Time integration support: evolve the position
     * according to dx = v * dt.
     *
     * @param dt a double that is the timestep.
     */
    public void leapPosition(double dt) {
	
        position = Vector3d.addVector(position, velocity.scalarMultiply(dt));
    }


    /** Time integration support: evolve the position
     * according to dx = x + v * dt + 0.5 * a * dt^2.
     *
     * @param dt a double that is the timestep.
     * @param force a vector that is the current force.
     */

    public void leapPosition(double dt, Vector3d force){
	Vector3d acceleration= new Vector3d();

	acceleration= force.scalarDivide(mass);

	position= Vector3d.addVector(Vector3d.addVector(position, velocity.scalarMultiply(dt)), acceleration.scalarMultiply(0.5*dt*dt));

    }
    

     /** Returns the kinetic energy of a Particle3d,
     * calculated as 1/2*m*v^2. Where v, is the magnitude of the
     *velocity vector . 
     *
     * @return a double that is the kinetic energy.
     */
    
    public double kineticEnergy() { return 0.5*mass*velocity.mag()*velocity.mag();}


    /** Method to calculate Gravitational Potential energy of two particles
     *
     *@param centre a Particle3d object to calculate potential energy from
     *
     *
     *@return a double that is the potential energy
     */


    public double potentialEnergy(Particle3d centre){
	
	Vector3d sep= new Vector3d(seperation(centre, this));

	double U=(-1.0*this.getMass()*centre.getMass())/sep.mag();

	return U;
    }
	

    

     /** Time integration support: evolve the velocity
     * according to dv = f/m * dt.
     *
     * @param dt a double that is the timestep.
     * @param force a Vector3d that is the current force on the particle.
     */
    public void leapVelocity(double  dt, Vector3d force) {
	
	velocity = Vector3d.addVector(velocity, force.scalarDivide(mass).scalarMultiply(dt));
        
    }
    
    
    /* ******************************************
     * Static Methods
     ********************************************/
    
    /** Scan variables from input file to create a particle.
     *
     *
     *@param info a Scanner object that provides mass, position, velocity and label.
     *@return a Particle3d object created from scanner object.
     */
    
    
    public static Particle3d readParticle(Scanner info){
	Particle3d a= new Particle3d();

	a.setMass(info.nextDouble());
	a.setPosition(new Vector3d(info.nextDouble(), info.nextDouble(), info.nextDouble()));
	a.setVelocity(new Vector3d(info.nextDouble(), info.nextDouble(), info.nextDouble()));
	a.setLabel(info.next());
	
	return a;
    }
    

    /**Determines the relative seperation of the position of two particles
     *
     *
     *@param centre a Particle3d object to determine the seperation from
     *@param orbit a Particle3d object to determine the seperation to
     *
     *@return a Vector3d object representing the relative seperation
     */ 

  public static  Vector3d seperation(Particle3d centre, Particle3d orbit){

	return new Vector3d( Vector3d.subVector(centre.getPosition(), orbit.getPosition()));

    }

    /**Converts a Vector3d object to a normalized vector in the same direction
     *
     *
     *
     *@param a Vector3d object to be normalized
     *
     *@return  Vector3d object that is normalized vector
     */


    public static Vector3d unitHat(Vector3d a){

	double mag=a.mag();

	return( a.scalarDivide(mag));
	    }

    /** Method to calculate the Gravitational force between two particles
     *
     *@param centre a Particle3d object for source of Gravitational potential energy
     *@param orbit a Particle3d object for target of Gravitational potential energy
     *@param skip an int to skip calculating force of particle due to itself
     *
     *@return Vector3d object representing Gravitational force
     */
    
    public static Vector3d forceCalc(Particle3d centre, Particle3d[] other, int skip){
	Vector3d force= new Vector3d();
       	double G=1.0; //6.674E-11;
	double magsep=0.0;
	Vector3d sep= new Vector3d();
	Vector3d numerator= new Vector3d();
	
	
	for (int i = 0 ; i < other.length ; i ++){ 

	    //skips index if it is index of centre particle
	    if (i ==skip){
		i+=i;}
	    
	    sep = Particle3d.seperation(centre,other[i]);
	    
	    magsep = sep.mag();
	    
	    numerator= unitHat(sep).scalarMultiply(centre.getMass()*other[i].getMass()*-1.0*G);
	    
	    numerator.scalarDivide(magsep*magsep);
	
	force= Vector3d.addVector(numerator, force);
	}
	
	return  (force);
    }
    
    
    
}
