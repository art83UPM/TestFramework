package spikeTest;
import spike.DataAccess;


public class EjemploTestData {
    private DataAccess data;
    private String excel;
    
    public EjemploTestData() {
        excel = "EjemploTestData.xls";
    }

    public int getM1IntX(){
        return data.getInt();
    }

    public void reset() {
        data.reset();        
    }

    public boolean next() {
        return data.next();
    }

    public boolean hasNext() {
        return data.hasNext();
    }

    public int getM1Result() {
        return 0;
    }
}
