import java.util.Scanner;
public class Main {
    public static void main(String args[]){
        Board board=new Board();
        board.setBoard();
        Player white=new WhitePlayer();
        Player black=new BlackPlayer();
        int moveNum=0;
        board.printBoard();
        while(true){
            Scanner scanner=new Scanner(System.in);
            if(moveNum%2==0){
                System.out.println("It's white to move!");
            }else{
                System.out.println("It's black to move!");
            }
            String move=scanner.nextLine();
            int x=move.charAt(0)-'0';
            int y=move.charAt(1)-'0';
            int z=move.charAt(2)-'0';
            int w=move.charAt(3)-'0';
            if(moveNum%2==0){
                white.move(board,x,y,z,w);
            }else{
                black.move(board,x,y,z,w);
            }
            board.printBoard();
            moveNum+=1;
        }
    }
}
