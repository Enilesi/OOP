abstract class Plane { //bec i dont write Plane p1=new Plane(200); obs: i can still write Plane [] p1= new Plane[20];
    private int planeID;
    private static int nrOfPlanes=1;
    private int enginePower;

    public Plane(int enginePower){
        this.enginePower=enginePower;
        this.planeID=nrOfPlanes++;
    }

    @Override
    public boolean equals(Object o){
        return (o instanceof Plane)&&((Plane)o).enginePower==this.enginePower&&((Plane)o).planeID==this.planeID;
    }

    @Override
    public String toString() {
        return "Plane ID: " + getPlaneID() + ", Engine Power: " + enginePower;
    }

    public String getPlaneID(){ return ""+planeID; }
    public int getTotalEnginePower(){ return enginePower;}
    public void takeOff() {System.out.println("take off");}
    public void land()  {System.out.println("land");}
    public void fly()  {System.out.println("fly");}

}

abstract class PassengerPlane extends Plane{
    private int maxPassengers;
    PassengerPlane(int enginePower, int maxPassengers){
        super(enginePower);
        this.maxPassengers=maxPassengers;
    }

    public int getMaxPassengers(){return maxPassengers;}
}

class Concorde extends PassengerPlane{// cannot make abstract bec Plane c1= new Concorde(200,200) etc
    public Concorde(int enginePower, int maxPassengers){
        super(enginePower, maxPassengers);
    }
    public void goSuperSonic() {System.out.println(getPlaneID()+ " : Supersonic mode activated");}
    public void goSubSonic() {System.out.println(getPlaneID()+ " : Supersonic mode deactivated");}
}

class Boeing extends PassengerPlane{
    public Boeing(int enginePower, int maxPassengers){
        super(enginePower, maxPassengers);
    }
}


abstract class FighterPlane extends Plane{
    public FighterPlane(int enginePower){
        super(enginePower);
    }
    public void launchMissile(){System.out.println(getPlaneID()+ " : Launching rocket");}
}

class MiG extends FighterPlane{
    public MiG(int enginePower){
        super(enginePower);
    }
    public void highSpeedGeometry(){System.out.println(getPlaneID()+ " : High speed selected geometry");}
    public void  normalGeometry(){System.out.println(getPlaneID()+ " : Normal selected geometry");}

} 

class TomCat extends FighterPlane{
    TomCat(int enginePower){
        super(enginePower);
    }
    public void refuel(){ System.out.println(getPlaneID()+ " : TomCat - Refuelling");}
}

class Fleet{
    private PassengerPlane []pp= new PassengerPlane[100];
    private FighterPlane [] fp= new FighterPlane[100];
    private int ppNr=1;
    private int fpNr=1;

    public boolean existsPlane (Plane planeToCheck){
        if(planeToCheck instanceof PassengerPlane){
            for(int i=0;i<ppNr;i++){
                if(pp[i]!= null&& pp[i].equals(planeToCheck)) return true;
            }
        }

        if(planeToCheck instanceof FighterPlane){
            for(FighterPlane f:fp){
                if(f!= null&& f.equals(planeToCheck)) return true;
            }
        }

        return false;

    }
    public void addPlane(Plane plane){
        if(!existsPlane(plane)){
            if(plane instanceof PassengerPlane){
                if(ppNr>=100){
                    System.out.println("fleet is full");
                }
                else {
                    pp[ppNr++]=(PassengerPlane)plane;
                }
            }
            if(plane instanceof FighterPlane){
                if(fpNr>=100){
                    System.out.println("fleet is full");
                }
                else {
                    fp[fpNr++]=(FighterPlane)plane;
                }
            }
        }
    }
    public FighterPlane getFighterPlane(int index){
        return fp[index]!=null?fp[index]:null;
    }
    public Plane getStrongestPlane() {
        int maxEngine=0;
        Plane strongestPlane=null;
        for(PassengerPlane p:pp){
            if( p!=null&&p.getTotalEnginePower()>maxEngine){
                maxEngine=p.getTotalEnginePower();
                strongestPlane=p;
            }
        }
        for(FighterPlane f:fp){
            if(f!=null&&f.getTotalEnginePower()>maxEngine){
                maxEngine=f.getTotalEnginePower();
                strongestPlane=f;
            }
        }
        return strongestPlane;
    }

    public int getTotalPassengerCapacity(){
        int totalPassengerCapacity=0;
        Plane strongestPlane=null;
        for(PassengerPlane p:pp){
            {
                if(p!=null)
                totalPassengerCapacity+=p.getMaxPassengers();
            }
        }
        return totalPassengerCapacity;
    }
}


class Main{
    public static void main(String [] args){
        Plane [] planes;

        Plane b1= new Boeing(3,20);
        Plane b2= new Boeing(23,20);

        Plane m1=new MiG(700);
        Plane m2=new MiG(900);

        Plane t1= new TomCat(1200);
        Plane t2=new TomCat(500);

        Plane c1= new Concorde(1200, 250);
        Plane c2= new Concorde(1700, 150);
        planes=new Plane[]{b1,b2,c1,c2,m1,m2,t1,t2};
    
        for (Plane p:planes){
            p.takeOff();
            p.fly();
            p.land();

            if (p instanceof PassengerPlane) {
                System.out.println("Plane:" + p.getPlaneID()+" has total number of passagers: "+((PassengerPlane) p).getMaxPassengers());
            }
            
            if (p instanceof FighterPlane) {
                ((FighterPlane) p).launchMissile();
            }


            if(p instanceof Boeing){}
            if (p instanceof Concorde){
                ((Concorde)p).goSuperSonic();
                ((Concorde)p).goSubSonic();
            }
            if(p instanceof MiG){
                ((MiG)p).highSpeedGeometry();
                ((MiG)p).normalGeometry();
            }
            
            if(p instanceof TomCat){
                ((TomCat)p).refuel();
            }
            
            
           
            System.out.println();
            

        }

        Fleet f=new Fleet();

        f.addPlane(m1);
        f.addPlane(c2);
        f.addPlane(t1);
        f.addPlane(b1);

        System.out.println(f.getFighterPlane(1));
        System.out.println("strongest: "+f.getStrongestPlane());
        System.out.println(f.getTotalPassengerCapacity());



        Plane strongestPlane = f.getStrongestPlane();
        if (strongestPlane != null) {
            System.out.println("Strongest Plane ID: " + strongestPlane.getPlaneID() +
                    ", Engine Power: " + strongestPlane.getTotalEnginePower());
            strongestPlane.takeOff();
            strongestPlane.fly();
            strongestPlane.land();
        }
    }

}