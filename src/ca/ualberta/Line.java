package ca.ualberta;

import java.util.Arrays;

class Line {
   // TODO Public fields are bad...
   public int id;
   public String op;
   public String[] args;

   public Line(String[] info) {
     id = Integer.valueOf(info[0]);
     op = info[1];
     args = Arrays.copyOfRange(info, 2, info.length);
   }

   public void print() {
     System.out.print(id);
     System.out.print(" " + op);
     for (String str : args)
       System.out.print(" " + str);
     System.out.println();
   }
}
