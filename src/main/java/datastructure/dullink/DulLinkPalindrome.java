package datastructure.dullink;

public class DulLinkPalindrome {

    public static boolean ifPalindrome(DulLink link) {
        DulNode slow = link.head.next;
        DulNode fast = link.head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast == null) {
            fast = slow.prev;
            while (fast.prev.data != null && slow.next != null) {
                if (!fast.data.equals(slow.data)) return false;
                fast = fast.prev;
                slow = slow.next;
            }
        } else {
            fast = slow;
            while (fast.prev.data != null && slow.next != null) {
                if (!fast.data.equals(slow.data)) return false;
                fast = fast.prev;
                slow = slow.next;
            }
        }

        return true;
    }

    public static void main(String[] args){
        DulLink dulLink = new DulLink();
        dulLink.init("abccba");
        dulLink.printLink();
        System.out.println(DulLinkPalindrome.ifPalindrome(dulLink));
    }
}
