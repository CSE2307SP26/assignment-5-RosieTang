public class Ball {
	double xLocation;
	double yLocation;
	double xSpeed;
	double ySpeed;
	double radius;;
	
	public Ball(double maxSpeed, double minSpeed, double radius) {
		this.xLocation = Math.random();
		this.yLocation = Math.random();
		this.xSpeed = Math.random() * (maxSpeed - minSpeed) + minSpeed;
		this.ySpeed = Math.random() * (maxSpeed - minSpeed) + minSpeed;
		this.radius = radius;
	}

	public void move() {
		xLocation = xLocation + xSpeed;
		yLocation = yLocation + ySpeed;
	}

	public void bounceWalls() {
		if(xLocation + radius > 1 || xLocation - radius < 0) { 
			xSpeed = -xSpeed;
		}
		if(yLocation + radius > 1 || yLocation - radius < 0) { 
			ySpeed = -ySpeed;
		}
	}

    public double getXLocation() {
        return xLocation;
    }
    
    public double getYLocation() {
        return yLocation;
    }

    public double getRadius() {
        return radius;
    }
    
    public void checkBallCollision(Ball other) {
        double distance = Math.sqrt(Math.pow(xLocation - other.getXLocation(), 2) + Math.pow(yLocation - other.getYLocation(), 2));
        if (distance < this.radius + other.getRadius()) {
            this.xSpeed = -this.xSpeed;
            this.ySpeed = -this.ySpeed;
        }
    }

    public boolean checkPlayerCollision(double playerXLocation, double playerYLocation, double playerRadius) {
        double distanceToPlayer = Math.sqrt(Math.pow(xLocation - playerXLocation, 2) + Math.pow(yLocation - playerYLocation, 2));
        return distanceToPlayer < this.radius + playerRadius; 
    }
}
