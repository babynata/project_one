package datastructure.queue;

public class NodeQueue {

    //    public NodeLink link;
    public Node head=new Node();

/*    public NodeQueue() {
        link = new NodeLink();
    }*/

/*    public void enQueue(String s) {
        link.add(s);
    }*/

    public void enQueue(String s) {
        Node cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        Node node = new Node();
        node.data = s;
        cur.next = node;
    }

/*    public String deQueue() {
        String t = link.head.next.data;
        link.delete(0);
        return t;
    }*/

    public String deQueue() {
        Node cur = head;
        if(head.next==null) return null;
        cur.next = cur.next.next;
        return cur.next.data;
    }

/*    public void printLink() {
        link.printLink();
    }*/

    public void printLink() {
        Node cur = head.next;
        while (cur != null) {
            System.out.print(cur.data);
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args){
        NodeQueue nodeQueue = new NodeQueue();
        nodeQueue.enQueue("a");
        nodeQueue.enQueue("b");
        nodeQueue.enQueue("c");
        nodeQueue.enQueue("d");
        nodeQueue.printLink();
        System.out.println(nodeQueue.deQueue());
        System.out.println(nodeQueue.deQueue());
        nodeQueue.enQueue("a");
        nodeQueue.enQueue("b");
        nodeQueue.printLink();
    }
}
