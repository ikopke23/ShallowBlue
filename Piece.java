import java.awt.Color;

abstract class Piece{
  protected boolean side;
  protected boolean initialPos;
  protected boolean promotable;
  protected int let; int num;
  protected int moveC;
  protected int[][] legalMoves;
  protected int[][] protects;
  protected Board board;

  // abstract public Square[] attacks();
  abstract public void display();
  abstract public void moves();
  abstract public void checkMoves(int kX, int kY, int aX, int aY);
  abstract public String toString();
  abstract public boolean attacksSquare(int x, int y);
  abstract public boolean protectsPiece(int a, int b);

  protected void setLet(int l){this.let = l;}
  protected void setNum(int n){this.num = n;}
  protected void setPos(int l, int n){this.num = n;this.let = l;}
  protected void setInitial(){this.initialPos = false;}
  protected void setPromoting(){this.promotable = true;}
  protected void displayLegal(){
    double square = (this.board.getWidth()-this.board.getX())/8;
    double centerPoint = square/2;
    StdDraw.setPenColor(new Color(0,0,0,120));
    if(board.getTurn() == this.side){
      for(int i = 0; i < legalMoves.length; i++){
        if((legalMoves[i][0] != 0) || (legalMoves[i][1] != 0) || (legalMoves[i][2] != 0) || (legalMoves[i][3] != 0)){
          StdDraw.filledSquare(legalMoves[i][2]*square+ centerPoint, legalMoves[i][3]*square+ centerPoint, (int)(centerPoint*0.66));
        }
      }
    }
  }// end displayLegal
  protected int getLet(){return this.let;}
  protected int getNum(){return this.num;}
  protected boolean getSide(){return side;}
  protected int[][] getLegalMoves(){return legalMoves;}
  protected int[][] getProtects(){return protects;}
  protected boolean getInitial(){return initialPos;}
  protected boolean getPromotable(){return promotable;}
  protected boolean isLegal(int x, int y){
    for(int i = 0; i < legalMoves.length; i++){
      if((legalMoves[i][2] == x) && (legalMoves[i][3] == y)){
        return true;
      }
    }
    return false;
  }

}//end class
