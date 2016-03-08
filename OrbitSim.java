/**
 *
 * N-body Simulation of the Solar System:
 *
 * This simulation utilizes the velocity-Verlet time-integration method in
 * conjuncture with Newtonian Gravitation to simulate the orbits of 
 * bodies in the solar system.
 *
 *
 * The command line takes five arguments. The first argument is the file name of the file containing the 
 * intial states of every particle. There should be an integer at the start of the file indicating the number
 * of particles in the system. From here, each particle's intial state should be described as follows:
 *
 *=============================================================
 * A string designating the particle label.
 * A double indicating the mass of the particle.
 * Three doubles indicating the initial position of the particle.
 * Three doubles indicating the initial velocity of the particle.
 *============================================================ 
 *
 * Repeat this sequence for all desired particles in the system.
 *
 * IMPORTANT NOTE: The Sun needs to be the first particle in the initial input file. Earth's 
 * moon needs to be the second.
 *
 * The second command line argument is the file name correlating to the parameters of the system. These
 * should be in the following sequence:
 *
 *===============================================================
 * A double indicating the number of time-steps to evolve over.
 * A double indicating the size of each time-step.
 * A double indicating the value of the gravitational constant, G.
 * =============================================================
 *
 * The third command line argument is the name of the file to write the VMD trajectory info to.
 *
 * The fourth command line argument is the name of the file to write the total energy values to.
 * The fifth command line argument is the name of the file to write the orbit count values and eccentricity values to.
 *
 *
 *
 *@author C. John
 *@author M. O'Shea
 *@version "02/2016"
 */


import java.io.*;
import java.util.Scanner;
import java.text.*;
import java.math.*;

public class OrbitSim {


     /**
     * Main method to run OrbitSim class.
     *
     *@param argv[0] initial state of the particles, with first integer for number of particles in system.
     *@param argv[1] values of the parameters.
     *@param argv[2] name of the output trajectory file for the VMD writing.
     *@param argv[3] name of the output file to monitor energy fluctuations. 
     *@param argv[4] name of the output file to track orbits and eccentricities.
     *
     */

    // We are using file IO so we are throwing an exception


    public static void main (String[] argv) throws IOException{



	/*Generating all necessary file reading and file wriitng capabilities
	 *
	 *
	 *
	 *
	 *
	 */



	//orbitSimReader to particleInfo
	//create reader for initial states of particles
	BufferedReader particleInfo = new BufferedReader(new FileReader(argv[0]));

	//orbitSimScan to particleScan
	//create scanner from reader for initial states of particles
	Scanner particleScan= new Scanner(particleInfo);

	// create array of particles from scanner object
	Particle3d[] particles = Particle3d.particleArray(particleScan);

	//create reader for parameter file
	BufferedReader parameterRead = new BufferedReader(new FileReader(argv[1]));

	//create scanner from reader for parameter file
	Scanner parameterScan= new Scanner(parameterRead);

	//arrayPositions to positionOutput
	//create writer for VMD output
        PrintWriter positionOutput = new PrintWriter(new FileWriter(argv[2]));

	//create writer for energy output
	PrintWriter energyOutput = new PrintWriter(new FileWriter(argv[3]));

	//create writer for orbit count and eccentricities
	PrintWriter orbitOutput = new PrintWriter(new FileWriter(argv[4]));
    


	
	/* Intialize all parameters, energy, and force array
	 *
	 *
	 *
	 *
	 *
	 */

	
	//save parameters from scanner object to global variables
	double numStep= parameterScan.nextDouble();
	double sizeStep= parameterScan.nextDouble();
	double G= parameterScan.nextDouble();

	double[] count = new double[particles.length];	

	// Set the value of G read from input file
	Particle3d.setG(G);

	//Initial time
	double t=0.0;

	//create an array of doubles to track aphelions and parahelions
	double[] aphelion= new double[particles.length];
	double[] perihelion= new double[particles.length];


	//initialize double arrays
	for(int i=0;i<particles.length;i++){
	    aphelion[i]= 0.0;
	    perihelion[i]= 100000000;
	}

	//Initial energies
	double totalE= Particle3d.potentialEnergy(particles) + Particle3d.kineticEnergy(particles);


	//Calculate Initial Force
	Vector3d[] force = Particle3d.forceCalc(particles);
	

	//Print these initial states to file

	energyOutput.printf("%10.5f %10.5f \n", t, totalE); //something wrong here
	
	Particle3d.toVMD(particles, positionOutput, t);

       

	//calculate Initial vCoM
	Vector3d vCoM = new Vector3d();

	//initialize total mass
	double massTotal = 0.0;



	//Adjusting initial velocities of all bodies

	Particle3d.adjustedVelocitys(particles);



	/* Start of loop for time-integration via Velocity-Verlet algorithm
	 *
	 *
	 *
	 *
	 *
	 */



	//Loop for each time step 
	for (int i=0; i<numStep; i++){

	    //test for eccentricities
	    Particle3d.eccentric(perihelion, aphelion, particles);

	    //Leap position of all particles due to current pairwise force

      	    Particle3d.leapPosition(sizeStep, force, particles);
		
	   
	    //update forces based on new positions
<<<<<<< HEAD

	    Vector3d[] forceNew =  Particle3d.forceCalc(particles);
	 
	    //update velocity based on average of current and new force
	    Particle3d.leapVelocity(sizeStep, force, forceNew, particles);

	    //set force array to new forces
	    for (int j=0; j<force.length; j++){
	    force[j] = forceNew[j]; 

	    }

	    //update timestep
	    t += sizeStep;

	    //print particle positions
	    Particle3d.toVMD(particles, positionOutput,t);

	    //calculate total energy
	     totalE= Particle3d.potentialEnergy(particles) + Particle3d.kineticEnergy(particles);
	    
	    //print energy output
	     energyOutput.printf("%10.5f %10.5f \n", t, totalE);
	     
	     //calculate gradient of Orbit for orbitTracker
	     Particle3d.gradTrack(particles);
	}

	//Loop to print orbit info

	for(int i=0; i<particles.length; i++){
	    
	    orbitOutput.printf("%s : Perihelion= %10.5f, Aphelion=%10.5f, Orbits=%10.5f \n", particles[i].getLabel(), perihelion[i], aphelion[i], count[i]);
	    

	}

	//Close the output streams
	energyOutput.close();
	positionOutput.close();
	orbitOutput.close();
	
    }
       
	
}


