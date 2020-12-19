import org.junit.Test;
import Lab.Functional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;
public class Functional_test {

    @Test
    public void test_OpenAndReadFile () throws IOException {
        Functional func = new Functional("src/test/java/a.csv","src/test/java/b.txt", ';',"%" );
        ArrayList<String> File_lines = new ArrayList();
        File_lines = func.OpenAndReadFile();
        assertEquals("abc;def;ggg", File_lines.get(0));
        assertEquals("\"abc\";d e f;gg\"g\"gg", File_lines.get(1));
        assertEquals("123 4 5 6 7 8 9;;;;1",File_lines.get(2));

    }
    @Test
    public void test_Schitalka() throws IOException{
        Functional func = new Functional("src/test/java/a.csv","src/test/java/b.txt", ';',"%");

        String File_line_1 = "/*fjan;kl";
        String File_line_2 = "\"kfk\"k;fs/*geerg*/f;\"ere\"ww;\"wew3342";
        String File_line_result_1 = func.Schitalka(File_line_1);
        String File_line_result_2 = func.Schitalka(File_line_2);
        assertEquals("6%2\n",File_line_result_1);
        assertEquals("4%12%5%8\n",File_line_result_2);
    }

    @Test
    public void test_Schitalka_by_threads() throws IOException, InterruptedException {
        Functional func = new Functional("src/test/java/a.csv","src/test/java/b.txt", ';',"%");
        func.Schitalka_by_threads(2);
        Functional func_2 = new Functional("src/test/java/b.txt","src/test/java/a.csv", ';',"%");
        ArrayList<String> list = func_2.OpenAndReadFile();
        System.out.println(list);
        assertEquals("3%5%7",list.get(1));
        assertEquals("15%0%0%0%1",list.get(2));
    }

    @Test
    public void test_WriteAndCloseFile() throws IOException{
        Functional func = new Functional("src/test/java/a.csv","src/test/java/b.txt", ';',"%");
        ArrayList<String> File_lines_result = new ArrayList<String>();
        File_lines_result.add("6%2\n");
        File_lines_result.add("4%12%5%8\n");
        func.WriteAndCloseFile(File_lines_result);
        File res_file = new File("src/test/java/b.txt");
        assertNotEquals("0",res_file.length());
    }

}
