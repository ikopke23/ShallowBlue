class Bishop extends Piece{
  public String name;
  protected String cPos;


  Bishop(Board brd, int i, int j, boolean col){
    this.board = brd;
    this.let = i;
    this.num = j;
    this.side = col;
    this.legalMoves = new int[14][4];
    this.protects = new int[4][2];
    this.name = "White Bishop";
    if(col == false)
      this.name = "Black Bishop";
  }

  public String toString(){
    String ret = this.name+" "+this.let+" "+this.num;
    return ret;
  }

  public void display(){
    double square = (this.board.getWidth()-this.board.getX())/8;
    double centerPoint = square/2;
    if(this.side == true){
      StdDraw.picture(centerPoint+square*let,centerPoint+square*num,"images/bishopW.png", square, square);
    } else {
      StdDraw.picture(centerPoint+square*let,centerPoint+square*num,"images/bishopB.png", square, square);
    }
  }  //end display


  public void moves(){
    int[][] moves = new int[14][4];
    moveC = 0;
    int[][] protec = new int[4][2];
    int proC = 0;
    // System.out.println("Finding moves");
    // System.out.println("initial array = " + moves);
    // System.out.println("let, num = " + this.let+ ", " + this.num);

  //-------------- Diagonal NE/SW------------
  for(int n = 1; ((n+this.num) <= 7) && ((n+this.let) <= 7); n++){
    // System.out.println("going Diagonal NE n = " + n);
    if(this.board.squareIsEmpty(n+this.let, n+this.num) == true){
      moves[moveC][2] = n+this.let;
      moves[moveC][3] = n+this.num;
      moveC++;
    } else if((this.board.pieceAtPos(n+this.let,n+this.num).getSide() != this.side)){
      moves[moveC][2] = n+this.let;
      moves[moveC][3] = n+this.num;
      moveC++;
      break;
    } else {
      // System.out.println("color of checking piece + "+this.board.pieceAtPos(this.let,n).getSide()+ " result "+(this.board.pieceAtPos(this.let,n).getSide() != this.side));
      protec[proC][0] = n+this.let;
      protec[proC][1] = n+this.num;
      proC++;
      break;
    }
  }
  for(int n = 1; ((this.num-n) >= 0) && ((this.let-n) >= 0); n++){
    // System.out.println("going Diagonal SW n = " + n);
    if(this.board.squareIsEmpty(this.let-n, this.num-n) == true){
      moves[moveC][2] = this.let-n;
      moves[moveC][3] = this.num-n;
      moveC++;
    } else if((this.board.pieceAtPos(this.let-n,this.num-n).getSide() != this.side)){
      moves[moveC][2] = this.let-n;
      moves[moveC][3] = this.num-n;
      moveC++;
      break;
    } else {
      // System.out.println("color of checking piece + "+this.board.pieceAtPos(this.let,n).getSide()+ " result "+(this.board.pieceAtPos(this.let,n).getSide() != this.side));
      protec[proC][0] = n-this.let;
      protec[proC][1] = n-this.num;
      proC++;
      break;
    }
  }
  //-------------- Diagonal NW/SE------------

  for(int n = 1; ((n+this.num) <= 7) && ((this.let-n) >= 0); n++){
    // System.out.println("going Diagonal NW n = " + n);
    // System.out.println("Bishop x, y = " + (this.let-n)+", "+(this.num+n));
    // System.out.println("Square is Empty "+ this.board.squareIsEmpty(n+this.let, n+this.num));
    // this.board.printPieces();
      if(this.board.squareIsEmpty(this.let-n, n+this.num) == true){
        moves[moveC][2] = this.let-n;
        moves[moveC][3] = n+this.num;
        moveC++;
      } else if((this.board.pieceAtPos(this.let-n,n+this.num).getSide() != this.side)){
        moves[moveC][2] = this.let-n;
        moves[moveC][3] = n+this.num;
        moveC++;
        break;
      } else {
        // System.out.println("color of checking piece + "+this.board.pieceAtPos(this.let,n).getSide()+ " result "+(this.board.pieceAtPos(this.let,n).getSide() != this.side));
        protec[proC][0] = this.let-n;
        protec[proC][1] = n+this.num;
        proC++;
        break;
      }
    }

  for(int n = 1; ((this.num-n) >= 0) && ((n+this.let) <= 7); n++){
    // System.out.println("going Diagonal SE n = " + n);
    // System.out.println("x, y = " + (this.let+n)+", "+(this.num-n));
    if(this.board.squareIsEmpty(this.let+n, this.num-n) == true){
      moves[moveC][2] = this.let+n;
      moves[moveC][3] = this.num-n;
      moveC++;
    } else if((this.board.pieceAtPos(this.let+n,this.num-n).getSide() != this.side)){
      moves[moveC][2] = this.let+n;
      moves[moveC][3] = this.num-n;
      moveC++;
      break;
    } else {
      // System.out.println("color of checking piece + "+this.board.pieceAtPos(this.let,n).getSide()+ " result "+(this.board.pieceAtPos(this.let,n).getSide() != this.side));
      protec[proC][0] = n+this.let;
      protec[proC][1] = this.num-n;
      proC++;
      break;
    }
  }


  for(int j = 0; j < moveC; j++){
    moves[j][0] = this.let;
    moves[j][1] = this.num;
  }
  // System.out.println("Final Moves VVV");
  // ArrayTools.println(moves);

  this.protects = protec;
  this.legalMoves = moves;
}//end moves



  public boolean attacksSquare(int x, int y){
    if(!(this.board.squareIsEmpty(x,y))){
      if(this.board.pieceAtPos(x,y).getSide() == side){
        return false;
      }
    }




    if(!(Math.abs(this.let-x) == Math.abs(this.num-y))){
      // System.out.println("not on correct diagonal");
      return false;
    } else if((x > this.let) && (y > this.num)){
      for(int i = 1; (((i+this.num) < y) && ((i+this.let) < x)); i++){
        if(!(this.board.squareIsEmpty(i+this.let, i+this.num))){
          return false;
        }
      }
      return true;
    } else if((x > this.let) && (y < this.num)){
      for(int i = 1; (((this.num-i) > y) && ((i+this.let) < x)); i++){
        if(!(this.board.squareIsEmpty(i+this.let, this.num-i))){
          return false;
        }
      }
      return true;
    } else if((x < this.let) && (y > this.num)){
      for(int i = 1; (((i+this.num) < y) && ((this.let-i) < x)); i++){
        if(!(this.board.squareIsEmpty(this.let-i, i+this.num))){
          return false;
        }
      }
      return true;
    } else/* x < and y <  aka SW */ {
      for(int i = 1; (((this.num-i) > y) && ((i+this.let) < x)); i++){
        if(!(this.board.squareIsEmpty(this.let-i, this.num-i))){
          return false;
        }
      }
      return true;
    }
  }//end attacksSquare

  public void checkMoves(int kX, int kY, int aX, int aY){
    int[][] moves = new int[16][4];
    int moveC = 0;
    int interC = 0;
    int[][] interposeSquares = new int[8][2];
    if((aX == kX) || (aY == kY)){
      if(aX > kX){
        for(int i = 0; i < aX-kX; i++){
          interposeSquares[interC][0] = aX-i;
          interposeSquares[interC][1] = kY;
          interC++;
        }
      } else if(aX < kX) {
        for(int i = 0; i < kX-aX; i++){
          interposeSquares[interC][0] = kX+i;
          interposeSquares[interC][1] = kY;
          interC++;
        }
      } else if(aY > kY){
        for(int i = 0; i < aY-kY; i++){
          interposeSquares[interC][0] = kX;
          interposeSquares[interC][1] = kY+i;
          interC++;
        }
      } else if(aY < kY){
        for(int i = 0; i < kY-aY; i++){
          interposeSquares[interC][0] = kX;
          interposeSquares[interC][1] = kY+i;
          interC++;
        }
      } else if((aX > kX) && (aY > kY)){
        for(int i = 1; ((i+kY) <= aY) && ((i+kX) <= aX); i++){
          interposeSquares[i][0] = kX+i;
          interposeSquares[i][1] = kY+i;
          interC++;
        }
      } else if((aX < kX) && (aY < kY)){
        for(int i = 1; ((kY-i) >= aY) && ((kX-i) >= aX); i++){
          interposeSquares[i][0] = kX-i;
          interposeSquares[i][1] = kY-i;
          interC++;
        }
      } else if((aX < kX) && (aY > kY)){
        for(int i = 1; ((kY-i) <= aY) && ((i+kX) >= aX); i++){
          interposeSquares[i][0] = kX+i;
          interposeSquares[i][1] = kY-i;
          interC++;
        }
      }
      else if((aX > kX) && (aY < kY)){
       for(int i = 1; ((kY+i) >= aY) && ((kX-i) <= aX); i++){
         interposeSquares[i][0] = kX-i;
         interposeSquares[i][1] = kY+i;
         interC++;
       }
     }
    }

    for(int n = 0; (interposeSquares[n][0] != 0) || (interposeSquares[n][1] != 0); n++){
      if(attacksSquare(interposeSquares[n][0], interposeSquares[n][1])){
        moves[moveC][2] = interposeSquares[n][0];
        moves[moveC][3] = interposeSquares[n][1];
        moveC++;
      }
    }

    for(int j = 0; j < moveC; j++){
      moves[j][0] = this.let;
      moves[j][1] = this.num;
    }
    ArrayTools.println(moves);

    this.legalMoves = moves;
  }//end checkMoves


  public boolean protectsPiece(int a, int b){
    for(int i = 0; i < protects.length;i++){
      if((protects[i][0] == a) && (protects[i][1] == b)){
        return true;
      }
    }
    return false;
  }//end protects pieces

}//end class
