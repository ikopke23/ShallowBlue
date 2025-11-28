class Queen extends Piece{
  public String name;
  protected String cPos;


  Queen(Board brd, int i, int j, boolean col){
    this.board = brd;
    this.let = i;
    this.num = j;
    this.side = col;
    this.legalMoves = new int[28][4];
    this.name = "White Queen";
    if(col == false)
      this.name = "Black Queen";
  }

  public String toString(){
    String ret = this.name+" "+this.let+" "+this.num;
    return ret;
  }

  public void moves(){
    int[][] moves = new int[28][4];
    moveC = 0;
    int[][] protecs = new int[8][2];
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
      //      System.out.println("going left i = " + i);
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
        protecs[proC][0] = n+this.let;
        protecs[proC][1] = n+this.num;
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
        protecs[proC][0] = this.let-n;
        protecs[proC][1] = this.num-n;
        proC++;
        break;
      }
    }
    //-------------- Diagonal NW/SE------------
    for(int n = 1; ((n+this.num) <= 7) && ((this.let-n) <= 7); n++){
      // System.out.println("going Diagonal NE n = " + n);
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
        protecs[proC][0] = this.let-n;
        protecs[proC][1] = n+this.num;
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
        protecs[proC][0] = n+this.let;
        protecs[proC][1] = this.num-n;
        proC++;
        break;
      }
    }


    for(int j = 0; j < moveC; j++){
      moves[j][0] = this.let;
      moves[j][1] = this.num;
    }
    ArrayTools.println(moves);

    this.protects = protecs;
    this.legalMoves = moves;
  }//end moves

  //kX, kY is King square, aX, aY is attacking Piece
  public void checkMoves(int kX, int kY, int aX, int aY){
    //int[][] moves = new int[28][4];
    // moveC = 0;
    //
    // if((kY == aY) && (kX > aX)){
    //
    // }
    //
    //
    // for(int j = 0; j < moveC; j++){
    //   moves[j][0] = this.let;
    //   moves[j][1] = this.num;
    // }
    // ArrayTools.println(moves);
    //
    // this.legalMoves = moves;
  }//end checkMoves



  public boolean attacksSquare(int x, int y){
    if(!(this.board.squareIsEmpty(x,y))){
      if(this.board.pieceAtPos(x,y).getSide() == side){
        return false;
      }
    }
    //Taken From rook horizontal and vertical
     if(x == this.let){
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
    } else if(y == this.num){
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
      //Taken from Bishop both diagonals
    else if(!(Math.abs(this.let-x) == Math.abs(this.num-y))){
      // System.out.println("not on correct diagonal");
      return false;
    } else if((x > this.let) && (y > this.num)){
      for(int i = 1; (((i+this.num) < y) && ((i+this.let) < x)); i++){
        // System.out.println("let, num, x, y, i " + this.let+", "+this.num+", "+x+", "+y+", "+i);
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
      for(int i = 1; (((this.num-i) > y) && ((this.let-i) > x)); i++){
        if(!(this.board.squareIsEmpty(this.let-i, this.num-i))){
          return false;
        }
      }
      return true;
    }

  }


  public void display(){
    double square = (this.board.getWidth()-this.board.getX())/8;
    double centerPoint = square/2;
    if(this.side == true){
      StdDraw.picture(centerPoint+square*let,centerPoint+square*num,"images/queenW.png", square, square);
    } else {
      StdDraw.picture(centerPoint+square*let,centerPoint+square*num,"images/queenB.png", square, square);
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
