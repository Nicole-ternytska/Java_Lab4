package Lab;

import java.awt.desktop.OpenFilesEvent;
import java.io.*;
import java.util.ArrayList;

public class Functional {

    private String file_name;
    private String result_file_name;
    private char symb;
    public int thread = 0;
    private String result_symbol;
    public ArrayList<String> result_strs;

    public Functional(String file_name, String result_file_name, char symb, String result_symbol) {
        this.file_name = file_name;
        this.result_file_name = result_file_name;
        this.symb = symb;
        this.result_symbol = result_symbol;
    }
//
//    public void setFile_name(String file_name) {
//        this.file_name = file_name;
//    }
//
//    public void setResult_file_name(String result_file_name) {
//        this.result_file_name = result_file_name;
//    }
//
//    public void setResult_symbol(String result_symbol) {
//        this.result_symbol = result_symbol;
//    }
//
//    public void setSymb(char symb) {
//        this.symb = symb;
//    }
//
//    public String getResult_file_name() {
//        return result_file_name;
//    }
    public ArrayList<String> OpenAndReadFile(){
        ArrayList<String> file_lines = new ArrayList<>();
        File file = new File(file_name);
        try {
            BufferedReader filereader = new BufferedReader(new FileReader(file));
            String line = filereader.readLine();
            while(line!=null){
                file_lines.add(line);
                line = filereader.readLine();
            }
            filereader.close();
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
        return file_lines;
    }
    public String Schitalka (String file_line) {
    int count = 0;
    int firs_index = 0;
    int last_index = 0;
    int temp = 0;
    boolean quotes = false;
    boolean coment = false;
    String temporary_string = "";
    String result_result = "";

    ArrayList<Integer> result = new ArrayList<>();

    char[] line_char = file_line.toCharArray();
    for (int i = 0; i < line_char.length; i++) {
        if (('\"' == line_char[i]) && (count == 0)) {
            firs_index = i;
            for (int j = i + 1; j < (line_char.length); j++) {
                if ('\"' == line_char[j]) {
                    last_index = j;
                    temp = temp + ((last_index - firs_index) - 1);
                    quotes = true;
                    count = 0;
                    last_index = 0;
                    firs_index = 0;
                    i = j;
                    break;
                }
            }
            if (!quotes) {
                count++;
            }
        } else if (line_char[i] != symb) {
            count++;
        } else {
            result.add(count + temp);
            temp = 0;
            count = 0;
        }
        quotes = false;
    }
    result.add(count + temp);
    for (int i = 0; i < result.size(); i++) {
        result_result = result_result + Integer.toString(result.get(i));
        if (i == (result.size() - 1)) {
            result_result = result_result + "\n";
        } else {
            result_result = result_result + result_symbol;
        }
    }

    return result_result;
}

    public void WriteAndCloseFile(ArrayList<String> result_result) {
        try (FileWriter filewriter = new FileWriter(result_file_name, true)){
            for (int i = 0; i < result_result.size(); i++) {
                filewriter.write(result_result.get(i));
                //filewriter.append('\n');
            }
            filewriter.flush();
        }
        catch(IOException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void Schitalka_by_threads(int max_threads) throws InterruptedException {
        ArrayList<String> file_lines = OpenAndReadFile();
        this.result_strs = file_lines;
        int counter = 0;

        while (counter<file_lines.size()) {
            System.out.println(this.thread);
            if (thread<max_threads) {
                Potok potok = new Potok(file_lines.get(counter), counter, this);
                Thread bee = new Thread(potok);
                bee.start();
                counter++;
                this.thread++;
            }
        }
        Thread.sleep(10);

        WriteAndCloseFile(this.result_strs);
    }
}
