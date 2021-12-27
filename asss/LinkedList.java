public class LinkedList {

    Node head;
    Node tail;
    Node curr;

    public LinkedList() {
        this.head = this.curr = this.tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Object getHead() {
        if(isEmpty()) {
            return null;
        }else{
            curr = head;
            return curr.data;
        }
    }

    public Object getTail() {
        if(isEmpty()) {
            return null;
        }else {
            curr = tail;
            return curr.data;
        }
    }

    public Object getNext() {
        if(isEmpty()){
            throw new EmptyListException();
        }else if(curr != tail){
            curr = curr.next;
            return curr.data;
        }else {
            return null;
        }
    }

    public void insertAtFront(Object obj) {
        if(isEmpty()) {
            head = tail = new Node(obj);
        }else {
            head = new Node(obj,head);
        }
    }

    public void insertAtBack(Object obj) {
        if(isEmpty()) {
            head = tail = new Node(obj);
        }else{
            tail = tail.next = new Node(obj);                                 
        }
    }

    public Object removeFront() {
        
        Object remove;
        if(isEmpty()) {
            throw new EmptyListException();
        }
        
        remove = head.data;

        if(head.equals(tail)) {
            head = tail = null;
        }else {
            head = head.next;
        }
        return remove;
    }

    public Object removeBack() {
        Object remove;

        if(isEmpty()) {
            throw new EmptyListException();
        }
        remove = tail.data;

        if(tail.equals(head)) {
            tail = head = null;
        }else {

            Node currN = head;
            while(currN != tail) {
                currN = currN.next;
            }
            tail = currN;
            tail.next = null;
        }
        return remove;
    }

    public int size(){
        Node temp = head;
        int count = 0;

        while(temp != null)
        {
            count++;
            temp = temp.next;
        }

        return count;
    }

    //merge sort algorithm
    public Node mergeByPrice(Node h1, Node h2) {

        Node temp = new Node();
        Node finallst = temp;

        while(h1 != null && h2 != null) {
            Weapon wTemp1 = (Weapon)h1.data;
            Weapon wTemp2 = (Weapon)h2.data;

            if(wTemp1.getWeaponPrice() < wTemp2.getWeaponPrice()) {
                temp.next = h1;
                h1 = h1.next;
            } else {
                temp.next = h2;
                h2 = h2.next;
            }
            temp = temp.next;
        }

        while(h1 != null) {
            temp.next = h1;
            h1 = h1.next;
            temp = temp.next;
        }

        while(h2 != null) {
            temp.next = h2;
            h2 = h2.next;
            temp = temp.next;
        }

        return finallst.next;
    }

    // //try to mergesort
    public Node mergeSortByPrice(Node head) {

        if(head.next == null) {
            return head;
        }

        Node middle = getMiddle(head);
        Node head2 = middle.next;
        middle.next = null;

        Node lefthead = mergeSortByPrice(head);
        Node righthead = mergeSortByPrice(head2);
        Node mergedHead = mergeByPrice(lefthead, righthead); //kiv

        return mergedHead;
    }

    //merge sort algorithm
    public Node mergeByID(Node h1, Node h2) {

        Node temp = new Node();
        Node finallst = temp;
        String key1 = null;
        String key2 = null;

        while(h1 != null && h2 != null) {
            Object data = h1.data;

            if(data.getClass().toString().equalsIgnoreCase("class Category")) {
                Category temp1 = (Category)h1.data;
                Category temp2 = (Category)h2.data;
                key1 = temp1.getCID();
                key2 = temp2.getCID();
            } else {
                Weapon temp1 = (Weapon)h1.data;
                Weapon temp2 = (Weapon)h2.data;
                key1 = temp1.getWeaponID();
                key2 = temp2.getWeaponID();
            }

            if(key2.compareToIgnoreCase(key1) > 0) {
                temp.next = h1;
                h1 = h1.next;
            } else {
                temp.next = h2;
                h2 = h2.next;
            }
            temp = temp.next;
        }

        while(h1 != null) {
            temp.next = h1;
            h1 = h1.next;
            temp = temp.next;
        }

        while(h2 != null) {
            temp.next = h2;
            h2 = h2.next;
            temp = temp.next;
        }

        return finallst.next;
    }

    // //try to mergesort
    public Node mergeSortByID(Node head) {

        if(head.next == null) {
            return head;
        }

        Node middle = getMiddle(head);
        Node head2 = middle.next;
        middle.next = null;

        Node lefthead = mergeSortByID(head);
        Node righthead = mergeSortByID(head2);
        Node mergedHead = mergeByID(lefthead, righthead); //kiv

        return mergedHead;
    } 

    //get middle of the linkedlist using hare and tortoise algorithm
    public Node getMiddle(Node head) {
        
        Node slow,fast;
        slow = head; //move one pointer at one time
        fast = head; //move two pointer at one time

        //loop until fast get the null and slow will point to the middle pointer
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public void displayList(Node head) {

        while(head != null) {
            Weapon obj = (Weapon)head.data;
            System.out.println(obj.toString());
            head = head.next;
        }
    }
    
}