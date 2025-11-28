class Pawn extends Piece{
  public String name;

  protected String cPos;


  Pawn(Board brd, int i, int j, boolean col){
    this.board = brd;
    this.initialPos = true;
    this.promotable = false;
    this.let = i;
    this.num = j;
    this.side = col;
    this.legalMoves = new int[4][4];
    this.protects = new int[2][2];
    this.name = "White Pawn";
    if(col == false)
      this.name = "Black Pawn";
  }

  public String toString(){
    String ret = this.name+" "+this.let+" "+this.num;
    return ret;
  }

  public void display(){
    double square = (this.board.getWidth()-this.board.getX())/8;
    double centerPoint = square/2;
    if(this.side == true){
      StdDraw.picture(centerPoint+square*let,centerPoint+square*num,"images/pawnW.png", square, square);
    } else {
      StdDraw.picture(centerPoint+square*let,centerPoint+square*num,"images/pawnB.png", square, square);
    }
  }  //end display

  public void moves(){
    int[][] moves = new int[4][4];
    moveC = 0;
    int[][] protecs = new int[2][2];
    // System.out.println("Finding moves");
    // System.out.println("Side = " + this.side);
    // System.out.println("initial array = " + moves);
    // System.out.println("Pawn at let, num = " + this.let+ ", " + this.num);
    if(side == true){
      if(this.num+1 < 8){
        if(this.board.squareIsEmpty(this.let, this.num+1) == true){
          moves[moveC][2] = this.let;
          moves[moveC][3] = this.num+1;
          moveC++;
        }
      }

      if(initialPos == true && ((this.board.squareIsEmpty(this.let, this.num+1) == true) && (this.board.squareIsEmpty(this.let, this.num+2) == true))){
        moves[moveC][2] = this.let;
        moves[moveC][3] = this.num+2;
        moveC++;
      }

      if(this.let-1 > -1){
        if(this.board.squareIsEmpty(this.let-1, this.num+1) == false)
         if(this.board.pieceAtPos(this.let-1,this.num+1).getSide() != side){
            moves[moveC][2] = this.let-1;
            moves[moveC][3] = this.num+1;
            moveC++;
        } else {
          protecs[0][0] = this.let-1;
          protecs[0][1] = this.num+1;
        }
      }
      if(this.let+1 < 8){
        if(this.board.squareIsEmpty(this.let+1, this.num+1) == false)
         if(this.board.pieceAtPos(this.let+1,this.num+1).getSide() != side){
            moves[moveC][2] = this.let+1;
            moves[moveC][3] = this.num+1;
            moveC++;
          } else {
            protecs[0][0] = this.let+1;
            protecs[0][1] = this.num+1;
          }
      }
    }else{
      // System.out.println("CHecking Black Pawn");
      if(this.num-1 > -1){
        if(this.board.squareIsEmpty(this.let, this.num-1) == true){
          moves[moveC][2] = this.let;
          moves[moveC][3] = this.num-1;
          moveC++;
        }
      }

      if(initialPos == true && ((this.board.squareIsEmpty(this.let, this.num-1) == true) && (this.board.squareIsEmpty(this.let, this.num-2) == true))){
        moves[moveC][2] = this.let;
        moves[moveC][3] = this.num-2;
        moveC++;
      }

      if(this.let-1 > -1){
        if(this.board.squareIsEmpty(this.let-1, this.num-1) == false)
         if(this.board.pieceAtPos(this.let-1,this.num-1).getSide() != side){
            moves[moveC][2] = this.let-1;
            moves[moveC][3] = this.num-1;
            moveC++;
          } else {
            protecs[0][0] = this.let-1;
            protecs[0][1] = this.num-1;
          }
      }
      if(this.let+1 < 8){
        if(this.board.squareIsEmpty(this.let+1, this.num-1) == false)
         if(this.board.pieceAtPos(this.let+1,this.num-1).getSide() != side){
            moves[moveC][2] = this.let+1;
            moves[moveC][3] = this.num-1;
            moveC++;
          } else {
            protecs[0][0] = this.let+1;
            protecs[0][1] = this.num-1;
          }
      }
    }


    for(int j = 0; j < moveC; j++){
      moves[j][0] = this.let;
      moves[j][1] = this.num;
    }

    this.protects = protecs;
    this.legalMoves = moves;
    // ArrayTools.println(moves);
  }//end moves

  public boolean attacksSquare(int x, int y){
    if(!(this.board.squareIsEmpty(x,y))){
      if(this.board.pieceAtPos(x,y).getSide() == side){
        return false;
      }
    }

    if(side == true){
      if((Math.abs(this.let-x) == 1.0) && (y == this.num+1)){
        return true;
      }
    }else{
      if((Math.abs(this.let-x) == 1.0) && (y == this.num-1)){
        return true;
      }
    }
    return false;
  }



  public void checkMoves(int kX, int kY, int aX, int aY){}

    public boolean protectsPiece(int a, int b){
      for(int i = 0; i < protects.length;i++){
        if((protects[i][0] == a) && (protects[i][1] == b)){
          return true;
        }
      }
      return false;
    }//end protects pieces


}//end class
