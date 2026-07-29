import java.util.Iterator;

public class LinkedList <T extends Comparable> {
    private Node<T> head;
    public LinkedList(){
        this.head=null;
    }

    public void addToFront(T val){
        Node<T> newNode=new Node<>(val);
        newNode.next=head;
        head=newNode;
    }
    public void addToEnd(T val){
        Node<T> newNode=new Node<>(val);
        if(head==null)
            head=newNode;
        else {
            Node<T> iterator = head;
            while (iterator.next != null) {
                iterator = iterator.next;
            }
            iterator.next = newNode;
        }
    }

    public int searchBellekErisimSayisiyla(T val){
        Node<T> iterator = head;
        int erisimSayisi = 0;
        while (iterator != null){
            erisimSayisi++;
            if (iterator.value.compareTo(val) == 0){
                return erisimSayisi;
            }
            iterator = iterator.next;
        }
        return erisimSayisi;//  erisim sayısını veriyor.
    }

    public int searchVeOneEkle(T val){
        if(head == null || head.value.compareTo(val) == 0){
            return 1; // liste boşsa veya sayi ztaen bastaysa
        }
        Node<T> prev = null;
        Node<T> iterator = head;
        int erisimSayisi = 0;

        while (iterator != null){
            erisimSayisi++;
            if(iterator.value.compareTo(val) == 0){
                if(prev != null){
                    prev.next = iterator.next;// aradıgımız sayıyı cıkardık
                    iterator.next = head;//sayıyı head yaptık
                    head = iterator;
                }
                return erisimSayisi;// null ise erisimsayisi basa gelir direkt
            }
            prev = iterator;// sayi listede yoksa iteratore koyup
            iterator = iterator.next;//iteratoru head yaparız
        }
        return erisimSayisi;
    }

    public void display(){
        if(head != null) {
            Node<T> iterator = head;
            while (iterator.next != null) {
                System.out.print(iterator + "-->");
                iterator = iterator.next;
            }
            System.out.println(iterator);
        }
    }
    public void sortedInsert(T val){
        Node<T> newNode=new Node<>(val);
        if (head==null){
            head=newNode;
        }else if(head.value.compareTo(val)==1){
            newNode.next=head;
            head=newNode;
        }else{
            Node<T> prev, iterator;
            prev=iterator=head;
            while(iterator!=null && iterator.value.compareTo(val)!=1 ){
                prev=iterator;
                iterator=iterator.next;
            }
            prev.next=newNode;
            newNode.next=iterator;

        }
    }
    public void delete(T val){
        if(!search(val)){
            return;
        }else if(head.value.compareTo(val)==0){
            head=head.next;
        }else{
            Node<T> prev, iterator;
            prev=iterator=head;
            while(iterator.value.compareTo(val)!=0){
                prev=iterator;
                iterator=iterator.next;
            }
            prev.next=iterator.next;
        }
    }
    public boolean isEmpty(){
        return this.head==null;
    }
    public T findMin() throws Exception {
        if(head==null)
            throw  new Exception("Liste boş");
        else{
            T min=head.value;
            Node<T> iterator=head;
            while(iterator!=null){
                if(iterator.value.compareTo(min)==-1)
                    min=iterator.value;
                iterator=iterator.next;
            }
            return min;
        }
    }
}
