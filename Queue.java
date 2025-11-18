public class Queue {
    private Node front;
    private Node rear;

    public Queue() {
        front = null;
        rear = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(String nama) {
        Node newNode = new Node(nama);
        
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
    }

    public String dequeue() {
        if (isEmpty()) {
            System.out.println("Queue kosong!");
        }
        
        Node temp = front;
        front = front.getNext();
        
        if (front == null) {
            rear = null;
        }
        
        return temp.getName();
    }

    public String peek() {
        if (isEmpty()) {
            System.out.println("Queue kosong!");
        }
        return front.getName();
    }
}