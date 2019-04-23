package datastructure.dullink;

public class DulLinkReverse {

    public static void reverse(DulLink link) {
        DulNode newPrev = link.head.next.next;
        DulNode cur = link.head.next;
        while (newPrev.next != null) {
            DulNode temp = newPrev.next;
            cur.prev = newPrev;
            newPrev.next = cur;
            cur = newPrev;
            newPrev = temp;
        }
        cur.prev = link.head;
        link.head.next = cur;
    }

    public static void main(String[] args){
        DulLink link = new DulLink();
        link.init("abcdef");
        link.printLink();
        DulLinkReverse.reverse(link);
        link.printLink();
    }
}
