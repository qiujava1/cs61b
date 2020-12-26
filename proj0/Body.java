public class Body{
	final static double G = 6.67e-11;
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Body(double xp,double yp,double xV, double yV, double m, String img){
		xxPos = xp;
		yyPos = yp;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	
	
	public Body(Body p){
		Body body = p;
	}

	public double calcDistance(Body p){
		double dx = Math.abs(xxPos - p.xxPos);
		double dy = Math.abs(yyPos - p.yyPos);
		return Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));
	}

	public double calcForceExertedBy(Body p){
		return G * mass * p.mass / Math.pow(calcDistance(p),2);
	}

	public double calcForceExertedByX(Body p){

		return calcForceExertedBy(p) * (xxPos - p.xxPos) * (-1) / calcDistance(p);
		
	}

	public double calcForceExertedByY(Body p){

		return calcForceExertedBy(p) * (yyPos - p.yyPos) * (-1) / calcDistance(p);
		
	}

	public double calcNetForceExertedByX(Body[] Bodys){
		double res = 0;
		for(Body body : Bodys){
			if(body.equals(this)){}
			else res +=calcForceExertedByX(body);
		}
		return res;
	}

	public double calcNetForceExertedByY(Body[] Bodys){
		double res = 0;
		for(Body body : Bodys){
			if(body.equals(this)){}
			else res +=calcForceExertedByY(body);
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
		StdDraw.picture(xxPos, yyPos, "images/"+ imgFileName);
	}
}