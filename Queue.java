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

    public void enqueue(int data) {
        Node newNode = new Node(String.valueOf(data));
        
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue kosong!");
        }
        
        Node temp = front;
        front = front.getNext();
        
        if (front == null) {
            rear = null;
        }
        
        return Integer.parseInt(temp.getName());
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue kosong!");
        }
        return Integer.parseInt(front.getName());
    }
}
