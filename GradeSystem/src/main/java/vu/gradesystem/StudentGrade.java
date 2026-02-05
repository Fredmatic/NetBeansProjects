
package vu.gradesystem;

import java.util.Scanner;

public class StudentGrade {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int count = 1;
        //counters for grade 1-9
        int [] gradeCount = new int[10];
        while (count <= 5) {
            System.out.println("Enter score for students " + count + ": ");
            int score = input.nextInt();
            
            int grade = 0;
            String remark = "";
            
            if (score >= 80){ grade =1; remark = "D1";}
            else if (score >= 75) { grade = 2; remark="D2";}
            else if (score >=66) { grade = 3; remark ="C3";}
            else if (score>=60) { grade = 4; remark ="C4";}
            else if (score>=50){ grade = 5; remark = "C5";}
            else if (score >=45){ grade = 6; remark = "C6";}
            else if (score >=35){ grade = 7; remark = "P7";}
            else if (score >=30){ grade = 8; remark = "P8";}
            else {grade = 9; remark = "F";}
            gradeCount[grade]++;
            
            System.out.println("Grade: " +grade+ " Remark: " +remark);
            System.out.println();
             count++;
            }
        System.out.println("SUMMARY");
        for (int i=1; i <=9; i++){
            System.out.println("Grade " +i + ": " +gradeCount[i]+ " student(s)");
        }
    }
    
}
