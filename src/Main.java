import java.util.Scanner;
class Main{
    public static void main(String args[]){
        Board board=new Board();
        board.setBoard();
        boolean white=true;
        while(true){
            board.printBoard();
            System.out.println(board.moveHistory);
            Scanner scanner=new Scanner(System.in);
            if(white){
                System.out.println("It is white's turn to move.");
            }else{
                System.out.println("It is black's turn to move.");
            }
            String move=scanner.nextLine();
            if(move.length()!=4){
                System.out.println("Invalid input! Must be a string of length 4.");
                continue;
            }
            char x=move.charAt(0);
            if("abcdefgh".indexOf(x)==-1){
                System.out.println("Invalid input! First character is invalid.");
                continue;
            }
            char y=move.charAt(1);
            if("12345678".indexOf(y)==-1){
                System.out.println("Invalid input! Second character is invalid.");
                continue;
            }
            char a=move.charAt(2);
            if("abcdefgh".indexOf(a)==-1){
                System.out.println("Invalid input! Third character is invalid.");
                continue;
            }
            char b=move.charAt(3);
            if("12345678".indexOf(b)==-1){
                System.out.println("Invalid input! Fourth character is invalid.");
                continue;
            }
            if(board.isEmpty(x,y)){
                System.out.println("There is no piece at ("+x+","+y+")!");
                continue;
            }
            if(!board.isEmpty(a,b) && board.getColor(a,b)==board.getColor(x,y)){
                System.out.println("There is another piece of yours at ("+x+","+y+")!");
                continue;
            }
            if(board.getColor(x,y)=='b' && white){
                System.out.println("The piece you have chosen is black. It's white's turn to move!");
                continue;
            }
            if(board.getColor(x,y)=='w' && !white){
                System.out.println("The piece you have chosen is white. It's black's turn to move!");
                continue;
            }
            if(!board.validMove(x,y,a,b)){
                System.out.println("The move you have chosen is invalid!");
                continue;
            }
            board.move(x,y,a,b);
            board.moveHistory.add(""+x+y+a+b);
            white=!white;
        }
    }
}