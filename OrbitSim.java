/**
 *
 * N-body Simulation of the Solar System:
 *
 * This simulation utilizes the velocity-Verlet time-integration method in
 * conjuncture with Newtonian Gravitation to simulate the orbits of 
 * bodies in the solar system.
 *
 *
 * The command line takes four arguments. The first argument is the file name of the file containing the 
 * intial states of every particle. There should be an integer at the start of the file indicating the number
 * of particles in the system. From here, each particle's intial state should be described as follows:
 *
 * A string designating the particle label.
 * A double indicating the mass of the particle.
 * Three doubles indicating the initial position of the particle.
 * Three doubles indicating the initial velocity of the particle.
 * 
 * Repeat this sequence for all desired particles in the system.
 *
 *
 * The second command line argument is the file name correlating to the parameters of the system. These
 * should be in the following sequence:
 *
 * A double indicating the number of time-steps to evolve over.
 * A double indicating the size of each time-step.
 * 
 *
 * The third command line argument is the name of the file to write the VMD trajectory info to.
 *
 * The fourth command line argument is the name of the file to write the total energy values to.
 *
 *
 *@author C. John
 *@author M. O'Shea
 *@version "02/2016"
 */


import java.io.*;
import java.util.Scanner;


public class OrbitSim {


     /**
     * Main method to run OrbitSim class.
     *
     *@param argv[0] initial state of the particles, with first integer for number of particles in system.
     *@param argv[1] values of the parameters.
     *@param argv[2] name of the output trajectory file for the VMD writing.
     *@param argv[3] name of the output file to monitor energy fluctuations. 
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
	Particle3d[] particles = particleArray(particleInfo);

	//create reader for parameter file
	BufferedReader parameterRead = new BufferedReader(new FileReader(argv[1]));

	//create scanner from reader for initial states of particles
	Scanner parameterScan= new Scanner(parameterRead);

	//save parameters from scanner to global variables
	double numStep= parameterScan.nextDouble();
	double sizeStep= parameterScan.nextDouble();
	
	//arrayPositions to positionOutput
	//create writer for VMD output
        PrintWriter positionOutput = new PrintWriter(new FileWriter(argv[2]));

	//create writer for energy output
	PrintWriter energyOutput = new PrintWriter(new FileWriter(argv[3]));







	/* Start of loop for time-integration via Velocity-Verlet algorithm
	 *
	 *
	 *
	 *
	 *
	 */
   

        for (int i=0; i < myParticle3d.length; i++) {

        myParticle3d[i] = Particle3d.readParticle(orbitSimScan);



	}
	toVMD(myParticle3d, arrayPositions); //call this at the end of each time step 
    }
}
