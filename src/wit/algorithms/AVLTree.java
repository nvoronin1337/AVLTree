package wit.algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

import java.lang.Math;
import java.util.concurrent.ConcurrentLinkedDeque;

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

    // Note: seems to work but needs more testing
    public boolean contains (T entry) {
        // implements AVLSearch algorithm
        if(root == null){
            return false;
        }
        // Iterative solution
        AVLNode current = root;
        while(current.getData().compareTo(entry) != 0){
            if(current.getData().compareTo(entry) > 0){
                current = current.getLeft();
            }else{
                current = current.getRight();
            }

            if(current == null){
                return false;
            }
        }
        return true;
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

    // TODO deal with duplicates (now they are always inserted in right subtree)
    public void insert (T entry) {
        AVLNode toAdd = new AVLNode (entry);

        // Implement Tree Insert
        if(root == null) {
            root = toAdd;
            return;
        }

        AVLNode current = root;
        AVLNode parent = null;

        while(current != null){
            parent = current;
            if(entry.compareTo(current.getData()) < 0){
                current = current.getLeft();
            }else{
                current = current.getRight();
            }
        }

        toAdd.setParent(parent);
        if(entry.compareTo(parent.getData()) < 0){
            parent.setLeft(toAdd);
        }else{
            parent.setRight(toAdd);
        }

        // AVL balancing and rotating
        AVLNode r = updateHeights (toAdd);

        if (r != null) {
            int balance = r.getLeftHeight() - r.getRightHeight();
            if (balance != 2 && balance != -2)
                throw new IllegalStateException();
            LeftRight rotation = getRotation (toAdd, r);

            switch (rotation) {
                case LL:
                    System.out.println("LL Rotate");
                    llRotate (r);
                    break;
                case LR:
                    System.out.println("LR Rotate");
                    lrRotate (r);
                    break;
                case RL:
                    System.out.println("RL Rotate");
                    rlRotate (r);
                    break;
                case RR:
                    System.out.println("RR Rotate");
                    rrRotate (r);
                    break;
                default:
                    throw new IllegalStateException ();
            }
        }

    }

    // TODO test
    private LeftRight getRotation (AVLNode node, AVLNode nail) {
        return null;
    }

    // TODO fix
    private AVLNode updateHeights (AVLNode node) {
        AVLNode parent = node.getParent();
        AVLNode rotationPoint = null;
        int oldHeight = 0;

        while(parent != null && parent.getHeight() != oldHeight){
            oldHeight = parent.getHeight();
            parent.resetHeights();

            if(node.isLeftChild()){
                int rightHeight = parent.getRightHeight();
                //parent.setLeftHeight(node.getHeight());
                parent.height = Math.max(node.getHeight() + 1, rightHeight);
            }else if(node.isRightChild()){
                int leftHeight = parent.getLeftHeight();
                //parent.setRightHeight(node.getHeight());
                parent.height = Math.max(node.getHeight() + 1, leftHeight);
            }else{
                throw new IllegalStateException();
            }

            if(parent.getHeight() == oldHeight){
                break;
            }

            node = parent;
            parent = node.getParent();

            if(parent == null){
                break;
            }
            parent.resetHeights();
            int balance = parent.getLeftHeight() - parent.getRightHeight();
            if(balance == 2 || balance == -2){
                rotationPoint = parent;
            }else{
                throw new IllegalStateException();
            }
        }

        return rotationPoint;
    }

    private void llRotate (AVLNode r) {
        /*
        // to implement
        AVLNode right = r.getRight();
        AVLNode leftChild = right.getLeft();

        // Rotate
        right.setLeft(r);
        r.setRight(leftChild);
         */
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

        protected int getRealHeight () {
            // to implement as recursive method
            int leftHeight = 0, rightHeight = 0;
            if(getRight() != null){
                rightHeight = getRight().getHeight();
            }
            if(getLeft() != null){
                leftHeight = getLeft().getHeight();
            }
            return 1 + (rightHeight > leftHeight ? rightHeight : leftHeight);
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

    // Left Root Right
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
