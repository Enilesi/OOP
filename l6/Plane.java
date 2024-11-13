//preferred to  display the plane: plane id
//more planes
//sa fac plane id protected-optional
//concorde poate mosteni boeing si pot sterge passagers plane ca sa simplificam codul
class Plane {
    private static int planesCounter = 1;
    private int planeID;
    private int enginePower;

    public Plane(int enginePower) {
        this.planeID = planesCounter++;
        this.enginePower = enginePower;
    }

    public String getPlaneID() {
        return "" + planeID;//or String.valueOf(plane.ID)
    }

    public int getTotalEnginePower() {
        return enginePower;
    }

    public void takeOff() {
        System.out.println("Plane:"+planeID+" takeOff");
    }

    public void land() {
        System.out.println("Plane:"+planeID+" land");
    }

    public void fly() {
        System.out.println("Plane:"+planeID+" fly");
    }
}

class PassengerPlane extends Plane {
    private int maxPassengers;

    public PassengerPlane(int enginePower, int maxPassengers) {
        super(enginePower);
        this.maxPassengers = maxPassengers;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }
}

class Boeing extends PassengerPlane {
    public Boeing(int enginePower, int maxPassengers) {
        super(enginePower, maxPassengers);
    }
}

class Concorde extends PassengerPlane {
    public Concorde(int enginePower, int maxPassengers) {
        super(enginePower, maxPassengers);
    }

    public void goSuperSonic() {
        System.out.println("Plane:"+getPlaneID()+" Supersonic mode activated");
    }

    public void goSubSonic() {
        System.out.println("Plane:"+getPlaneID()+"  Supersonic mode deactivated");
    }
}

class FighterPlane extends Plane {
    public FighterPlane(int enginePower) {
        super(enginePower);
    }

    public void launchMissile() {
        System.out.println("Plane:"+getPlaneID()+" Launching rocket");
    }
}

class MiG extends FighterPlane {
    public MiG(int enginePower) {
        super(enginePower);
    }

    public void highSpeedGeometry() {
        System.out.println("Plane:"+getPlaneID()+" High speed selected geometry");
    }

    public void normalGeometry() {
        System.out.println("Plane:"+getPlaneID()+" Normal selected geometry");
    }
}

class TomCat extends FighterPlane {
    public TomCat(int enginePower) {
        super(enginePower);
    }

    public void refuel() {
        System.out.println("Plane:"+getPlaneID()+" TomCat - Refuelling");
    }
}

class PlaneClient {
    public static void main(String[] args) {
        Boeing b1 = new Boeing(1700, 260);
        Boeing b2 = new Boeing(2999, 17);
        Concorde c1 = new Concorde(5473, 127);
        Concorde c2 = new Concorde(4600, 50);
        MiG m1 = new MiG(4400);
        MiG m2 = new MiG(1270);
        TomCat t1 = new TomCat(6500);
        TomCat t2 = new TomCat(3986);

        Plane[] planes = {b1, c1, m1, t1};

        for (Plane plane : planes) {
            plane.takeOff();
            plane.fly();
            plane.land();
            System.out.println("Plane:" + plane.getPlaneID()+" has total engine power: "+plane.getTotalEnginePower());

            if (plane instanceof PassengerPlane) {
                System.out.println("Plane:" + plane.getPlaneID()+" has total number of passagers: "+((PassengerPlane) plane).getMaxPassengers());
            }
            if (plane instanceof Concorde) {
                ((Concorde) plane).goSuperSonic();
                ((Concorde) plane).goSubSonic();
            }
            if (plane instanceof FighterPlane) {
                ((FighterPlane) plane).launchMissile();
            }
            if (plane instanceof MiG) {
                ((MiG) plane).highSpeedGeometry();
                ((MiG) plane).normalGeometry();
            }
            if (plane instanceof TomCat) {
                ((TomCat) plane).refuel();
            }
            System.out.println();
        }
    }
}
