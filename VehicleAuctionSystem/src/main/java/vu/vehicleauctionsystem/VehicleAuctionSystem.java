package vu.vehicleauctionsystem;

import java.util.Scanner;

public class VehicleAuctionSystem {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Vehicle registration: ");
        String reg = input.nextLine();
        System.out.println("Enter Vehicle cost: ");
        double cost = input.nextDouble();
        
        System.out.println("Enter expenses: ");
        double expenses = input.nextDouble();
        
       double highest =0;
        for (int i = 1; i <=3; i++) {
            System.out.println("Enter bid " + i + ": ");
            double bid = input.nextDouble();
            
            if (bid > highest){
                highest = bid;
            }
        }
        double balance = highest -(cost+expenses);
        
        System.out.println("/nVehicle: "+reg);
        System.out.println("Highest Bid: "+ highest);
        
        if (balance >=0) {
            System.out.println("Profit: " +balance);
            
        } else{
            System.out.println("Loss: " +Math.abs(balance));
        }
        
    }
    
}

