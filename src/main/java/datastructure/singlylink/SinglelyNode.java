package datastructure.singlylink;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SinglelyNode<T>{

    private T data;

    private SinglelyNode next;

    public SinglelyNode(T data, SinglelyNode next) {
        this.data = data;
        this.next = next;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public SinglelyNode getNext() {
        return next;
    }

    public void setNext(SinglelyNode next) {
        this.next = next;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SinglelyNode<?> that = (SinglelyNode<?>) o;

        return new EqualsBuilder()
                .append(data, that.data)
                .append(next, that.next)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(data)
                .append(next)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "SinglelyNode{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }

}
