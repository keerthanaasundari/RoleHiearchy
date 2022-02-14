package com.program.main;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class RoleHierarchy {
	public static void insertNode(RoleTree root,RoleTree subNode,String reportingRole) { 
		if(root.getVal().equals(reportingRole)) {
			  if(root.getChildren().isEmpty()) {
			  List<RoleTree> subNodes=new LinkedList<>();
			  subNode.parent=root;
			  subNodes.add(subNode);
			  root.children=subNodes;
		      }else {
		    	root.getChildren().add(subNode); 		      
		     }
		  }else {
			  if(!root.getChildren().isEmpty()) {
				  for(RoleTree element:root.getChildren()) {
						 if(element.getVal().equals(reportingRole)) {
							 subNode.parent=element;
							  element.children.add(subNode);
							  break;
						 }
				      }
				  
			  }
			  
		  }
	}
	
	public static void printTree(RoleTree root) {
		if(root == null) return;
        Queue<RoleTree> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int l = queue.size();
            for(int i=0;i<l;i++) { 
                RoleTree node = queue.remove();
                System.out.print(node.val + " "+"\n");
                for (RoleTree element : node.children) {
                    queue.add(element);
                }
            }
       
        }
		}
	public static void printUserTree(RoleTree root) {
		if(root == null) return;
        Queue<RoleTree> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int l = queue.size();
            for(int i=0;i<l;i++) { 
                RoleTree node = queue.remove();
                for(int iterator=0;iterator<node.userName.size();iterator++) {
                	System.out.print(node.userName.get(iterator) + " - " +node.val + " "+"\n");
                }
                
                for (RoleTree element : node.children) {
                    queue.add(element);
                }
            }
       
        }
		}
	public static void printSubUserTree(RoleTree root) {
		if(root == null) return;
        Queue<RoleTree> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int l = queue.size();
            for(int i=0;i<l;i++) { 
                RoleTree node = queue.remove();
                if(node==root) {
                if(!root.userName.isEmpty())
                		for(int item=0;item<root.userName.size();item++) {
                	        System.out.print(root.userName.get(item)+" - ");
                		}
                }else {
                	
                for(int iterator=0;iterator<node.userName.size();iterator++) {
                	
            		System.out.print(node.userName.get(iterator)+" ");
            		
                		
                }
                }
                for (RoleTree element : node.children) {
                    queue.add(element);
                }
            }
        }
		}
	public static void printSubUser(RoleTree root) {
		if(root == null) return;
        Queue<RoleTree> queue = new LinkedList<>();
        queue.add(root);
        printSubUserTree(root);
        System.out.print("\n");
        while(!queue.isEmpty()) {
            int l = queue.size();
            
            for(int i=0;i<l;i++) { 
                RoleTree node = queue.remove();
                
                for (RoleTree element : node.children) {	
                	printSubUserTree(element);
                	System.out.print("\n");
                    queue.add(element);
                }
                
            }
         
        }
        
		}
	public static RoleTree searchNode(RoleTree root, String search){
		if(root == null) return null;
        Queue<RoleTree> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int l = queue.size();
            for(int i=0;i<l;i++) { 
                RoleTree node = queue.remove();
               
                for (RoleTree element : node.children) {
                	if(element.getVal().equals(search)) {
                		return element;
                	}
                    queue.add(element);
                }
                
            }
       
        }
		return root;
	}
	public static RoleTree searchNodeByUser(RoleTree root, String user){
		if(root == null) return null;
        Queue<RoleTree> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int l = queue.size();
            for(int i=0;i<l;i++) { 
                RoleTree node = queue.remove();
               
                for (RoleTree element : node.children) {
                	for(int item=0;item<element.userName.size();item++) {
                	if((element.userName.get(item)).equals(user)) {
                		return element;
                	}
                	}
                    queue.add(element);
                }
                
            }
       
        }
		return root;
	}
	public static void deleteNode(RoleTree root, String deleteRole, String transferRole) {
		
		if(root == null) return;
        RoleTree tr1=searchNode(root,deleteRole);
        RoleTree tr2=searchNode(root,transferRole);
        if(tr2.parent.equals(deleteRole)) {
        	tr2.parent=tr2.parent.parent;
        	if(!tr2.getChildren().isEmpty())
        	tr2.userName.addAll(tr2.parent.parent.userName);
        	tr1.parent=null;
        	tr1.children=null;
        }
        else if(!tr1.getChildren().isEmpty()) {
        	tr2.parent.children.addAll(tr1.getChildren());
        	if(!tr2.getChildren().isEmpty())
        	tr2.userName.addAll(tr1.userName);
        	for(RoleTree node:tr1.getChildren()) {
        		node.parent=tr2.parent;
        	}
        	tr1.parent.children.remove(tr1);
        	
        }
        
	}
	public static void deleteUser(RoleTree root,String user) {
		if(root == null) return;
        RoleTree deleteuserNode=searchNodeByUser(root,user);
        deleteuserNode.userName.remove(user);
	}
	public static int countUser(RoleTree root,String user) {
		if(root == null) return 0;
        RoleTree deleteuserNode=searchNodeByUser(root,user);
        int count=0;
        Queue<RoleTree> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int l = queue.size();
            
            for(int i=0;i<l;i++) { 
                RoleTree node = queue.remove();
                count++;
                for (RoleTree element : node.children) {
                	for(int item=0;item<element.userName.size();item++) {
                	if((element.userName.get(item)).equals(user)) {
                		return count;
                	}
                	}
                    queue.add(element);
                }
                
            }
       
        }
        return count;
	}
	public static int height(RoleTree root) {
		if(root==null)
			 return 1;
		else {
			if(!root.children.isEmpty()) {
				for(int i=0;i<root.getChildren().size();i++) {
					return height(root.children.get(i));
				}
			}
				
		}
		return 0;
	}
	 public static String boss(RoleTree userNode1,RoleTree userNode2) {
		 if((userNode1.parent.getVal()).equals(userNode2.parent.val)){
			 return userNode1.parent.userName.get(0);
		 }
		 else {
		      return boss(userNode1.parent,userNode2.parent);
	 }
	 }
    public static String commonBoss(RoleTree root,String user1,String user2) {
    	if(root == null) return null;
        RoleTree userNode1=searchNodeByUser(root,user1);
        RoleTree userNode2=searchNodeByUser(root,user2);
        return boss(userNode1,userNode2);
    	
    }
	public static void main(String[] args) {
		String rootValue;
		System.out.println("Enter the root name:");
		Scanner input=new Scanner(System.in);
		RoleTree root=new RoleTree(input.next());
		int choice=0;
		int operation;
		do {
			System.out.println("Operations: \n" + 
					" 1. Add Sub Role"+"\n 2. Display Roles"+"\n 3. Delete Role"+"\n 4. Add User"
					+"\n 5. Display Users"+"\n 6. Display Users and subUsers1"
					+"\n 7. Delete User"+"\n 8. Number of users from top"
					+"\n 9. Height of role hieararchy"+"\n 10. common boss of users");
			System.out.println("Operation to be performed:");
			operation=new Scanner(System.in).nextInt();
			switch(operation){
				case 1:
					
					System.out.println("Enter sub role name:");
					String subRole=new Scanner(System.in).nextLine();
					System.out.println("Enter reporting to role name:");
					String reportingRole=new Scanner(System.in).nextLine();
			       insertNode(root,new RoleTree(subRole),reportingRole);
			       break;
				case 2:
					printTree(root);
				      break;  
				case 3:
					System.out.println("Enter role to be deleted:");
					String deleteRole=new Scanner(System.in).nextLine();
					System.out.println("Enter role to be transferred:");
					String transferRole=new Scanner(System.in).nextLine();
			       deleteNode(root,deleteRole,transferRole); 
			       break;
				case 4:
					System.out.println("Enter User Name:");
					String userName=new Scanner(System.in).nextLine();
					System.out.println("Enter Role:");
					String Role=new Scanner(System.in).nextLine();
					RoleTree node=searchNode(root,Role);
					node.userName.add(userName);
					break;
				case 5:
					printUserTree(root);
				      break; 
				case 6:
				     printSubUser(root);
				     break;
				case 7:
					System.out.println("Enter User Name:");
				    userName=new Scanner(System.in).nextLine();
					 deleteUser(root,userName);
					 printSubUser(root);
				     break;
				case 8:
					System.out.println("Enter User Name:");
				    userName=new Scanner(System.in).nextLine();
					System.out.println("Number of users from top:");
					System.out.println(countUser(root,userName));
				     break;
					
				case 9:
					System.out.println(height(root));
					break;
				case 10:
					System.out.println("Enter User1:");
				    String user1=new Scanner(System.in).nextLine();
				    System.out.println("Enter User2:");
				    String user2=new Scanner(System.in).nextLine();
					System.out.println("Top most common boss:"+commonBoss(root,user1,user2));
				     break;
			}
			System.out.println("Enter 1 to continue the operations and 0 to stop:");
			choice=new Scanner(System.in).nextInt();
			
		}while(choice!=0);
		
	  }

	

	
	}
