package Lab;

public class Potok implements Runnable{
    public String str;
    public int index;
    public Functional temp;

    public Potok(String str, int index, Functional temp) {
        this.str = str;
        this.index = index;
        this.temp = temp;
    }

    public void run()  {
        String line = temp.Schitalka(str);
        temp.result_strs.set(index, line);
        temp.thread--;
    }
}
