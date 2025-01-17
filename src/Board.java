public class Board {
    Piece[][] board;
    WhitePlayer white;
    BlackPlayer black;
    // The chess board is an eight by eight 2D array; the setBoard method initializes the board.
    void setBoard() {
        this.board = new Piece[][]{{new Rook(), new Knight(), new Bishop(), new Queen(), new King(), new Bishop(), new Knight(), new Rook()},
                {new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn()},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn()},
                {new Rook(), new Knight(), new Bishop(), new Queen(), new King(), new Bishop(), new Knight(), new Rook()}
        };
        for(int j=0;j<8;j++){
            for(int i=0;i<2;i++){
                this.board[i][j].ID='w';
            }
            for (int i=6;i<8;i++){
                this.board[i][j].ID='b';
            }
        }
    }
    boolean notBlocked(int x, int y, int a, int b){
        if(this.board[a][b]!=null && this.board[a][b].ID==this.board[x][y].ID){
            return false;
        }
        if(x==a){
            for(int i=1;i<Math.abs(y-b);i++){
                if (this.board[x][Math.min(y,b)+i]!=null) {
                    return false;
                }
            }
            return true;
        }
        if(y==b){
            for(int i=1;i<Math.abs(x-a);i++){
                if(this.board[Math.min(x,a)+i][y]!=null){
                    return false;
                }
            }
            return true;
        }
        if(Math.abs(x-a)==Math.abs(y-b)){
            int dx= (x<a)? 1:-1;
            int dy= (y<b)? 1:-1;
            for(int i=1;i<Math.abs(x-a);i++){
                if(this.board[x+dx*i][y+dy*i]!=null) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    void printBoard(){
        for(int i=7;i>=0;i--){
            for (int j=0;j<8;j++){
                if(board[i][j]!=null){
                    board[i][j].printPiece();
                }else{
                    System.out.print("  |");
                }
            }
            System.out.println("\n_________________________________");
        }
    }
}
