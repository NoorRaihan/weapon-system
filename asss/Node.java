public class Node {

    Object data;
    Node next;

    public Node() {
        data = next = null;
    }

    public Node(Object data) {
        this.data = data;
        this.next = null;
    }

    public Node(Object data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Object getData() {return data;}
    public Object getNext() {return next;}
}