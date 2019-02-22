/**
 * @(#)NavTree.java
 *
 *
 * @author 
 * @version 1.00 2010/9/13
 */
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeNode;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public class NavTree extends JTree implements TreeSelectionListener{
	
	DefaultTreeModel treeModel;
	DefaultMutableTreeNode rootNode, active;
	
    public NavTree(String root) {
    	super(new DefaultMutableTreeNode(root));
    	treeModel = (DefaultTreeModel)this.getDefaultTreeModel();
    	rootNode = (DefaultMutableTreeNode)treeModel.getRoot();
    	getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
                
        addTreeSelectionListener(this);
    }
    
    public void add(DefaultMutableTreeNode child) {
    	int children = rootNode.getChildCount();
    	System.out.println(children);
    	treeModel.insertNodeInto(rootNode, child, 0);
    	scrollPathToVisible(new TreePath(child.getPath()));
    }
    
    public void add(DefaultMutableTreeNode child, DefaultMutableTreeNode parent, int childCount) {
    	treeModel.insertNodeInto(parent, child, childCount);
    	scrollPathToVisible(new TreePath(child.getPath()));
    }
    
    public DefaultMutableTreeNode getActiveNode() {
    	return active;
    }
    
    public void valueChanged(TreeSelectionEvent e) {
        active = (DefaultMutableTreeNode)getLastSelectedPathComponent();
    }
}