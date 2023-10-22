public class Main {

    public static void main(String[] args) throws InterruptedException {

        final boolean[] validation = new boolean[]{true};


        final int[][] sudoku  ={
            {6, 2, 4, 5, 3, 9, 1, 8, 7},
            {5, 1, 9, 7, 2, 8, 6, 3, 4},
            {8, 3, 7, 6, 1, 4, 2, 9, 5},
            {1, 4, 3, 8, 6, 5, 7, 2, 9},
            {9, 5, 8, 2, 4, 7, 3, 6, 1},
            {7, 6, 2, 3, 9, 1, 4, 5, 8},
            {3, 7, 1, 9, 5, 6, 8, 4, 2},
            {4, 9, 6, 1, 8, 2, 5, 7, 3},
            {2, 8, 5, 4, 7, 3, 9, 1, 6}
        };

        Thread row_Check = new Thread(() -> {
            for (int[] arr : sudoku) {
                boolean[] values = new boolean[9];
                for (int i: arr) {
                    values[i-1] = true;
                }
                for (int i = 0; i < values.length; i++) {
                    if (!values[i]){
                        validation[0] = false;
                    }
                }
            }
            System.out.println("Thread End");
        }
        );

        Thread column_Check = new Thread(() -> {
            for(int i = 0; i < sudoku.length; i++) {
                boolean[] values = new boolean[9];
                for (int j = 0; j < 9; j++) {
                    values[ (sudoku[j][i] ) - 1 ]= true;
                }

                for (int z = 0; z < values.length; z++) {
                    if (!values[z]){
                        validation[0] = false;
                    }
                }
            }
            System.out.println("Thread End");
        }
        );

        Thread grid_Check = new Thread(() -> {
            for(int gr = 0; gr < 3; gr++) {
                for(int gc = 0; gc < 3; gc++) {
                    boolean[] values = new boolean[9];
                    int col_offset = gc * 3;
                    int row_offset = gr * 3;

                    values[ (sudoku[0 + row_offset][0 + col_offset] ) - 1 ]= true;
                    values[ (sudoku[0 + row_offset][1 + col_offset] ) - 1 ]= true;
                    values[ (sudoku[0 + row_offset][2 + col_offset] ) - 1 ]= true;

                    values[ (sudoku[1 + row_offset][0 + col_offset] ) - 1 ]= true;
                    values[ (sudoku[1 + row_offset][1 + col_offset] ) - 1 ]= true;
                    values[ (sudoku[1 + row_offset][2 + col_offset] ) - 1 ]= true;

                    values[ (sudoku[2 + row_offset][0 + col_offset] ) - 1 ]= true;
                    values[ (sudoku[2 + row_offset][1 + col_offset] ) - 1 ]= true;
                    values[ (sudoku[2 + row_offset][2 + col_offset] ) - 1 ]= true;


                    for (int z = 0; z < values.length; z++) {
                        if (!values[z]){
                            validation[0] = false;
                        }
                    }
                }


            }
            System.out.println("Thread End");
        }
        );

        row_Check.run();
        column_Check.run();
        grid_Check.run();

        row_Check.join();
        column_Check.join();
        grid_Check.join();

        String valid = "Yes, your solution to the Sudoku puzzle is valid! Good job!";
        String notvalid = "Your solution to the Sudoku puzzle is not valid! Please try again with a different solution this time.";

        String s = validation[0]? valid : notvalid;

        System.out.println(s);
}



}
