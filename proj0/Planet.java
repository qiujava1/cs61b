public class Planet{
	final static double G = 6.67e-11;
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xp,double yp,double xV, double yV, double m, String img){
		xxPos = xp;
		yyPos = yp;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	
	
	public Planet(Planet p){
		Planet Planet = p;
	}

	public double calcDistance(Planet p){
		double dx = Math.abs(xxPos - p.xxPos);
		double dy = Math.abs(yyPos - p.yyPos);
		return Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));
	}

	public double calcForceExertedBy(Planet p){
		return G * mass * p.mass / Math.pow(calcDistance(p),2);
	}

	public double calcForceExertedByX(Planet p){

		return calcForceExertedBy(p) * (xxPos - p.xxPos) * (-1) / calcDistance(p);
		
	}

	public double calcForceExertedByY(Planet p){

		return calcForceExertedBy(p) * (yyPos - p.yyPos) * (-1) / calcDistance(p);
		
	}

	public double calcNetForceExertedByX(Planet[] Planets){
		double res = 0;
		for(Planet Planet : Planets){
			if(Planet.equals(this)){}
			else res +=calcForceExertedByX(Planet);
		}
		return res;
	}

	public double calcNetForceExertedByY(Planet[] Planets){
		double res = 0;
		for(Planet Planet : Planets){
			if(Planet.equals(this)){}
			else res +=calcForceExertedByY(Planet);
		}
		return res;
	}

	public void update(double dt,double fx, double fy){
		double ax = fx / mass;
		double ay = fy / mass;
		xxVel += dt * ax;
		yyVel += dt * ay;
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}

	public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
	}
}