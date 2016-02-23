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



public static void toVMD(Particle3d[] myParticle3d, PrintWriter outfile) {

    
    outfile.printf("%d\n" ,myParticle3d.length);
    outfile.printf("Point = %d\n" , numstep);
       
    for (int i=0; i < myParticle3d.length; i++) {
       
    outfile.printf("%s\n", myParticle3d[i].toString());

   }
    outfile.flush();
}



      /**
     * Main method to run OrbitSim class.
     *
     *@param argv[0] name of the output file for position.
     *@param argv[1] name of the output file for the total energy
     *@param argv[2] number of timesteps for time integration
     *@param argv[3] size of each timestep
     *
x     */

    // We are using file IO so we are throwing an exception

    //the input file for 

    public static void main (String[] argv) throws IOException{


        PrintWriter arrayPositions = new PrintWriter(new FileWriter("outPositions"));

	int length = 2; //or whaterver

	Particle3d[] myParticle3d = new Particle3d[length];

        BufferedReader orbitSimRead = new BufferedReader(new FileReader("orbitSim.input"));

        Scanner orbitSimScan= new Scanner(orbitSimRead);

        for (int i=0; i < myParticle3d.length; i++) {

        myParticle3d[i] = Particle3d.readParticle(orbitSimScan);

	}
	toVMD(myParticle3d, arrayPositions); //call this at the end of each time step 
    }
}
