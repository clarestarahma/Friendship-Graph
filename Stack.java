public class Stack {
    private Node top;

    public Stack() {
        top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(String nama) {
        Node newNode = new Node(nama);
        newNode.setNext(top);
        top = newNode;
    }

    public String pop() {
        if (isEmpty()) {
            System.out.println("Stack kosong!");
        }
        Node temp = top;
        top = top.getNext();
        return temp.getName();
    }

    public String peek() {
        if (isEmpty()) {
            System.out.println("Stack kosong!");
        }
        return top.getName();
    }
}