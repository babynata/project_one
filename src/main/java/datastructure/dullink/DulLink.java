package datastructure.dullink;

public class DulLink {

    public DulNode head;

    public void init(String str) {
        if (str != null) {
            String[] strs = str.split("");
            head = new DulNode(null, null, null);
            DulNode prev = head;
            int i = 0;
            while (i < strs.length) {
                DulNode cur = new DulNode(strs[i], null, null);
                prev.next = cur;
                cur.prev = prev;
                prev = cur;
                i++;
            }
        }
    }

    public void add(String s) {
        DulNode newNode = new DulNode(s, null, null);
        add(newNode);
    }

    public void add(DulNode newNode) {
        DulNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = newNode;
        newNode.prev = cur;
    }

    public void add(int index, DulNode newNode) {
        DulNode cur = head;
        int count = 0;
        while (count != index) {
            cur = cur.next;
            count++;
        }
        DulNode next = cur.next;
        cur.next = newNode;
        newNode.prev = cur;
        newNode.next = next;
    }

    public void add(int index, String s) {
        DulNode newNode = new DulNode(s, null, null);
        add(index, newNode);
    }

    public void delete() {
        DulNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.prev.next = null;
    }

    public void delete(DulNode node) {
        delete(node.data);
    }

    public void delete(String s) {
        DulNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
            if (cur.data.equals(s)) {
                cur.prev.next = cur.next;
                cur.next.prev = cur.prev;
                break;
            }
        }
    }

    public void delete(int index) {
        DulNode cur = head.next;
        int count = 0;
        while (count != index) {
            cur = cur.next;
            count++;
        }
        DulNode next = cur.next;
        cur.prev.next = next;
        next.prev = cur.prev;
    }

    public void printLink() {
        DulNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
            System.out.print(cur.data);
        }
        System.out.println();
    }

    public static void main(String[] args){
        DulLink link = new DulLink();
        link.init("abcde");
        link.printLink();
        link.add("f");
        link.printLink();
        link.add(0,"z");
        link.printLink();
        link.delete();
        link.printLink();
        link.delete("z");
        link.printLink();
        link.delete(0);
        link.printLink();
    }
}
