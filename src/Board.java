import java.util.Scanner;
import java.util.HashSet;
public class Board{
    boolean whiteLongCastle;
    boolean whiteShortCastle;
    boolean blackLongCastle;
    boolean blackShortCastle;
    String[][] board;
    HashSet<String> pastPositions;
    boolean isEmpty(char x, char y){
        return this.board[x-'a'][y-'1']=="  ";
    }
    char getColor(char x,char y){
        return this.board[x-'a'][y-'1'].charAt(0);
    }
    char getType(char x, char y){
        return this.board[x-'a'][y-'1'].charAt(1);
    }
    void setPiece(char color, char type, char x, char y){
        this.board[x-'a'][y-'1']=""+color+type;
    }
    void removePiece(char x,char y){
        this.board[x-'a'][y-'1']="  ";
    }
    void setBoard(){
        this.board=new String[8][8];
        this.setPiece('w','r','a','1');
        this.setPiece('w','n','b','1');
        this.setPiece('w','b','c','1');
        this.setPiece('w','q','d','1');
        this.setPiece('w','k','e','1');
        this.setPiece('w','b','f','1');
        this.setPiece('w','n','g','1');
        this.setPiece('w','r','h','1');
        for(char x:"abcdefgh".toCharArray()){
            this.setPiece('w','p',x,'2');
        }
        this.setPiece('b','r','a','8');
        this.setPiece('b','n','b','8');
        this.setPiece('b','b','c','8');
        this.setPiece('b','q','d','8');
        this.setPiece('b','k','e','8');
        this.setPiece('b','b','f','8');
        this.setPiece('b','n','g','8');
        this.setPiece('b','r','h','8');
        for(char x:"abcdefgh".toCharArray()){
            this.setPiece('b','p',x,'7');
        }
        for(int i=0;i<=7;i++){
            for(int j=2;j<=5;j++){
                this.board[i][j]="  ";
            }
        }
    }

    boolean validMove(char x, char y, char a, char b){
        char type=this.getType(x,y);
        char color=this.getColor(x,y);
        if (!this.isEmpty(a,b) && this.getColor(x,y)==this.getColor(a,b)){
            return false;
        }
        if(type=='n'){
            return (Math.abs(a-x)==2 && Math.abs(b-y)==1) || (Math.abs(a-x)==1 && Math.abs(b-y)==2);
        }
        if(type=='r'){
            if(x==a){
                for(int i=Math.min(b,y)+1;i<Math.max(b,y);i++){
                    if(!this.isEmpty(a,(char) i)){
                        return false;
                    }
                }
                return true;
            }else if(y==b){
                for(int i=Math.min(a,x)+1; i<Math.max(a,x);i++){
                    if(!this.isEmpty((char) i, b)){
                        return false;
                    }
                }
                return true;
            }else{
                return false;
            }
        }
        if(type=='b'){
            if(x-a==y-b){
                for(int i=Math.min(x,a)+1;i<Math.max(x,a);i++){
                    if(!this.isEmpty((char)i,(char) (i-(x-a)))){
                        return false;
                    }
                }
                return true;
            }
            if(x+y==a+b){
                for(int i=Math.min(x,a)+1;i<Math.max(x,a);i++){
                    if(!this.isEmpty((char) i,(char) (x+y-i))){
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        if(type=='q'){
            if(x==a){
                for(int i=Math.min(b,y)+1;i<Math.max(b,y);i++){
                    if(!this.isEmpty(a,(char) i)){
                        return false;
                    }
                }
                return true;
            }else if(y==b){
                for(int i=Math.min(a,x)+1; i<Math.max(a,x);i++){
                    if(!this.isEmpty((char) i, b)){
                        return false;
                    }
                }
                return true;
            }else if(x-a==y-b){
                for(int i=Math.min(x,a)+1;i<Math.max(x,a);i++){
                    if(!this.isEmpty((char)i,(char) (i-(x-a)))){
                        return false;
                    }
                }
                return true;
            }else if(x+y==a+b){
                for(int i=Math.min(x,a)+1;i<Math.max(x,a);i++){
                    if(!this.isEmpty((char) i,(char) (x+y-i))){
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        if(type=='p' && this.getColor(x,y)=='w'){
            if(x==a && b-y==1){
                return this.isEmpty(a,b);
            }
            if(x==a && b-y==2){
                return this.isEmpty(a,b) && this.isEmpty(a,(char) (b-1));
            }
            if(Math.abs(a-x)==1 && b-y==1){
                return !this.isEmpty(a,b) && this.getColor(a,b)!=this.getColor(x,y);
            }
            return false;
            //Need to implement en Passant.
        }
        if(type=='p' && this.getColor(x,y)=='b'){
            if(x==a && b-y==-1){
                return this.isEmpty(a,b);
            }
            if(x==a && b-y==-2){
                return this.isEmpty(a,b) && this.isEmpty(a,(char) (b+1));
            }
            if(Math.abs(a-x)==1 && b-y==-1){
                return !this.isEmpty(a,b) && this.getColor(a,b)!=this.getColor(x,y);
            }
            return false;
            //Need to implement en Passant.
        }
        return true;
    }

    void move(char x,char y,char a,char b){
        this.setPiece(this.getColor(x,y),this.getType(x,y),a,b);
        if(b=='8'&& this.getType(a,b)=='p'){
            Scanner scanner= new Scanner(System.in);
            System.out.println("Enter q,r,b,n for promotion to a Queen, a rook, a bishop or a knight.");
            char type=scanner.next().charAt(0);
            this.setPiece('w',type,a,b);
        }
        if(b=='1' && this.getType(a,b)=='p'){
            Scanner scanner=new Scanner(System.in);
            System.out.println("Enter q,r,b,n for promotion to a Queen, a rook, a bishop or a knight.");
            char type=scanner.next().charAt(0);
            this.setPiece('b',type,a,b);
        }
        if(x=='e' && y=='0' && this.getColor(x,y)=='w' && this.getType(x,y)=='k' && a=='g' && b=='1'){
            this.setPiece('w','r','f','1');
            this.whiteShortCastle=false;
        }
        if(x=='e' && y=='0' && this.getColor(x,y)=='w' && this.getType(x,y)=='k' && a=='c' && b=='1'){
            this.setPiece('w','r','d','1');
            this.whiteLongCastle=false;
        }
        if(x=='e' && y=='8' && this.getColor(x,y)=='b' && this.getType(x,y)=='k' && a=='g' && b=='8'){
            this.setPiece('b','r','f','8');
            this.blackShortCastle=false;
        }
        if(x=='e' && y=='8' && this.getColor(x,y)=='b' && this.getType(x,y)=='k' && a=='c' && b=='8'){
            this.setPiece('b','r','d','8');
            this.blackLongCastle=false;
        }
        this.removePiece(x,y);
    }

    void printBoard(){
        for(int i=7;i>=0;i--){
            System.out.print((i+1)+" ");
            for(int j=0;j<8;j++){
                System.out.print(this.board[j][i]+" |");
            }
            System.out.println("\n-------------------------------------------");
        }
        System.out.println("   a   b   c   d   e   f   g   h   ");
    }

    boolean whiteWins(){
        return false;
    }


    boolean blackWins(){
        return false;
    }

    boolean threeFold(){
        return false;
    }

    boolean staleMate(){
        return false;
    }

    boolean insufficientMaterial(){
        return false;
    }

}