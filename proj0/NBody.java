
public class NBody{
	private static String background = "images/starfield.jpg";

	public static double readRadius(String Filename){
		In in = new In(Filename);
		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
		return secondItemInFile;
	}

	public static Planet[] readPlanets(String Filename){
	 
		In in = new In(Filename);
		int firstItemInFile = in.readInt();
		Planet[] planets = new Planet[firstItemInFile];
		double secondItemInFile = in.readDouble();
		int i = 0;
		while(!in.isEmpty()) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String name = in.readString();
			planets[i++] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,name);
			if(i == firstItemInFile) break;
		}
		return planets;
	}

	public static void main(String args[]){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] planets = readPlanets(filename);
		double radius = readRadius(filename);

		int waitTimeMilliseconds = 10;
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);

		StdDraw.picture(0, 0, background);
		for(int i = 0; i < planets.length; i++){
			planets[i].draw();
		}
		StdDraw.show();
		StdDraw.pause(waitTimeMilliseconds);

		double time = 0;
		while(time < T){
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for(int i = 0; i < planets.length; i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for(int i = 0; i < planets.length; i++){
				planets[i].update(dt,xForces[i],yForces[i]);
			}
			StdDraw.picture(0, 0, background);
			for(int i = 0; i < planets.length; i++){
					planets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(waitTimeMilliseconds);
			time += dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}

	}
}