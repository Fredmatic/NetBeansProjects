

package vu.gradesystem;

import java.util.Scanner;

public class GradeSystem {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter students's score: ");
        int score = input.nextInt();
        int grade = 0;
        String remarks = "";
        
        if (score >=80 && score <= 100){
            grade = 1; remarks = "D1";
        } else if (score >=75){
            grade = 2; remarks = "D2";
        } else if (score >= 66){
            grade = 3; remarks = "C3";
        } else if (score >= 60){
            grade = 4; remarks = "C4";
        }else if (score >=50){
            grade = 5; remarks = "C5";
        } else if (score >= 45){
            grade = 6; remarks = "C6";
        } else if (score >=35){
            grade = 7; remarks = "P7";
        } else if (score >=30){
            grade = 8; remarks = "P8";
        } else if (score >=0){
            grade = 9; remarks = "F";
        }
        System.out.println("Score: " +score);
        System.out.println("Grade: " + grade);
        System.out.println("Remarks: " +remarks);
        
    }
}