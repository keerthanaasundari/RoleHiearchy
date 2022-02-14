package com.program.main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RoleTree {
        String val;
        List<RoleTree> children = new LinkedList<>();
        RoleTree parent;
        List<String> userName=new ArrayList<>();
        
        RoleTree(String value){
            val = value;
        }

        RoleTree(String value,List<RoleTree> child,RoleTree parentNode){
            val = value;
            children = child;
            parent=parentNode;
        }
        RoleTree(String value,RoleTree parentNode){
            val = value;
            parent=parentNode;
        }
		public String getVal() {
			return val;
		}

		public List<RoleTree> getChildren() {
			return children;
		}

		
		
        

}
