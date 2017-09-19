package io.wangsu.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wangs on 9/18/2017.
 */
public class UndirectedGraphNode {
    public int label;
    public List<UndirectedGraphNode> neighbors;
    public UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
}
