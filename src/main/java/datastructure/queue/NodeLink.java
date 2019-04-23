package datastructure.queue;

public class NodeLink {

    public Node head = new Node();

    public void add(String s) {
        Node cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        Node node = new Node();
        node.data = s;
        cur.next = node;
    }

    public void add(int index, String s) {
        int count = 0;
        Node cur = head;
        while (count != index) {
            cur = cur.next;
            count++;
        }
        Node node = new Node();
        node.data = s;
        node.next = cur.next;
        cur.next = node;
    }

    public void delete() {
        Node cur = head;
        while (cur.next.next != null) {
            cur = cur.next;
        }
        cur.next = null;
    }

    public void delete(int index) {
        Node cur = head;
        int count = 0;
        while (count != index) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
    }

    public void printLink() {
        Node cur = head.next;
        while (cur != null) {
            System.out.print(cur.data);
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args){
        NodeLink link = new NodeLink();
        link.add("a");
        link.add("b");
        link.add("c");
        link.add("d");
        link.printLink();
        link.add(0,"z");
        link.printLink();
        link.delete();
        link.printLink();
        link.delete(0);
        link.printLink();
    }
}
