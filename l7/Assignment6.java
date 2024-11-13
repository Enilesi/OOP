
public class Assignment6 {
    public static void main(String[] args) {
        Plane concorde1 = new Concorde(5000, 100);
        Plane boeing1 = new Boeing(4000, 200);
        Plane mig1 = new MiG(6000);
        Plane tomcat1 = new TomCat(7000);

        Fleet fleet = new Fleet();

        fleet.addPlane(concorde1);
        fleet.addPlane(boeing1);
        fleet.addPlane(mig1);
        fleet.addPlane(tomcat1);

        Plane concorde2 = new Concorde(5500, 110);
        Plane boeing2 = new Boeing(4200, 180);
        Plane mig2 = new MiG(6200);
        Plane tomcat2 = new TomCat(7100);

        fleet.addPlane(concorde2);
        fleet.addPlane(boeing2);
        fleet.addPlane(mig2);
        fleet.addPlane(tomcat2);

        Plane strongestPlane = fleet.getStrongestPlane();
        if (strongestPlane != null) {
            System.out.println("Strongest Plane ID: " + strongestPlane.getPlaneID() +
                    ", Engine Power: " + strongestPlane.getTotalEnginePower());
            strongestPlane.takeOff();
            strongestPlane.fly();
            strongestPlane.land();
        }

        int totalCapacity = fleet.getTotalPassengerCapacity();
        System.out.println("Total Passenger Capacity of the Fleet: " + totalCapacity);

        for (int i = 0; i < 6; i++) {
            FighterPlane fighterPlane = fleet.getFighterPlane(i);
            if (fighterPlane != null) {
                fighterPlane.takeOff();
                fighterPlane.fly();
                fighterPlane.launchMissile();
                fighterPlane.land();
            } else {
                System.out.println("No fighter plane at index " + i);
            }
        }
    }
}

abstract class Plane {
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

abstract class PassengerPlane extends Plane {
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

abstract class FighterPlane extends Plane {
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


class Fleet {
    private PassengerPlane[] passengerPlanes = new PassengerPlane[100];
    private FighterPlane[] fighterPlanes = new FighterPlane[100];
    private int passengerPlaneCount = 0;
    private int fighterPlaneCount = 0;

    public void addPlane(Plane plane) {
        if (plane instanceof PassengerPlane) {
            if (passengerPlaneCount < 100) {
                passengerPlanes[passengerPlaneCount++] = (PassengerPlane) plane;
            } else {
                System.out.println("Fleet is full");
            }
        } else if (plane instanceof FighterPlane) {
            if (fighterPlaneCount < 100) {
                fighterPlanes[fighterPlaneCount++] = (FighterPlane) plane;
            } else {
                System.out.println("Fleet is full");
            }
        }
    }

    public FighterPlane getFighterPlane(int index) {
        if (index >= 0 && index < fighterPlaneCount) {
            return fighterPlanes[index];
        }
        return null;
    }

    public Plane getStrongestPlane() {
        Plane strongest = null;
        int highestPower = 0;

        for (int i = 0; i < passengerPlaneCount; i++) {
            if (passengerPlanes[i].getTotalEnginePower() > highestPower) {
                highestPower = passengerPlanes[i].getTotalEnginePower();
                strongest = passengerPlanes[i];
            }
        }

        for (int i = 0; i < fighterPlaneCount; i++) {
            if (fighterPlanes[i].getTotalEnginePower() > highestPower) {
                highestPower = fighterPlanes[i].getTotalEnginePower();
                strongest = fighterPlanes[i];
            }
        }
        return strongest;
    }

    public int getTotalPassengerCapacity() {
        int totalCapacity = 0;
        for (int i = 0; i < passengerPlaneCount; i++) {
            totalCapacity += passengerPlanes[i].getMaxPassengers();
        }
        return totalCapacity;
    }
}
