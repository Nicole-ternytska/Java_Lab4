package Lab;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
public class main {
    public static void main(String[] args) throws InterruptedException {

        String file_name = "C:\\Users\\HP\\IdeaProjects\\Lab2\\src\\main\\java\\Lab\\Scene_10f", result_file_name = "C:\\Users\\HP\\IdeaProjects\\Lab2\\src\\main\\java\\Lab\\loggg.txt",  symbol = ";",  result_symbol = "+";
        int max_threads = 0;

        Scanner sep = new Scanner(System.in);
        System.out.println("Input through ENTER:initial file path, a symbol to separate, result file path, a symbol to result connection, max_threads");
        file_name = sep.nextLine();
        symbol = sep.nextLine();
        result_file_name = sep.nextLine();
        result_symbol = sep.nextLine();
        max_threads = Integer.valueOf(sep.nextLine());


        File[] files = new File(file_name).listFiles();
        for (int i = 0; i < files.length; i++) {
            Functional function = new Functional(files[i].getAbsolutePath(), result_file_name, symbol.charAt(0), result_symbol);
            function.Schitalka_by_threads(max_threads);
        }
    }
}
