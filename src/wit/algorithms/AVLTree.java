package wit.algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;


public class AVLTree < T extends Comparable <? super T>> {

    public AVLNode root;

    public AVLTree () {
        root = null;
    }

    public void setRoot (AVLNode rootNode) {
        root = rootNode;
    }

    public int getHeight() {
        if (isEmpty())
            return 0;
        return root.getHeight();
    }

    public int getRealHeight () {
        if (isEmpty())
            return 0;
        return root.getRealHeight();
    }

    public int getNumberOfNodes() {
        return root.getNumberOfNodes();
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public void clear() {
        root = null;
    }

    public boolean contains (T entry) {
        // implements AVLSearch algorithm
        return false;
    }

    private enum LeftRight {
        LL, LR, RL, RR
    }


    private boolean delete (AVLNode node) {
        // to implement; do rotation only for extra credit if you can
        return true;
    }

    public boolean delete (T entry) {
        AVLNode node = root;
        // find the node
        if (node == null)
            return false;  // should be fixed

        return delete (node);
    }

    public void insert (T entry) {
        AVLNode toAdd = new AVLNode (entry);
        // Implement Tree Insert
        AVLNode y = null;
        AVLNode x = root;

        while(x != null){
            y = x;
            if(entry.compareTo(x.getData()) < 0){
                x = x.getLeft();
            }else{
                x = x.getRight();
            }
        }
        toAdd.setParent(y);
        if(y == null){
            root = toAdd;
        }else if(entry.compareTo(y.getData()) < 0){
            y.setLeft(toAdd);
        }else{
            y.setRight(toAdd);
        }

        /**
        AVLNode r = updateHeights (toAdd);

        if (r != null) {
            int balance = r.getLeftHeight() - r.getRightHeight();
            if (balance != 2 && balance != -2)
                throw new IllegalStateException();
            LeftRight rotation = getRotation (toAdd, r);

            switch (rotation) {
                case LL:
                    llRotate (r);
                    break;
                case LR:
                    lrRotate (r);
                    break;
                case RL:
                    rlRotate (r);
                    break;
                case RR:
                    rrRotate (r);
                    break;
                default:
                    throw new IllegalStateException ();
            }
        }
         */
    }

    // A utility function to get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private LeftRight getRotation (AVLNode node, AVLNode nail) {
        int balance =  node.getLeftHeight() - node.getRightHeight();

        return null;  // should be fixed
    }

    private AVLNode updateHeights (AVLNode node) {
        // to implement
        AVLNode x = node.getLeft();
        AVLNode T2 = x.getRight();

        x.setRight(node);
        node.setLeft(T2);

        node.resetHeights();


        return null;  // should be fixed
    }

    private void llRotate (AVLNode r) {
        // to implement
        AVLNode right = r.getRight();
        AVLNode leftChild = right.getLeft();

        // Rotate
        right.setLeft(r);
        r.setRight(leftChild);
    }
    private void rrRotate (AVLNode r) {
        // to implement
    }

    private void lrRotate (AVLNode r) {
        // to implement
    }

    private void rlRotate (AVLNode r) {
        // to implement
    }

    private class AVLNode {
        T data;
        AVLNode left;
        AVLNode right;
        AVLNode parent;
        int height;
        int leftHeight;
        int rightHeight;

        public AVLNode (T newEntry ) {
            data = newEntry;
            left = null; right = null; parent = null;
            height = 1;
            leftHeight = rightHeight = 0;
        }

        // ACCESSORS/MUTATORS
        protected T getData() {
            return data;
        }

        protected void setData (T data) {
            this.data = data;
        }

        protected AVLNode getParent () {
            return parent;
        }

        protected AVLNode getLeft () {
            return left;
        }

        protected AVLNode getRight () {
            return right;
        }

        protected void setLeft( AVLNode node) {
            left = node;
        }

        protected void setRight (AVLNode node) {
            right = node;
        }

        protected void setParent (AVLNode p) {
            parent = p;
        }

        protected int getLeftHeight () {
            return leftHeight;
        }

        protected int getRightHeight () {
            return rightHeight;
        }

        protected void setLeftHeight ( int h) {
            leftHeight = h;
        }

        protected void setRightHeight ( int h) {
            rightHeight = h;
        }

        protected boolean isLeftChild () {
            return (parent != null && this == parent.getLeft());
        }

        protected boolean isRightChild () {
            return (parent != null && this == parent.getRight());
        }

        protected int getHeight() {
            return height;
        }

        protected void computeHeight () {
            height = 1 + (leftHeight > rightHeight ? leftHeight : rightHeight);
        }

        protected void resetHeights() {
            leftHeight = (left == null ? 0 : left.getHeight());
            rightHeight = (right == null) ? 0 : right.getHeight();
            computeHeight ();
        }

        // TODO
        protected int getNumberOfNodes () {
            // to implement
            return 0; // should be fixed
        }

        // TODO
        protected AVLNode getSuccessor () {
            // to implement; needed for delete()
            return null; // should be fixed
        }

        // TODO
        protected int getRealHeight () {
            // to implement as recursive method

            return 0; // needs to be fixed
        }
    }

    // ITERATORS
    protected Iterator <T> getLevelOrderIterator() {
        if (isEmpty())
            throw new IllegalStateException ("No iteration on empty tree");
        return new LevelOrderIterator();
    }

    private class LevelOrderIterator implements Iterator <T> {

        private ArrayList <AVLNode > nodeQueue;

        public LevelOrderIterator () {
            nodeQueue = new ArrayList <> ();
            nodeQueue.add (root);
        }

        @Override
        public boolean hasNext() {
            return (!nodeQueue.isEmpty());
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException ();
            AVLNode node = nodeQueue.remove(0);
            T item = node.getData();
            AVLNode left = node.getLeft();
            if (left != null)
                nodeQueue.add(left);
            AVLNode right = node.getRight();
            if (right != null)
                nodeQueue.add(right);
            return item;
        }

    }

    private class InOrderIterator implements Iterator <T> {

        private Stack <AVLNode> stack;
        private AVLNode currNode;

        public InOrderIterator () {
            stack = new Stack <> ();
            currNode = root;
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty() || currNode != null;
        }

        @Override
        public T next() {
            T item;
            while (currNode != null ) {
                stack.push (currNode);
                currNode = currNode.getLeft();
            }
            if (!stack.isEmpty()) {
                currNode = stack.pop();
                item = currNode.getData();
                currNode = currNode.getRight();
            }
            else
                throw new NoSuchElementException ();
            return item;
        }

    }

    public Iterator <T> getInorderIterator () {
        if (isEmpty())
            throw new IllegalStateException ("No iteration on empty tree");
        return new InOrderIterator();
    }

}
