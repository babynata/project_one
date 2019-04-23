package datastructure.singlylink;

public class SinglyLinkStack {

    public int count;
    public SinglelyLinked<String> link;

    public SinglyLinkStack() {
        link = new SinglelyLinked<>();
    }

    public boolean push(String s) {
        link.add(s);
        count++;
        return true;
    }

    public String pop() {
        if(count==0) return null;
        SinglelyNode<String> cur = link.getHead().getNext();
        while (cur.getNext() != null) {
            cur = cur.getNext();
        }
        count--;
        link.delete();
        return (String) cur.getData();
    }

    public static void main(String[] args){
        SinglyLinkStack singlyLinkStack = new SinglyLinkStack();
        singlyLinkStack.push("a");
        singlyLinkStack.push("b");
        singlyLinkStack.push("c");
        singlyLinkStack.link.printLink();
        System.out.println(singlyLinkStack.pop());
        System.out.println(singlyLinkStack.pop());
        System.out.println(singlyLinkStack.pop());
    }
}
