/**
 *
 * N-body Simulation of the Solar System:
 *
 * This simulation utilizes the velocity-Verlet time-integration method in
 * conjuncture with Newtonian Gravitation to simulate the orbits of 
 * bodies in the solar system.
 *
 *
 *@author C. John
 *@author M. O'Shea
 *@version "02/2016"
 */


import java.io.*;
import java.util.Scanner;


public class OrbitSim {



public static void toVMD(Particle3d[] particles, PrintWriter outfile) {

    
    outfile.printf("%d\n" ,particles.length);
    outfile.printf("Point = %d\n" , numstep);
       
    for (int i=0; i < particles.length; i++) {
       
    outfile.printf("%s\n", particles[i].toString());

   }
    outfile.flush();
}



     /**
     * Main method to run OrbitSim class.
     *
     *@param argv[0] initial state of the particles.
     *@param argv[1] values of the parameters
     *@param argv[2] name of of the output trajectory file for the VMD
     *
     */

    // We are using file IO so we are throwing an exception

    //the input file for 

    public static void main (String[] argv) throws IOException{


        PrintWriter arrayPositions = new PrintWriter(new FileWriter("outPositions"));

	int length = 2; //or whaterver

	Particle3d[] particles = new Particle3d[length];

        BufferedReader orbitSimRead = new BufferedReader(new FileReader("orbitSim.input"));

        Scanner orbitSimScan= new Scanner(orbitSimRead);


		//start of the main loop// 

	//Read in particle of intrest
		particles[i] = Particle3d.readParticle(orbitSimScan);

		Vector3d[] force = Particle3d.forceCalc(particles);

	//Loop for each time step 
	for (int i=0; i<numstep; i++){


	    //Leap position of all particles due to current peerwise forces
	    for (int i=0; i <particles.length; i++) {

        

	    //Leap position of particle of intrest
      	    Particle3d.leapPosition(dt, force, particles);
		
	    }

	   
	    //calculate the new peerwise forces

	    Vector3d[] force_new = new Vector3d[paticles.length]; 

	    force_new = Particle3d.forceCalc(particles);


	    for (int i=0; i <myParticle3d.length; i++){

	    Particle3d.leapVelocity(dt, force_new, particles);

	    force = new_force; //Could cause issues (becasue we are equating memory adresses not values) 

	   

      	}

	    t = t + dt

	toVMD(myParticle3d, arrayPositions); //call this at the end of each time step 
    }

    }
}
