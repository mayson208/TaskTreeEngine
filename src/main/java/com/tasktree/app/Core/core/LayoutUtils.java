package com.tasktree.core;

import com.tasktree.model.TaskNode;

import java.util.ArrayList;
import java.util.List;

public class LayoutUtils {

    public static void applyTreeLayout(TaskNode root, double startX, double startY, double horizontalSpacing, double verticalSpacing) {
        List<TaskNode> children = root.getChildren();
        int count = children.size();

        root.setX(startX);
        root.setY(startY);

        if (count == 0) return;

        double totalWidth = (count - 1) * horizontalSpacing;

        for (int i = 0; i < count; i++) {
            TaskNode child = children.get(i);
            double childX = startX - totalWidth / 2 + i * horizontalSpacing;
            double childY = startY + verticalSpacing;

            applyTreeLayout(child, childX, childY, horizontalSpacing, verticalSpacing);
        }
    }

    public static void applyRadialLayout(TaskNode root, double centerX, double centerY, double radiusStep) {
        root.setX(centerX);
        root.setY(centerY);
        radialChildren(root, centerX, centerY, radiusStep, 0);
    }

    private static void radialChildren(TaskNode node, double centerX, double centerY, double radiusStep, int depth) {
        List<TaskNode> children = node.getChildren();
        int count = children.size();
        if (count == 0) return;

        double radius = radiusStep * (depth + 1);
        double angleStep = 360.0 / count;

        for (int i = 0; i < count; i++) {
            TaskNode child = children.get(i);

            double angle = Math.toRadians(i * angleStep);
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);

            child.setX(x);
            child.setY(y);

            radialChildren(child, centerX, centerY, radiusStep, depth + 1);
        }
    }

    public static void applyForceDirectedLayout(List<TaskNode> nodes, int iterations, double repulsion, double attraction) {
        if (nodes.size() < 2) return;

        for (int iter = 0; iter < iterations; iter++) {
            List<double[]> forces = new ArrayList<>();

            for (TaskNode n1 : nodes) {
                double fx = 0;
                double fy = 0;

                for (TaskNode n2 : nodes) {
                    if (n1 == n2) continue;

                    double dx = n1.getX() - n2.getX();
                    double dy = n1.getY() - n2.getY();
                    double dist = Math.sqrt(dx * dx + dy * dy) + 0.01;

                    double repulse = repulsion / (dist * dist);
                    fx += repulse * (dx / dist);
                    fy += repulse * (dy / dist);
                }

                for (TaskNode c : n1.getChildren()) {
                    double dx = c.getX() - n1.getX();
                    double dy = c.getY() - n1.getY();
                    double dist = Math.sqrt(dx * dx + dy * dy) + 0.01;

                    double attract = (dist * dist) / attraction;
                    fx += attract * (dx / dist);
                    fy += attract * (dy / dist);
                }

                forces.add(new double[] { fx, fy });
            }

            for (int i = 0; i < nodes.size(); i++) {
                TaskNode node = nodes.get(i);
                double[] f = forces.get(i);
                node.setX(node.getX() + f[0]);
                node.setY(node.getY() + f[1]);
            }
        }
    }

    public static double centerX(List<TaskNode> nodes) {
        return nodes.stream().mapToDouble(TaskNode::getX).average().orElse(0);
    }

    public static double centerY(List<TaskNode> nodes) {
        return nodes.stream().mapToDouble(TaskNode::getY).average().orElse(0);
    }
}
