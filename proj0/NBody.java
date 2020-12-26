
public class NBody{
	public static String background = "images/starfield.jpg";

	public static double readRadius(String Filename){
		In in = new In(Filename);
		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
		return secondItemInFile;
	}

	public static Body[] readBodies(String Filename){
	 
		In in = new In(Filename);
		int firstItemInFile = in.readInt();
		Body[] bodies = new Body[firstItemInFile];
		double secondItemInFile = in.readDouble();
		int i = 0;
		while(!in.isEmpty()) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String name = in.readString();
			bodies[i++] = new Body(xxPos,yyPos,xxVel,yyVel,mass,name);
			if(i == firstItemInFile) break;
		}
		return bodies;
	}
	public static void draw(){
		StdDraw.enableDoubleBuffering();

	}
	public static void main(String args[]){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Body[] bodies = readBodies(filename);
		double radius = readRadius(filename);

		int waitTimeMilliseconds = 10;
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);

		StdDraw.picture(0, 0, background);
		for(int i = 0; i < bodies.length; i++){
			bodies[i].draw();
		}
		StdDraw.show();
		StdDraw.pause(waitTimeMilliseconds);

		double time = 0;
		while(time < T){
			double[] xForces = new double[bodies.length];
			double[] yForces = new double[bodies.length];
			for(int i = 0; i < bodies.length; i++){
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}
			for(int i = 0; i < bodies.length; i++){
				bodies[i].update(dt,xForces[i],yForces[i]);
			}
			StdDraw.picture(0, 0, background);
			for(int i = 0; i < bodies.length; i++){
					bodies[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(waitTimeMilliseconds);
			time += dt;
		}
		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
		}
				
	}
}