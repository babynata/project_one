package datastructure.singlylink;

public class SinglyLinkCombination {

    public SinglelyLinked<String> combine(SinglelyLinked<String> a, SinglelyLinked<String> b) {
        SinglelyLinked<String> newLink = new SinglelyLinked<>();

        SinglelyNode<String> aa = a.getHead().getNext();
        SinglelyNode<String> bb = b.getHead().getNext();

        int count = 0;
        while (aa != null && bb != null) {
            String as = (String) aa.getData();
            String bs = (String) bb.getData();
            if (as.compareTo(bs) > 0) {
                newLink.add(bb);
                bb = bb.getNext();
                b.getHead().setNext(bb);
            } else if (as.compareTo(bs) < 0) {
                newLink.add(aa);
                aa = aa.getNext();
                a.getHead().setNext(aa);
            }
        }
        SinglelyNode<String> next = a.getHead().getNext();
        while (next != null) {
            newLink.add(next);
            next = next.getNext();
        }
        next = b.getHead().getNext();
        while (next != null) {
            newLink.add(next);
            next = next.getNext();
        }

        return newLink;
    }

    public static void main(String[] args){
        SinglelyLinked<String> a = new SinglelyLinked<>();
        a.init("aceg", a);
        SinglelyLinked<String> b = new SinglelyLinked<>();
        b.init("bdfh",b);
        SinglelyLinked<String> link = new SinglyLinkCombination().combine(a, b);
        link.printLink();
     }
}
