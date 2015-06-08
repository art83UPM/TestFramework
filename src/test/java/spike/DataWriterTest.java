package spike;

import writers.DataWriter;

public class DataWriterTest {

    public static void main(String[] args) {
        DataWriter dw = new DataWriter("C:\\Users\\nyuron\\git\\TestFramework\\src\\test\\resources\\ExampleWithSheets.xlsx");
        
        dw.setSheet("Hoja 1");
        dw.write("Método1");
        dw.write("Método1param1");
        dw.write("Método1param2");
        dw.write("Método1param3");
        dw.write("Método1result");
        
        dw.setSheet("Hoja 2");
        dw.write("Método2");
        dw.write("Método2param1");
        dw.write("Método2param2");
        dw.write("Método2param3");
        dw.write("Método2result");
        
        dw.save();
    }

}
