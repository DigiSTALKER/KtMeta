package io.github.hochikong.ktmeta.testOnly;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

public class TreeWithMultiDiscontiguousSelections {

    public static void main(String[] argv) {
        JTree tree = new JTree();
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        int[] treeSelectedRows = {3, 1};
        tree.setSelectionRows(treeSelectedRows);
        TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
                JTree treeSource = (JTree) treeSelectionEvent.getSource();
                System.out.println("<-------------------->");
                System.out.println("Min: " + treeSource.getMinSelectionRow());
                System.out.println("Max: " + treeSource.getMaxSelectionRow());
                System.out.println("Lead: " + treeSource.getLeadSelectionRow());
                for (int x : treeSource.getSelectionRows()) {
                    System.out.println(x);
                }
            }
        };
        tree.addTreeSelectionListener(treeSelectionListener);
        JFrame frame = new JFrame("JTree With Multi-Discontiguous selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JScrollPane(tree));
        frame.setPreferredSize(new Dimension(380, 320));
        frame.setLocation(150, 150);
        frame.pack();
        frame.setVisible(true);
    }

    private TreeWithMultiDiscontiguousSelections() {
    }
}
