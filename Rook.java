class Rook extends Piece{
  public String name;
  protected String cPos;


  Rook(Board brd, int i, int j, boolean col){
    this.board = brd;
    this.let = i;
    this.num = j;
    this.side = col;
    this.legalMoves = new int[14][4];
    this.protects = new int[4][2];
    this.initialPos = true;
    this.name = "White Rook";
    if(col == false)
      this.name = "Black King";


  }

  public String toString(){
    String ret = this.name+" "+this.let+" "+this.num;
    return ret;
  }


  public void moves(){
    int[][] moves = new int[14][4];
    int moveC = 0;
    int[][] protecs = new int[4][2];
    int proC = 0;
    // System.out.println("Finding moves");
    // System.out.println("initial array = " + moves);
    // System.out.println("let, num = " + this.let+ ", " + this.num);
    //--------------- Horizontal ----------
    for(int i = this.let+1; i <= 7; i++){
      // System.out.println("going right i = " + i);
      if(this.board.squareIsEmpty(i, this.num) == true){
        moves[moveC][2] = i;
        moves[moveC][3] = this.num;
        moveC++;
      } else if((this.board.pieceAtPos(i, this.num).getSide() != this.side)){
        moves[moveC][2] = i;
        moves[moveC][3] = this.num;
        moveC++;
        break;
      } else {
        protecs[proC][0] = i;
        protecs[proC][1] = this.num;
        proC++;
        break;
      }
    }

    for(int i = this.let-1; i >= 0; i--){
      // System.out.println("going left i = " + i);
      if(this.board.squareIsEmpty(i, this.num) == true){
        moves[moveC][2] = i;
        moves[moveC][3] = this.num;
        moveC++;
      } else if((this.board.pieceAtPos(i, this.num).getSide() != this.side)){
        moves[moveC][2] = i;
        moves[moveC][3] = this.num;
        moveC++;
        break;
      } else {
        protecs[proC][0] = i;
        protecs[proC][1] = this.num;
        proC++;
        break;
      }
    }
    //-------------- Vertical------------
    for(int n = this.num+1; n <= 7; n++){
      // System.out.println("going up n = " + n);
      if(this.board.squareIsEmpty(this.let, n) == true){
        moves[moveC][2] = this.let;
        moves[moveC][3] = n;
        moveC++;
      } else if((this.board.pieceAtPos(this.let,n).getSide() != this.side)){
        moves[moveC][2] = this.let;
        moves[moveC][3] = n;
        moveC++;
        break;
      } else {
        // System.out.println("color of checking piece + "+this.board.pieceAtPos(this.let,n).getSide()+ " result "+(this.board.pieceAtPos(this.let,n).getSide() != this.side));
        protecs[proC][0] = this.let;
        protecs[proC][1] = n;
        proC++;
        break;
      }
    }

    for(int n = this.num-1; n >= 0; n--){
      // System.out.println("going down n = " + n);
      if(this.board.squareIsEmpty(this.let, n) == true){
        moves[moveC][2] = this.let;
        moves[moveC][3] = n;
        moveC++;
      } else if((this.board.pieceAtPos(this.let,n).getSide() != this.side)){
        moves[moveC][2] = this.let;
        moves[moveC][3] = n;
        moveC++;
        break;
      } else {
        protecs[proC][0] = this.let;
        protecs[proC][1] = n;
        proC++;
        break;
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
    if(!(this.board.squareIsEmpty(x,y))){
      if(this.board.pieceAtPos(x,y).getSide() == side){
        return false;
      }
    }

    if((x != this.let) && (y != this.num)){
      return false;
    } else if(x == this.let){
      if(y > this.num){
        for(int i = 1; i < y-this.num; i++){
          if(!(this.board.squareIsEmpty(this.let, this.num+i))){
            return false;
          }
        }
        return true;
      } else {
        for(int i = 1; i < this.num-y; i++){
          // System.out.println("let, num, x, y, i " + this.let+", "+this.num+", "+x+", "+y+", "+i);
          if(!(this.board.squareIsEmpty(this.let, this.num-i))){
            // System.out.println("returning false + let, num, x, y, i" + this.let+", "+this.num+", "+x+", "+y+", "+i);
            return false;
          }
        }
        return true;
      }
    } else {
      if(x > this.let){
        for(int i = 1; i < x-this.let; i++){
          if(!(this.board.squareIsEmpty(this.let+i, this.num))){
            return false;
          }
        }
        return true;
      } else {
        for(int i = 1; i < this.let-x; i++){
          if(!(this.board.squareIsEmpty(this.let-i, this.num))){
            return false;
          }
        }
        return true;
      }
    }
  }

  //kX, kY is King square, aX, aY is attacking Piece
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


  public void display(){
    double square = (this.board.getWidth()-this.board.getX())/8;
    double centerPoint = square/2;
    if(this.side == true){
      StdDraw.picture(centerPoint+square*let,centerPoint+square*num,"images/rookW.png", square, square);
    } else {
      StdDraw.picture(centerPoint+square*let,centerPoint+square*num,"images/rookB.png", square, square);
    }
  }  //end display

  public boolean protectsPiece(int a, int b){
    for(int i = 0; i < protects.length;i++){
      if((protects[i][0] == a) && (protects[i][1] == b)){
        return true;
      }
    }
    return false;
  }//end protects pieces


}//end class
