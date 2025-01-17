abstract class Player {
    void move(Board board, int x, int y, int a, int b){
        if(!(0<=x && x<8 && 0<=y && y<8 && 0<=a && a<8 && 0<=b && b<8)){
            System.out.println("Coordinates must be between 0 and 7!");
        }else if(!board.board[x][y].isValid(board,x,y,a,b)) {
            System.out.println("This is not a valid move!");
        }else{
                board.board[a][b]=board.board[x][y];
                board.board[x][y]=null;
        }
    }
}
class WhitePlayer extends Player{
    @Override
    void move(Board board, int x,int y,int a,int b){
        if(board.board[x][y]==null){
            System.out.println("There is no piece at ("+x+","+y+")");
        }else if(board.board[x][y].ID!='w'){
            System.out.println("This is not your piece!");
        }else{
            super.move(board,x,y,a,b);
        }
    }
}
class BlackPlayer extends Player{
    @Override
    void move(Board board, int x,int y,int a,int b){
        if(board.board[x][y]==null){
            System.out.println("There is no piece at ("+x+","+y+")");
        }else if(board.board[x][y].ID!='b'){
            System.out.println("This is not your piece!");
        }else{
            super.move(board,x,y,a,b);
        }
    }
}