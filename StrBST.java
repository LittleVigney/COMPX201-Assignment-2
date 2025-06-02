public class StrBST {
    Node root;

    public StrBST(){
        this.root = null;
    }

    public boolean empty(){
        return this.root == null;
    }

    public void insert(String goal){
        root = insertR(this.root, goal);
    }

    public Node insertR(Node currentRoot, String goal){
        // root is empty, add it here
        if (currentRoot== null){
            currentRoot= new Node(goal);
        }

        // goal is smaller than root
        if (goal.compareTo(currentRoot.value) < 0){
            currentRoot.left = insertR(currentRoot.left, goal);
        }

        // goal is greater than root
        if (goal.compareTo(currentRoot.value) > 0){
            currentRoot.right = insertR(currentRoot.right, goal);
        }

        return currentRoot;
    }

    public boolean search(String goal){
        return searchR(root, goal);
    }

    private boolean searchR(Node currentRoot, String goal){
        boolean res = false;

        if (currentRoot == null){
            return false;
        }
        else if (goal.compareTo(currentRoot.value) == 0){
            return true;
        }
        else if (goal.compareTo(currentRoot.value) < 0){
            res = searchR(currentRoot.left, goal);
        }
        else if (goal.compareTo(currentRoot.value) > 0){
            res = searchR(currentRoot.right, goal);
        }

        return res;
    }

    public void print(){
        printR(this.root);
    }

    private void printR(Node currentRoot){
        // reach null node or tree is empty
        if (currentRoot == null){
            return;
        }

        // print current node
        System.out.print("Root: " + currentRoot.value);

        // print left child
        if (currentRoot.left == null){
            System.out.print("| Left: null. ");
        }
        else{
            System.out.print("| Left: " + currentRoot.left.value + ". ");
        }

        // print right child
        if (currentRoot.right == null){
            System.out.print("| Right: null. ");
        }
        else{
            System.out.print("| Right: " + currentRoot.right.value + ". ");
        }

        System.out.println();

        // print left subtree
        printR(currentRoot.left);

        // print right subtree
        printR(currentRoot.right);
    }

    public void remove(String goal){
        root = removeR(this.root, goal);
    }

    public Node removeR(Node currentRoot, String goal){
        // if reach null node
        if (currentRoot == null){
            System.out.println("Goal is not in tree. Remove failed.");
            return null;
        }

        // if goal is smaller than currentRoot, enter left subtree
        if (goal.compareTo(currentRoot.value) < 0){
            currentRoot.left = removeR(currentRoot.left, goal);
        }
        // if goal is greater than currentRoot, enter right subtree
        else if (goal.compareTo(currentRoot.value) > 0){
            currentRoot.right = removeR(currentRoot.right, goal);
        }
        // if goal is found
        else if (goal.compareTo(currentRoot.value) == 0) {
            // if goal is leaf node
            if (currentRoot.left == null && currentRoot.right == null){
                currentRoot = null;
            }
            // if goal has one child
            else if (currentRoot.left == null && currentRoot.right != null){
                return currentRoot.right;
            }
            else if (currentRoot.left != null && currentRoot.right == null){
                return currentRoot.left;
            }
            // if goal has two child
            else if (currentRoot.left != null && currentRoot.right != null){
                // find the left most node of current root's right subtree
                Node newRoot = findLeftMost(currentRoot.right);

                // replace and remove the left most node
                currentRoot.value = newRoot.value;

                currentRoot.right = removeR(currentRoot.right, newRoot.value);
            }
        }

        return currentRoot;
    }

    // find the left most node
    public Node findLeftMost(Node goal){
        Node currentRoot = goal;

        while (currentRoot.left != null){
            currentRoot = currentRoot.left;
        }

        return currentRoot;
    }
}
