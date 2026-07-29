import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> sourceValues = FileReader.readFile("Source.txt");// source dosyası okundu
        LinkedList<Integer> linkedList = new LinkedList<>();// linkedList oluşturuldu
        for (int value : sourceValues) {
            linkedList.addToEnd(value);// alınan degerler listenin sonuna ekleniyor
        }

        List<Integer> searchValues = FileReader.readFile("Search.txt");// search dosyası okudnu

        int toplambellekErisim = 0;
        for (int value : searchValues) {
            int erisimSayisi = linkedList.searchBellekErisimSayisiyla(value);
            toplambellekErisim += erisimSayisi;
        }

        double ortalamaErisim = (double) toplambellekErisim / searchValues.size();
        System.out.println("Değişiklik Yapmadan:");
        System.out.println("Toplam Bellek Erişimi: " + toplambellekErisim);
        System.out.println("Ortalama Bellek Erişimi: " + ortalamaErisim);

        int toplambellekErisimOneKoymali = 0;
        for (int value : searchValues) {
            toplambellekErisimOneKoymali += linkedList.searchVeOneEkle(value);
        }

        double ortalamaErisimOneKoymali = (double) toplambellekErisimOneKoymali / searchValues.size();
        System.out.println("Başa Taşıyarak:");
        System.out.println("Toplam Bellek Erişimi: " + toplambellekErisimOneKoymali);
        System.out.println("Ortalama Bellek Erişimi: " + ortalamaErisimOneKoymali);

        if(toplambellekErisim > toplambellekErisimOneKoymali){// karşılaştırma blokları
            System.out.println("Değişiklik yapmadan erişilen bellek sayısı daha buyuktur.");
        }
        if (toplambellekErisimOneKoymali > toplambellekErisim){
            System.out.println("One koyulan degerlerin olduğu listtenin bellek erişimi daha fazladır.");
        }
        if (toplambellekErisimOneKoymali == toplambellekErisim){// esitlik
            System.out.println("öne koymali ve değişiklik yapılmadan erişilen ellek sayısı aynıdır.");
        }

    }
}

