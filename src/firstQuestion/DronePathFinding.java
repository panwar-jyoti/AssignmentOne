package firstQuestion;

import java.util.Scanner;

public class DronePathFinding {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter grid size (rows columns): ");
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        int[][] grid = new int[rows][columns];

        System.out.print("Enter target position (row column): ");
        int targetRow = scanner.nextInt();
        int targetColumn = scanner.nextInt();
        grid[targetRow][targetColumn] = 1;

        int[][] drones = {
                {0, 0}, // Drone 1
                {0, columns - 1}, // Drone 2
                {rows - 1, 0}, // Drone 3
                {rows - 1, columns - 1} // Drone 4
        };

        for (int drone = 0; drone < drones.length; drone++) {
            int[] dronePosition = drones[drone];
            System.out.println("Searching with Drone " + (drone + 1) + "...");
            if (searchTarget(grid, dronePosition, targetRow, targetColumn)) {
                System.out.println("Target found by Drone " + (drone + 1) + "!");
                break;
            }
        }
        scanner.close();
    }
    private static boolean searchTarget(int[][] grid, int[] dronePosition, int targetRow, int targetColumn) {
        int currentRow = dronePosition[0];
        int currentColumn = dronePosition[1];
        while (currentRow != targetRow || currentColumn != targetColumn) {

            if (currentRow < targetRow) {
                currentRow++;
            } else if (currentRow > targetRow) {
                currentRow--;
            }
            if (currentColumn < targetColumn) {
                currentColumn++;
            } else if (currentColumn > targetColumn) {
                currentColumn--;
            }

            System.out.println("Drone at position: (" + currentRow + ", " + currentColumn + ")");
        }
        return true;
    }
}