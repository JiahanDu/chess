abstract class Piece{
    // The class field ID can be 'w' for white pieces, or 'b' for black pieces;
    char ID;
    // The method isValid assumes there is a piece at board[x][y] and checks if the move from (x,y) to (a,b) is valid.
    abstract boolean isValid(Board board,int x, int y, int a, int b);
    abstract void printPiece();
}

class Knight extends Piece{
    @Override
    boolean isValid(Board board,int x,int y, int a, int b){
        return board.notBlocked(x,y,a,b) && (Math.abs(x-a)==1 && Math.abs(y-b)==2) || (Math.abs(x-a)==2 && Math.abs(y-b)==1);
    }
    @Override
    void printPiece(){
        System.out.print(this.ID+"N|");
    }
}
class Bishop extends Piece{
    @Override
    boolean isValid(Board board,int x,int y, int a, int b){
        return board.notBlocked(x,y,a,b) &&  Math.abs(x-a)==Math.abs(y-b);
    }
    @Override
    void printPiece(){
        System.out.print(this.ID+"B|");
    }
}
class King extends Piece{
    boolean hasMoved=false;
    @Override
    boolean isValid(Board board,int x, int y,int a, int b){
        return board.notBlocked(x,y,a,b) && Math.abs(x-a)<=1 && Math.abs(y-b)<=1;
    }
    @Override
    void printPiece(){
        System.out.print(this.ID+"K|");
    }
}
class Queen extends Piece{
    @Override
    boolean isValid(Board board,int x,int y,int a,int b){
        return board.notBlocked(x,y,a,b) && (Math.abs(x-a)==Math.abs(y-b) || x==a || y==b);
    }
    @Override
    void printPiece(){
        System.out.print(this.ID+"Q|");
    }
}
class Pawn extends Piece{
    // En Passant and promotion of pawns are not implemented here.
    @Override
    boolean isValid(Board board,int x,int y,int a, int b){
        if(board.board[x][y].ID=='w'){
            return (b==y && a==x+1 && board.board[a][b]==null) ||
                    (b==y && x==1 && a==3 && board.board[2][b]==null && board.board[3][b]==null) ||
                    (b==y+1 && a==x+1 && board.board[a][b]!=null && board.board[a][b].ID!=board.board[x][y].ID)||
                    (b==y-1 && a==x+1 && board.board[a][b]!=null && board.board[a][b].ID!=board.board[x][y].ID);
        }else{
            return (b==y && a==x-1 && board.board[a][b]==null) ||
                    (b==y && x==6 && a==4 && board.board[5][b]==null && board.board[4][b]==null) ||
                    (b==y+1 && a==x-1 && board.board[a][b]!=null && board.board[a][b].ID!=board.board[x][y].ID)||
                    (b==y-1 && a==x-1 && board.board[a][b]!=null && board.board[a][b].ID!=board.board[x][y].ID);
        }
    }
    @Override
    void printPiece(){
        System.out.print(this.ID+"P|");
    }
}

class Rook extends Piece{
    boolean hasMoved=false;
    @Override
    boolean isValid(Board board, int x,int y, int a,int b){
        return board.notBlocked(x,y,a,b) && ((x==a)||(y==b));
    }
    @Override
    void printPiece(){
        System.out.print(this.ID+"R|");
    }
}



