class King extends Piece{
  public String name;
  protected String cPos;


  King(Board brd, int i, int j, boolean col){
    this.board = brd;
    this.let = i;
    this.num = j;
    this.side = col;
    this.legalMoves = new int[10][4];
    this.initialPos = true;
    this.name = "White King";
    if(col == false)
      this.name = "Black King";

  }

  public String toString(){
    String ret = this.name+" "+this.let+" "+this.num;
    return ret;
  }


  public void display(){
    double square = (this.board.getWidth()-this.board.getX())/8;
    double centerPoint = square/2;
    if(this.side == true){
      StdDraw.picture(centerPoint+square*let,centerPoint+square*num,"images/kingW.png", square, square);
    } else {
      StdDraw.picture(centerPoint+square*let,centerPoint+square*num,"images/kingB.png", square, square);
    }
  }  //end display

  public void checkMoves(int kX, int kY, int aX, int aY){}

  public void moves(){
    int[][] moves = new int[10][4];
    moveC = 0;
    int[][] protecs = new int [8][2];
    int proC = 0;
    if((initialPos == true)){
      System.out.println("King Initial == true");
      if(this.side == true){
        if(!(this.board.squareIsEmpty(0,0))){
          if(this.board.pieceAtPos(0,0).getInitial() == true && this.board.squareIsEmpty(1,0) && !(this.board.attacksSquareAll(1,0, true)) && this.board.squareIsEmpty(2,0) && !(this.board.attacksSquareAll(2,0, true)) && this.board.squareIsEmpty(3,0) && !(this.board.attacksSquareAll(3,0, true))){
            moves[moveC][2] = 2;
            moves[moveC][3] = 0;
            moveC++;
          }
        }
        if(!(this.board.squareIsEmpty(7,0))){
          if(this.board.pieceAtPos(7,0).getInitial() == true && this.board.squareIsEmpty(5,0) && !(this.board.attacksSquareAll(5,0, true)) && this.board.squareIsEmpty(6,0) && !(this.board.attacksSquareAll(6,0, true))){
            moves[moveC][2] = 6;
            moves[moveC][3] = 0;
            moveC++;
          }
        }
      } else {
        if(!(this.board.squareIsEmpty(0,7))){
          if(this.board.pieceAtPos(0,7).getInitial() == true && this.board.squareIsEmpty(1,7) && !(this.board.attacksSquareAll(1, 7, false)) && this.board.squareIsEmpty(2,7) && !(this.board.attacksSquareAll(2,7, false)) && this.board.squareIsEmpty(3,7) && !(this.board.attacksSquareAll(3,7, false))){
            moves[moveC][2] = 2;
            moves[moveC][3] = 7;
            moveC++;
          }
        }
        if(!(this.board.squareIsEmpty(7,7))){
          if(this.board.pieceAtPos(7,7).getInitial() == true && this.board.squareIsEmpty(5,7) && !(this.board.attacksSquareAll(5,7, false)) && this.board.squareIsEmpty(6,7) && !(this.board.attacksSquareAll(6,7, false))){
            moves[moveC][2] = 6;
            moves[moveC][3] = 7;
            moveC++;
          }
        }
      }
    }

    // System.out.println("King Moves being called");
    for(int i = -1; i <= 1; i++){
      for(int n = -1; n <= 1; n++){
        // System.out.println("a#, i, n = " + this.let+this.num+ ", " + i + "/, " +n);
        // System.out.println(" a+i, #+n = " + (this.let+i)+(this.num+n));
        if(((i != 0) || (n != 0)) && (this.let+i > -1)&&(this.let+i < 8)&&(this.num+n > -1)&&(this.num+n < 8)){
          // System.out.println("passes Check a#, i, n = " + this.let+this.num+ ", " + i + ", " +n);
          if(!(this.board.attacksSquareAll(this.let+i, this.num+n, this.side))){
            // System.out.println("Passed attacksSquareAll at " + (this.let+i) + ", " + (this.num+n));
            // System.out.println("a#, i, n = " + this.let+this.num+ ", " + i + ", " +n);
            // System.out.println("squareIsEmpty = "+this.board.squareIsEmpty(this.let+i, this.num+n));
            if(this.board.squareIsEmpty(this.let+i, this.num+n)){
              moves[moveC][2] = this.let+i;
              moves[moveC][3] = this.num+n;
              moveC++;
              // }else if((this.board.pieceAtPos(this.let+i, this.num+n).getSide() != this.side) && (this.board.pieceisPro(this.let+1, this.num+n)){
              }else if(this.board.pieceAtPos(this.let+i, this.num+n).getSide() == this.side){
              protecs[proC][0] = this.let+i;
              protecs[proC][1] = this.num+n;
              proC++;
            } else if(this.board.pieceisPro(this.let+i, this.num+n, this.side)){
              moves[moveC][2] = this.let+i;
              moves[moveC][3] = this.num+n;
              moveC++;

            }
          }//end attacksSquare if
        }// within bounds if statement

      }
    }

    for(int j = 0; j < moveC; j++){
      moves[j][0] = this.let;
      moves[j][1] = this.num;
    }
    this.protects = protecs;
    this.legalMoves = moves;
  }//end moves

  public boolean attacksSquare(int x, int y){
    if(((Math.abs(this.let-x) == 1) && (Math.abs(this.num-y) == 1)) ||
      (((Math.abs(this.let-x) == 0) && (Math.abs(this.num-y) == 1)) || ((Math.abs(this.let-x) == 1) && (Math.abs(this.num-y) == 0)))){
      if(this.board.squareIsEmpty(x,y)){
       return true;
     } else {
       if(this.board.pieceAtPos(x,y).getSide() != side){
         return true;
       }
     }
    }
    return false;
  }

  public boolean protectsPiece(int a, int b){
    for(int i = 0; i < protects.length;i++){
      if((protects[i][0] == a) && (protects[i][1] == b)){
        return true;
      }
    }
    return false;
  }//end protects pieces


}//end king
