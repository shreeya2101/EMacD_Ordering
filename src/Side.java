import java.io.IOException;
import java.io.*;

public class Side{

    private String whichSide;
    private String sideVegNonveg;
    private String dipReqd;
    private String sSize;
    private int sQty;
    public static double sidePrice;
    public static double totalSidePrice;

    public Side(String sideVegNonveg,String whichSide,String dipReqd,String sSize,int sQty) {
        this.whichSide = whichSide;
        this.sideVegNonveg=sideVegNonveg;
        this.dipReqd=dipReqd;
        this.sSize=sSize;
    }
    public double calcSidePrice(String sideVegNonveg,String whichSide,String dipReqd,String sSize,int sQty) {
        sidePrice=70;
        if (sideVegNonveg=="Non Veg") {
            sidePrice += 20;
            if(sSize=="Large"){
                sidePrice+=20;
            }
                if(dipReqd=="Yes Dip")
                {
                    sidePrice+=10;
                }
            else{
                if(dipReqd=="Yes Dip")
                    sidePrice+=10;
                }

        }else{
            if(sSize=="Large") {
                sidePrice += 20;

                if (dipReqd == "Yes Dip") {
                    sidePrice += 10;
                }
            }
            else {
                if (dipReqd == "Yes Dip") {
                    sidePrice+= 10;
                }
            }
        }
        sidePrice=sidePrice*sQty;
        return sidePrice;
    }

    public void createSideOrder(int oID, String sideVegNonveg,String whichSide,String dipReqd,String sSize,int sQty,double sidePrice){
        String s=oID+"  "+sideVegNonveg+"  "+whichSide+"  "+sSize+"  "+dipReqd+"  "+sQty+"  "+sidePrice;
        try {
            OutputStream myWriter = new FileOutputStream("./McdOrder.txt",true);
            myWriter.write(s.getBytes());
            myWriter.write("\r\n".getBytes());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}