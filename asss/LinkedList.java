import java.text.*;
public class LinkedList {

    Node head;
    Node tail;
    Node curr;
    DecimalFormat df = new DecimalFormat("0.00");
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

    public void deleteCust(String ic) {

        Node prev = null;
        Node temp = head;

        String data = ((Sale)temp.data).getCustomerIC();
        boolean exist = false;
        

        //check all the node to find the matching key
        while(temp != null) {

            if(prev == null && data.equalsIgnoreCase(ic)) {
                exist = true;
                head = temp.next;
                temp = head;
                data = ((Sale)temp.data).getCustomerIC();
                continue;
            }
            else if(data.equalsIgnoreCase(ic)) {
                exist = true;
                prev.next = temp.next;
            }

            prev = temp;
            temp = temp.next;
            if(temp != null)
                data = ((Sale)temp.data).getCustomerIC();
        }

        //ic not exist in the list
        if(!exist) {
            System.out.println("No Record Found for this IC");
            return;
        }

        System.out.println("Record for " + ic + " is deleted!");
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
    public Node mergeByName(Node h1, Node h2) {

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
            } else if(data.getClass().toString().equalsIgnoreCase("class Sale")) {
                Sale temp1 = (Sale)h1.data;
                Sale temp2 = (Sale)h2.data;
                key1 = temp1.getCustomerName();
                key2 = temp2.getCustomerName();
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
    public Node mergeSortByName(Node head) {

        if(head.next == null) {
            return head;
        }

        Node middle = getMiddle(head);
        Node head2 = middle.next;
        middle.next = null;

        Node lefthead = mergeSortByName(head);
        Node righthead = mergeSortByName(head2);
        Node mergedHead = mergeByName(lefthead, righthead); //kiv

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

        Object data = head.data;

        if(data.getClass().toString().equalsIgnoreCase("class Category")) {
            System.out.format("+------------+----------------------+%n");
            System.out.format("| ID         | NAME                 |%n");
            System.out.format("+------------+----------------------+%n");
        } else if(data.getClass().toString().equalsIgnoreCase("class Sale")) {
            System.out.format("+-----------------+----------------------+----------------------+-----------------+---------------------+%n");
            System.out.format("| IC NUMBER       | NAME                 | WEAPON  BOUGHT       | QUANTITY        | TOTAL PRICE (RM)    |%n");
            System.out.format("+-----------------+----------------------+----------------------+-----------------+---------------------+%n");
        } else {
            System.out.format("+------------+----------------------+----------------------+-----------------+%n");
            System.out.format("| ID         | CATEGORY             | NAME                 | PRICE (RM)      |%n");
            System.out.format("+------------+----------------------+----------------------+-----------------+%n");
        }

        while(head != null) {
            

            if(data.getClass().toString().equalsIgnoreCase("class Category")) {
                Category obj = (Category)head.data;
                System.out.format("| %-10s | %-20s |\n", obj.getCID(), obj.getCName());
            } else if(data.getClass().toString().equalsIgnoreCase("class Sale")) {
                Sale obj = (Sale)head.data;
                double total = obj.totalPrice();
                System.out.format("| %-15s | %-20s | %-20s | %-15s | %-20s|\n", obj.getCustomerIC(), obj.getCustomerName(), obj.getWeaponName(), obj.getQuantity(), "RM"+ df.format(total));
            } else {
                Weapon obj = (Weapon)head.data;
                System.out.format("| %-10s | %-20s | %-20s | %-15s |\n", obj.getWeaponID(), obj.getCName(), obj.getWeaponName(), "RM" + df.format(obj.getWeaponPrice()));
            }
            // System.out.println(obj.toString());
            head = head.next;
        }

        if(data.getClass().toString().equalsIgnoreCase("class Category")) {
            System.out.format("+------------+----------------------+%n");
        } else if(data.getClass().toString().equalsIgnoreCase("class Sale")) {
            System.out.format("+-----------------+----------------------+----------------------+-----------------+---------------------+%n");
        } else {
            System.out.format("+------------+----------------------+----------------------+-----------------+%n");
        }
    }
    
}