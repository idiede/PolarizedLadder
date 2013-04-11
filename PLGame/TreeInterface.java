


import java.util.Iterator;

public interface TreeInterface<E> {
	
	
	public boolean isEmpty();
	public Iterator<E> iterator();
	public boolean isRoot();
	public Iterable<Node<E>>children(Node<E> v);
	public boolean isExternal(Node<E> t);
	public int size();
	public int depth();
}
/*public class Tree<T> {
    private Node<T> root;

    public Tree(T rootData) {
        root = new Node<T>();
        root.data = rootData;
        root.children = new ArrayList<Node<T>>();
    }

    public static class Node<T> {
        private T data;
        private Node<T> parent;
        private List<Node<T>> children;
    }
}*/
