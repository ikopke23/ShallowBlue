class Knight extends Piece{
  public String name;
  protected String cPos;


  Knight(Board brd, int i, int j, boolean col){
    this.board = brd;
    this.let = i;
    this.num = j;
    this.side = col;
    this.legalMoves = new int[8][4];
    this.protects = new int[8][2];
    this.name = "White Knight";
    if(col == false)
      this.name = "Black Knight";
  }

  public String toString(){
    String ret = this.name+" "+this.let+" "+this.num;
    return ret;
  }

  public void display(){
    double square = (this.board.getWidth()-this.board.getX())/8;
    double centerPoint = square/2;
    if(this.side == true){
      StdDraw.picture(centerPoint+square*let,centerPoint+square*num,"images/knightW.png", square, square);
    } else {
      StdDraw.picture(centerPoint+square*let,centerPoint+square*num,"images/knightB.png", square, square);
    }
  }  //end display

  public void moves(){
    int[][] moves = new int[8][4];
    int[][] protecs = new int[8][2];
    int proC = 0;
    int moveC = 0;
    // System.out.println("Finding moves");
    // System.out.println("initial array = " + moves);
    // System.out.println("let, num = " + this.let+ ", " + this.num);
    //all vertical moves

    if((this.let+2 < 8) && (this.num+1 < 8)){
      if(this.board.squareIsEmpty(this.let+2, this.num+1)){
        moves[moveC][2] = this.let+2;
        moves[moveC][3] = this.num+1;
        moveC++;
      } else if(this.board.pieceAtPos(this.let+2,this.num+1).getSide() != side){
        moves[moveC][2] = this.let+2;
        moves[moveC][3] = this.num+1;
        moveC++;
      } else {
        protecs[proC][0] = this.let+2;
        protecs[proC][1] = this.num+1;
        proC++;
      }
    }
    if((this.let+2 < 8) && (this.num-1 > -1)){
      if(this.board.squareIsEmpty(this.let+2, this.num-1)){
        moves[moveC][2] = this.let+2;
        moves[moveC][3] = this.num-1;
        moveC++;
      } else if(this.board.pieceAtPos(this.let+2,this.num-1).getSide() != side){
        moves[moveC][2] = this.let+2;
        moves[moveC][3] = this.num-1;
        moveC++;
      }else {
        protecs[proC][0] = this.let+2;
        protecs[proC][1] = this.num-1;
        proC++;
      }
    }
    if((this.let-2 > -1) && (this.num+1 < 8)){
      if(this.board.squareIsEmpty(this.let-2, this.num+1)){
        moves[moveC][2] = this.let-2;
        moves[moveC][3] = this.num+1;
        moveC++;
      } else if(this.board.pieceAtPos(this.let-2,this.num+1).getSide() != side){
        moves[moveC][2] = this.let-2;
        moves[moveC][3] = this.num+1;
        moveC++;
      } else {
        protecs[proC][0] = this.let-2;
        protecs[proC][1] = this.num+1;
        proC++;
      }
    }
    if((this.let-2 > -1) && (this.num-1 > -1)){
      if(this.board.squareIsEmpty(this.let-2, this.num-1)){
        moves[moveC][2] = this.let-2;
        moves[moveC][3] = this.num-1;
        moveC++;
      } else if(this.board.pieceAtPos(this.let-2,this.num-1).getSide() != side){
        moves[moveC][2] = this.let-2;
        moves[moveC][3] = this.num-1;
        moveC++;
      } else {
        protecs[proC][0] = this.let-2;
        protecs[proC][1] = this.num-1;
        proC++;
      }
    }
    if((this.let+1 < 8) && (this.num+2 < 8)){
      if(this.board.squareIsEmpty(this.let+1, this.num+2)){
        moves[moveC][2] = this.let+1;
        moves[moveC][3] = this.num+2;
        moveC++;
      } else if(this.board.pieceAtPos(this.let+1,this.num+2).getSide() != side){
        moves[moveC][2] = this.let+1;
        moves[moveC][3] = this.num+2;
        moveC++;
      } else {
        protecs[proC][0] = this.let+1;
        protecs[proC][1] = this.num+2;
        proC++;
      }
    }
    if((this.let+1 < 8) && (this.num-2 > -1)){
      if(this.board.squareIsEmpty(this.let+1, this.num-2)){
        moves[moveC][2] = this.let+1;
        moves[moveC][3] = this.num-2;
        moveC++;
      } else if(this.board.pieceAtPos(this.let+1,this.num-2).getSide() != side){
        moves[moveC][2] = this.let+1;
        moves[moveC][3] = this.num-2;
        moveC++;
      } else {
        protecs[proC][0] = this.let+1;
        protecs[proC][1] = this.num+2;
        proC++;
      }
    }
    if((this.let-1 > -1) && (this.num+2 < 8)){
      if(this.board.squareIsEmpty(this.let-1, this.num+2)){
        moves[moveC][2] = this.let-1;
        moves[moveC][3] = this.num+2;
        moveC++;
      } else if(this.board.pieceAtPos(this.let-1,this.num+2).getSide() != side){
        moves[moveC][2] = this.let-1;
        moves[moveC][3] = this.num+2;
        moveC++;
      }else {
        protecs[proC][0] = this.let-1;
        protecs[proC][1] = this.num+2;
        proC++;
      }
    }
    if((this.let-1 > -1) && (this.num-2 > -1)){
      if(this.board.squareIsEmpty(this.let-1, this.num-2)){
        moves[moveC][2] = this.let-1;
        moves[moveC][3] = this.num-2;
        moveC++;
      } else if(this.board.pieceAtPos(this.let-1,this.num-2).getSide() != side){
        moves[moveC][2] = this.let-1;
        moves[moveC][3] = this.num-2;
        moveC++;
      } else {
        protecs[proC][0] = this.let-1;
        protecs[proC][1] = this.num-2;
        proC++;
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
    if(((Math.abs(this.num-y) == 1.0) && (Math.abs(this.let-x) == 2.0)) || (Math.abs(this.num-y) == 2.0) && (Math.abs(this.let-x) == 1)){
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

  public void checkMoves(int kX, int kY, int aX, int aY){
    

  }

  public boolean protectsPiece(int a, int b){
    for(int i = 0; i < protects.length;i++){
      if((protects[i][0] == a) && (protects[i][1] == b)){
        return true;
      }
    }
    return false;
  }//end protects pieces



}//end class
