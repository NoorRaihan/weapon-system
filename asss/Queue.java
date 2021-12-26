public class Queue {

    LinkedList list;

    public Queue() {
        list = new LinkedList();
    }

    public void enqueue (Object elem) {
        list.insertAtBack(elem);
    }

    public Object dequeue() {
        return list.removeFront();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
