public class Stack {
    private Node top;

    public Stack() {
        top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(int data) {
        Node newNode = new Node(String.valueOf(data));
        newNode.setNext(top);
        top = newNode;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack kosong!");
        }
        Node temp = top;
        top = top.getNext();
        return Integer.parseInt(temp.getName());
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack kosong!");
        }
        return Integer.parseInt(top.getName());
    }
}