package uidesign;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

///Calcualtions are done in cm & grams 
public class ReadFile {

    private static final String FILENAME = "src/UIDesign/data.txt";
    private static final String FILENAME2 = "src/UIDesign/info.txt";
    public static Double weight[] = new Double[5];
    public static Double distance[] = new Double[5];
    public static Double temprature[] = new Double[5];
    public static String juices[] = new String[5];
    public static Double xFac[] = new Double[5];
    static int count = 0;
    Double avWeight = 0.00;
    Double avHeight = 0.00;
    Double avTemp = 0.00;
    Double mass = 0.00;
    Double density = 0.00;
    Double volume = 0.00;
    Double xFactor = 0.00;
    Double netMass = 0.00 ;
    Double calories =0.00;
    Double carbohydrates =0.00;
    Double protiens =0.00;
    Double vitaminA =0.00;
    Double vitaminC =0.00;
    Double calcium =0.00;
    Double iron =0.00;
    Double magnesium =0.00;
    Double sodium =0.00;
    Double potassium =0.00;
    Double fat =0.00;
    Double arrCarrot[] = {1.72,0.096,0.009,0.05,0.059,0.33,0.003,0.12,0.69,3.20,2.00};
    Double arrMango[] = {1.9,0.1,0.007,0.001,0.5,0.11,0.002,0.11,0.01,2.00,2.00};
    final double RADIUS = 3.09;
    final double WATER_DENSITY = 0.952;
    final double BOTTLE_HEIGHT = 13.00;

    /*Change accordingly*/
    public void readFile() {
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(FILENAME));
            int i = 0;
            while ((sCurrentLine = br.readLine()) != null) {

                String line[] = sCurrentLine.split(" ");

                weight[i] = Double.parseDouble(line[1]);
                distance[i] = Double.parseDouble(line[3]); //reduce the bottle height
                temprature[i] = Double.parseDouble(line[5]);
                i++;
            }
            System.out.println(Arrays.toString(weight));
            System.out.println(Arrays.toString(distance));
            System.out.println(Arrays.toString(temprature));

        } catch (IOException e) {

            System.out.println("Fail");

        } finally {

            try {

                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }

            } catch (IOException ex) {

                System.out.println("Fail");

            }

        }

    }
    
     /*Change accordingly*/
    public void readFile2() {
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(FILENAME2);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(FILENAME2));
            int i = 0;
            while ((sCurrentLine = br.readLine()) != null) {

                String line[] = sCurrentLine.split(" ");

                juices[i] = line[0];
                xFac[i] = Double.parseDouble(line[1]);
            
                i++;
            }
            System.out.println(Arrays.toString(juices));
            System.out.println(Arrays.toString(xFac));


        } catch (IOException e) {

            System.out.println("Fail");

        } finally {

            try {

                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }

            } catch (IOException ex) {

                System.out.println("Fail");

            }

        }

    }
    
    public void defineXFactor (String juice){
        count++;
        for(int i = 0; i < juices.length ;){
            if (juices[i].equalsIgnoreCase(juice)){
            xFactor = xFac[i];
                System.out.println("xFactor: " + xFactor);
                i = juices.length;
            }else{
                i++;
            }
        }     
    }

    public void calAverage() {

        for (int i = 0; i < weight.length; i++) {

            avWeight = avWeight + weight[i];
            avHeight = avHeight + (BOTTLE_HEIGHT- distance[i]);
            avTemp = avTemp + temprature[i];

        }

        avHeight = avHeight / weight.length;
        avWeight = avWeight / weight.length;
        avTemp = avTemp / weight.length;

        System.out.println("av Weight: " +avWeight);
        System.out.println("av height: " +avHeight);
        System.out.println("av Temp: " +avTemp);

    }

    public void calDensityVolume() {

        mass = avWeight;
        System.out.println("Mass: " + mass);

        volume = 3.14 * (RADIUS * RADIUS) * avHeight; //change the radius accordingly
        System.out.println("Volume: " + volume);

        density = mass / volume;
        
        System.out.println("Density: " + density);

    }

    JLabel lblWeight, lblHeight, lblTemperature, lblMass, lblVolume, lblDensity;
    JLabel lblWeightVal, lblHeightVal, lblTemperatureVal, lblMassVal, lblVolumeVal, lblDensityVal;
    
    public void calcNetMass(){
        netMass = xFactor * density ;
        System.out.println("Net Mass: " +netMass);
    }
    
    DecimalFormat df =new DecimalFormat("0.0000");
    
    public void calNutrients(Double arrNutrients[]){
      calories = arrNutrients[0] * netMass ;
      carbohydrates = arrNutrients[1] * netMass;
      protiens = arrNutrients[2] * netMass;
      vitaminA = arrNutrients[3] * netMass;
      vitaminC = arrNutrients[4] * netMass;
      calcium = arrNutrients[5] * netMass;
      iron = arrNutrients[6] * netMass;
      magnesium = arrNutrients[7] * netMass;
      sodium = arrNutrients[8] * netMass;
      potassium = arrNutrients[9] * netMass;
      fat = arrNutrients[10] * netMass;
      
    }
    
    public void window() {
        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(mainFrame.DISPOSE_ON_CLOSE);//close only one window
        mainFrame.setSize(300, 300);
        mainFrame.setResizable(true);
        mainFrame.setVisible(true);
        mainFrame.setLayout(new GridLayout(1, 2));
        mainFrame.setTitle("DATA SHEET");

        JPanel amountPanel = new JPanel(new GridLayout(7, 1));
        JPanel resultPanel = new JPanel(new GridLayout(7, 1));

        ReadFile rf = new ReadFile();

        lblHeight = new JLabel(" Height : ");
        amountPanel.add(lblHeight);
        lblHeightVal = new JLabel(df.format(avHeight)+ " cm");
        resultPanel.add(lblHeightVal);

        lblTemperature = new JLabel(" Temperature : ");
        amountPanel.add(lblTemperature);
        lblTemperatureVal = new JLabel(df.format(avTemp) + " Celcius");
        resultPanel.add(lblTemperatureVal);

        lblMass = new JLabel(" Mass : ");
        amountPanel.add(lblMass);
       lblMassVal = new JLabel(df.format(mass) + " g");
        resultPanel.add(lblMassVal);

        lblVolume = new JLabel(" Volume : ");
        amountPanel.add(lblVolume);
        lblVolumeVal = new JLabel(df.format(volume)+ " cm^3");
        resultPanel.add(lblVolumeVal);
//
       lblDensity = new JLabel(" Density : ");
        amountPanel.add(lblDensity);
                lblDensityVal = new JLabel(df.format(density) + " g/cm^3");
        resultPanel.add(lblDensityVal);
//
//        JButton btnSaveAs = new JButton(" Save ");
//        resultPanel.add(btnSaveAs);
//       
        // amountPanel.setBackground(Color.red);
        mainFrame.add(amountPanel);
        mainFrame.add(resultPanel);
        mainFrame.setVisible(true);
    }
}
