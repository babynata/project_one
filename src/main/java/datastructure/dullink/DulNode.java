package datastructure.dullink;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DulNode {

    public String data;

    public DulNode prev;

    public DulNode next;

    public DulNode(String data, DulNode prev, DulNode next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DulNode dulNode = (DulNode) o;

        return new EqualsBuilder()
                .append(data, dulNode.data)
                .append(prev, dulNode.prev)
                .append(next, dulNode.next)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(data)
                .append(prev)
                .append(next)
                .toHashCode();
    }
}
