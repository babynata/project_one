package datastructure.singlylink;

public class SinglyLinkedPalindrome {

    private SinglelyLinked<String> link;

    public boolean ifIsPalindrome(SinglelyLinked<String> link) {
        SinglelyNode<String> slow = link.getHead();
        SinglelyNode<String> fast = link.getHead();
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        if (fast == null) {
            SinglelyNode<String> head = slow.getNext();
            SinglelyNode<String> next = slow;
            SinglelyNode<String> oldNext = slow;
            while (head != null) {
                oldNext = head.getNext();
                head.setNext(next);
                next = head;
                head = oldNext;
            }
            slow.setNext(null);

            head = next;
            slow = link.getHead().getNext();
            while (head != null && slow != null) {
                if (!head.getData().equals(slow.getData())) {
                    return false;
                }
                head = head.getNext();
                slow = slow.getNext();
            }

        }else{
            slow = slow.getNext();
            SinglelyNode<String> head = slow.getNext();
            SinglelyNode<String> next = slow;
            SinglelyNode<String> oldNext = slow;
            while (head != null) {
                oldNext = head.getNext();
                head.setNext(next);
                next = head;
                head = oldNext;
            }
            slow.setNext(null);

            head = next;
            slow = link.getHead().getNext();
            while (head != null && slow != null) {
                if (!head.getData().equals(slow.getData())) {
                    return false;
                }
                head = head.getNext();
                slow = slow.getNext();
            }
        }
        return true;
    }

    public SinglelyLinked<String> initLink(String text) {
        link = new SinglelyLinked<>();
        for (String s : text.split("")) {
            link.add(s);
        }
        return link;
    }

    public void printLink(SinglelyLinked<String> linked) {
        linked.printLink();
    }

    public static void main(String[] args){
        String text = "abccb";
        SinglyLinkedPalindrome singlyLinkedPalindrome = new SinglyLinkedPalindrome();
        SinglelyLinked<String> link=singlyLinkedPalindrome.initLink(text);
//        singlyLinkedPalindrome.printLink(link);
//        System.out.println(link.getSize());
        System.out.println(singlyLinkedPalindrome.ifIsPalindrome(link));
    }
}
