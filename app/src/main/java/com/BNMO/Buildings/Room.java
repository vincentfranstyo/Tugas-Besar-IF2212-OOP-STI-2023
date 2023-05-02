package com.BNMO.Buildings;

import java.util.*;
import com.BNMO.Utilities.*;
import com.BNMO.Object.Object;
import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;

public class Room {
    private ArrayList<Object> objects;
    private String nameRoom;
    private Room front;
    private Room right;
    private Room behind;
    private Room left;
    private int length = 6;
    private int width = 6;
    private int totalObject;
    private Object[][] mapObj;
    private static int price = 1;

    public Room(String nRoom, Room front, Room right, Room behind, Room left) {
        this.nameRoom = nRoom;
        this.front = front;
        this.right = right;
        this.behind = behind;
        this.left = left;
        this.objects = new ArrayList<Object>();
        this.mapObj = new Object[6][6];
        this.totalObject = 0;
    }

    private boolean availableLoc(Point Loc, int width, int length) {
        if ((Loc.getX() < 1 && (Loc.getX() + length > 7)) || (Loc.getY() < 1 && (Loc.getY() + width > 7))) {
            return false;
        } else {
            boolean available = true;
            for (int i = Loc.getX(); i < length + Loc.getX(); i++) {
                for (int j = Loc.getY(); j < width + Loc.getY(); j++) {
                    if (mapObj[i - 1][j - 1] != null) {
                        available = false;
                        break;
                    }
                }
            }
            return available;
        }
    }

    public void addObject(Object o, Point loc, String direction) {
        // Cek location yang diinput, location merupakan titik kiri atas dari object
        if (o instanceof NonFoodObjects) {
            if (direction.toLowerCase().equals("horizontal")) {
                while (!availableLoc(loc, ((NonFoodObjects) o).getWidth(), ((NonFoodObjects) o).getLength())) {
                    System.out.print("Location Not Available, Please Input Location Object Again: ");
                    Scanner sc = new Scanner(System.in);
                    try {
                        loc.setX(sc.nextInt());
                        loc.setY(sc.nextInt());
                    } catch (NoSuchElementException e) {
                        sc.close();
                    }
                }
                ((NonFoodObjects) o).setPosition(loc);
                for (int i = loc.getX(); i < loc.getX() + ((NonFoodObjects) o).getLength(); i++) {
                    for (int j = loc.getY(); j < loc.getY() + ((NonFoodObjects) o).getWidth(); j++) {
                        setMapObj(i - 1, j - 1, o);
                    }
                }
            } else if (direction.toLowerCase().equals("vertikal")) {
                while (!availableLoc(loc, ((NonFoodObjects) o).getLength(), ((NonFoodObjects) o).getWidth())) {
                    System.out.print("Location Not Available, Please Input Location Object Again: ");
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Masukkan Nilai");
                    try {
                        System.out.print("Masukkan Nilai X: ");
                        String x = sc.nextLine();
                        int xInt;
                        while (true) {
                            try {
                                xInt = Integer.parseInt(x);
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Masukan harus dalam bentuk angka!");
                                System.out.print("Masukkan Nilai X: ");
                                x = sc.nextLine();
                            }
                        }
                        System.out.print("Masukkan Nilai Y: ");
                        String y = sc.nextLine();
                        int yInt;
                        while (true) {
                            try {
                                yInt = Integer.parseInt(y);
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Masukan harus dalam bentuk angka!");
                                System.out.print("Masukkan Nilai X: ");
                                y = sc.nextLine();
                            }
                        }
                        loc.setPoint(xInt, yInt);
                    } catch (NoSuchElementException e) {
                        sc.close();
                    }
                }
                ((NonFoodObjects) o).setPosition(loc);
                for (int i = loc.getX(); i < loc.getX() + ((NonFoodObjects) o).getWidth(); i++) {
                    for (int j = loc.getY(); j < loc.getY() + ((NonFoodObjects) o).getLength(); j++) {
                        setMapObj(i - 1, j - 1, o);
                    }
                }
            }
            this.objects.add(totalObject, o);
            this.totalObject++;
        } else {
            System.out.println("Object Tersebut Tidak Dapat Diletakkan Pada Ruangan");
        }
    }
    public void removeObject(Object o, Point Loc, Sim owner){
        if(objects.contains(o)){
            ((NonFoodObjects) o).setPosition(null);
            for(int i=0; i<6; i++){
                for(int j=0; j<6; j++){
                    if(mapObj[i][j] != null){
                        if(mapObj[i][j].getName().equals(o.getName())){
                            mapObj[i][j] = null;
                        }
                    }
                }
            }
            owner.getInventory().addObject(o);
            objects.remove(o);
            this.totalObject--;
        } else {
            System.out.println("No " + o.getName() + " Object in The " + nameRoom + " Room");
        }
    }

    public Iterator<Object> getObjects() {
        return objects.iterator();
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String newNameRoom) {
        this.nameRoom = newNameRoom;
    }

    public Room getFront() {
        return front;
    }

    public void setFront(Room newFront) {
        this.front = newFront;
    }

    public Room getRight() {
        return right;
    }

    public void setRight(Room newRight) {
        this.right = newRight;
    }

    public Room getBehind() {
        return behind;
    }

    public void setBehind(Room newBehind) {
        this.behind = newBehind;
    }

    public Room getLeft() {
        return left;
    }

    public void setLeft(Room newLeft) {
        this.left = newLeft;
    }

    public int getTotalObject() {
        return totalObject;
    }

    public static int getPrice() {
        return price;
    }

    public void setMapObj(int x, int y, Object o) {
        this.mapObj[x][y] = o;
    }

    public Object getObjFromMap(int x, int y) {
        return mapObj[x][y];
    }

    public Object[][] getMapObj() {
        return mapObj;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }
    public void printObjRoom(){
        char[][] por = new char[6][6];
        Iterator<Object> itr = getObjects();
        for(int i=0; i<6; i++){
            for(int j=0; j<6; j++){
                if(mapObj[i][j] != null){
                    por[i][j] = '.';
                }
                else{
                    por[i][j] = ' ';
                }
            }
        }
        while(itr.hasNext()){
            Object o = itr.next();
            por[((NonFoodObjects)o).getPosition().getX()-1][((NonFoodObjects)o).getPosition().getY()-1] = o.getName().charAt(0);
        }
        // System.out.println("|------|");
        for(int y=5; y>-1; y--){
            System.out.print("|");
            for(int x=0; x<6; x++){
                System.out.print(por[x][y]);
                if(x != 5) System.out.print(" ");
            }
            System.out.print("|\n");
        }
        // System.out.println("|------|");
        
    }
    // public static void main(String[] args) {
    //     Room A = new Room("Main Room", null, null, null, null);
    //     Object mattress = new SingleBed("Single Bed", null);
    //     Object toilet = new Toilet("WC", null);
    //     Object tablenchair = new TableAndChair("Table and Chair", null);
    //     A.addObject(mattress, new Point(1,1));
    //     A.addObject(toilet, new Point(6,6));
    //     A.addObject(tablenchair, new Point(4,1));
    // }
}
