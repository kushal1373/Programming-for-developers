//Qn 4b
import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

public class MyClosestValuesInBST {
    // Method to find x number of closest values to the target in a BST
    public List<Integer> closestValues(TreeNode root, double target, int x) {
        List<Integer> closestValues = new ArrayList<>();
        helper(root, target, x, closestValues);
        return closestValues;
    }
    
    private void helper(TreeNode node, double target, int x, List<Integer> closestValues) {
        if (node == null) return;
        
        // In-order traversal to maintain sorted order
        helper(node.left, target, x, closestValues);
        
        // Add the current node's value if the list is not yet filled up to x values
        if (closestValues.size() < x) {
            closestValues.add(node.val);
        } else {
            // If the list is already filled, check if the current value is closer than the farthest value
            if (Math.abs(node.val - target) < Math.abs(closestValues.get(0) - target)) {
                closestValues.remove(0);
                closestValues.add(node.val);
            } else {
                // If not closer, break the loop
                return;
            }
        }
        
        helper(node.right, target, x, closestValues);
    }

    // Main method for testing
    public static void main(String[] args) {
        MyClosestValuesInBST solution = new MyClosestValuesInBST();
        // Create the sample BST
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        // Target value and number of closest values to find
        double target = 3.8;
        int x = 2;

        // Find the closest values to the target
        List<Integer> result = solution.closestValues(root, target, x);
        // Print the result
        System.out.println("Closest values to " + target + ": " + result);
    }
}
