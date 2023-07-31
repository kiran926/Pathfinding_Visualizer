import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PathDemo extends JPanel {

	final int maxCol=15;
	final int maxRow=10;
	final int nodeSize=70;
	final int screenWidth=nodeSize*maxCol;
	final int screenHeight=nodeSize*maxRow;
	
	Node[][] node= new Node[maxCol][maxRow];
	Node startNode, goalNode, currentNode;
	ArrayList<Node> openList =new ArrayList<>();
	ArrayList<Node> opencheckedList =new ArrayList<>();
	
	boolean goalReached=false;
	int step =0;
	
	public PathDemo() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setLayout(new GridLayout(maxRow, maxCol));
		this.addKeyListener(new KeyIn(this));
		this.setFocusable(true);
		
		
		int col=0;
		int row=0;
		
		while(col<maxCol && row<maxRow) {
			node[col][row]=new Node(col, row);
			this.add(node[col][row]);
			
			col++;
			if(col == maxCol){
			col=0;
			row++;
		}
		}
		
		setStartNode(3, 6);
		setGoalNode(11, 3);	
		
		
		setSolidNode(10, 2);
		setSolidNode(10, 3);
		setSolidNode(10, 4);
		setSolidNode(10, 5);
		setSolidNode(10, 6);
		setSolidNode(10, 7);
		setSolidNode(6, 2);
		setSolidNode(7, 2);
		setSolidNode(8, 2);
		setSolidNode(9, 2);
		setSolidNode(11, 7);
		setSolidNode(12, 7);
		setSolidNode(6, 1);
		
		setCostOnNodes();
		}
	private void setStartNode(int col, int row) {
		node[col][row].setAsStart();
		startNode=node[col][row];
		currentNode=startNode;
		
	}
	private void setGoalNode(int col, int row) {
		node[col][row].setAsGoal();
		goalNode=node[col][row];
				
	}
	private void setSolidNode(int col, int row) {
		node[col][row].setAsSolid();
	}
	private void setCostOnNodes() {
		int col=0;
		int row=0;
		
		while(col<maxCol && row <maxRow) {
			
			getCost(node[col][row]);
			col++;
			if(col==maxCol) {
				col=0;
				row++;
			}
		}
	}
	
	
    private void getCost(Node node) {
    	int xDistance =Math.abs(node.col - startNode.col);
    	int yDistance=Math.abs(node.row - startNode.row);
    	node.gCost=xDistance + yDistance;
    	
    	xDistance =Math.abs(node.col - goalNode.col);
    	yDistance=Math.abs(node.row - goalNode.row);
    	node.hCost=xDistance + yDistance;
    	
    	node.fCost=node.gCost + node.hCost;
    	
    	if(node!=startNode && node!=goalNode) {
    		node.setText("<html>F:" + node.fCost + "<br>G:" + node.gCost + "</html>");
    	}
    }
    public void search() {
    	if(goalReached==false) {
    		
    		int col = currentNode.col;
    		int row= currentNode.row;
    		
    		currentNode.setAsChecked();
    		opencheckedList.add(currentNode);
    		openList.remove(currentNode);
    		
    		if(row-1>=0) {
    		openNode(node[col][row-1]);
    		}
    		if(col-1>=0) {
    		openNode(node[col-1][row]);
    		}
    		if(row+1<maxRow) {
    		openNode(node[col][row+1]);
    		}
    		if(col+1<maxCol) {
    		openNode(node[col+1][row]);
    		}
    		
    		
    		int bestNodeIndex=0;
    		int bestNodefCost=999;
    		
    		for(int i=0; i<openList.size(); i++) {
    			if(openList.get(i).fCost<bestNodefCost) {
    				bestNodeIndex=i;
    				bestNodefCost=openList.get(i).fCost;
    			}
    			
    			else if(openList.get(i).fCost == bestNodefCost) {
    				if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
    					bestNodeIndex =i;
    				}
    			}
    		}
    		
    		currentNode = openList.get(bestNodeIndex);
    		
    		if(currentNode==goalNode) {
    			goalReached=true;
    			
    		}
    	}
    	
    	
    }
    
    public void AutoSearch() {
    	while(goalReached==false  && step <300) {
    		
    		int col = currentNode.col;
    		int row= currentNode.row;
    		
    		currentNode.setAsChecked();
    		opencheckedList.add(currentNode);
    		openList.remove(currentNode);
    		
    		if(row-1>=0) {
    		openNode(node[col][row-1]);
    		}
    		if(col-1>=0) {
    		openNode(node[col-1][row]);
    		}
    		if(row+1<maxRow) {
    		openNode(node[col][row+1]);
    		}
    		if(col+1<maxCol) {
    		openNode(node[col+1][row]);
    		}
    		
    		
    		int bestNodeIndex=0;
    		int bestNodefCost=999;
    		
    		for(int i=0; i<openList.size(); i++) {
    			if(openList.get(i).fCost<bestNodefCost) {
    				bestNodeIndex=i;
    				bestNodefCost=openList.get(i).fCost;
    			}
    			
    			else if(openList.get(i).fCost == bestNodefCost) {
    				if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
    					bestNodeIndex =i;
    				}
    			}
    		}
    		
    		currentNode = openList.get(bestNodeIndex);
    		
    		if(currentNode==goalNode) {
    			goalReached=true;
    			trackPath();
    		}
    	}
    	step++;
    }
    private void openNode(Node node) {
    	if(node.open == false && node.checked == false && node.solid==false) {
    		node.setAsOpen();
    		node.parent=currentNode;
    		openList.add(node);
    	}
    }
    private void trackPath() {
    	Node current = goalNode;
    	while(current!=startNode) {
    		current=current.parent;
    		
    		if(current!=startNode) {
    			current.setAsPath();
    		}
    	}
    }
}
