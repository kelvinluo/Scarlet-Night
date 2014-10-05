public class Queue<Item,I>
{
    private Node first = new Node();
    public class Node
    {
        private Item item;
        private Node next;
    }

    public boolean isEmpty()
    {
        return (first.item == null);
    }

    public void enqueue(Item getitem)
    {
        if (first.item == null)
        {
            first.item = getitem;
        }
        else
        {
            Node newNode = new Node();
            newNode.item = getitem;
            newNode.next = first;
            first = newNode;
        }
    }

    public Item dequeue()
    {
        if (first.next == null)
        {
            Item result = first.item;
            first.item = null;
            return result;
        }
        else
        {
            Node newNode = first;
            Node last = new Node();
            while(newNode.next != null)
            {
                last = newNode;
                newNode = newNode.next;
            }
            last.next = null;
            return newNode.item;
        }
    }
}