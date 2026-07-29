import java.io.*;
import java.util.*;
public class FileReader {
    public static List<Integer> readFile (String filename) throws IOException{
        List<Integer> numbers = new ArrayList<>();// sayiları koyduğum yeni liste
        BufferedReader br = new BufferedReader(new java.io.FileReader(filename));//dosyayı okuma satırı
        String line = br.readLine();//ilk satır

        if(line != null){//satırın boş olmama durumu
            String[] values = line.split(",");
            for(String value : values){//sayiları gezmesi için
                numbers.add(Integer.parseInt(value.trim()));//sayiya cevirir
            }
        }
        br.close();//satır bossa
        return numbers;// sayiları koydugumuz listeyi dondurur.
    }
}
