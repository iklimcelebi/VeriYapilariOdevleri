public class Node <T extends Comparable>{
    T value;
    Node<T> next;
    public Node(T val){
        this.value=val;
        this.next=null;
    }
    public String toString(){
        return String.valueOf(this.value);
    }
}
