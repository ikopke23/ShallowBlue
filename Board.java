import java.awt.Color;
import java.util.*;

class Board{
  private boolean side; // white == true, black == false used for orientation of the board
  protected ArrayList<String> hist;
  protected ArrayList<Piece> pieces;
  protected int botLeftX, botLeftY;
  protected int width;
  protected static int botX, botY;
  protected static int wid;

  protected boolean initial;
  protected boolean check;
  protected boolean isPromoting;
  protected int promX, promY;
  public String[][] position;


  Board(boolean orientation, int x, int y, int wid){
    this.side = orientation;
    this.botLeftX = x;
    this.botLeftY = y;
    this.width = wid;
    this.botX = x;
    this.botY = y;
    this.wid = wid;
    this.check = false;
    //variant
    this.hist = new ArrayList<String>();
    this.pieces = new ArrayList<Piece>();

    String[][] pos = {
    {"RW", "PW", "00", "00", "00", "00", "PB", "RB"},
    {"NW", "PW", "00", "00", "00", "00", "PB", "NB"},
    {"BW", "PW", "00", "00", "00", "00", "PB", "BB"},
    {"QW", "PW", "00", "00", "00", "00", "PB", "QB"},
    {"KW", "PW", "00", "00", "00", "00", "PB", "KB"},
    {"BW", "PW", "00", "00", "00", "00", "PB", "BB"},
    {"NW", "PW", "00", "00", "00", "00", "PB", "NB"},
    {"RW", "PW", "00", "00", "00", "00", "PB", "RB"}};

    this.position = pos;
    addPieces();
    initial = true;
    //put generate all moves here
  }//end main constructor

  public static int getSX(){return botX;}
  public static int getSY(){return botY;}
  public static int getSWidth(){return wid;}

  public int getX(){return this.botLeftX;}
  public int getY(){return this.botLeftY;}
  public int getWidth(){return this.width;}

  public void addPiece(String piece, int x,  int y, boolean side){
    if(squareIsEmpty(x,y) == true){
      if(piece == "rook"){
        pieces.add(new Rook(this, x, y, side));
        if(side== true){
          position[x][y] = "RW";
        }else {
          position[x][y] = "RB";
        }
      } else if(piece == "knight"){
        pieces.add(new Knight(this, x, y, side));
        if(side== true){
          position[x][y] = "NW";
        }else {
          position[x][y] = "NB";
        }
      } else if(piece == "bishop"){
        pieces.add(new Bishop(this, x, y, side));
        if(side== true){
          position[x][y] = "BW";
        }else {
          position[x][y] = "BB";
        }
      } else if(piece == "king"){
        pieces.add(new King(this, x, y, side));
        if(side== true){
          position[x][y] = "KW";
        }else {
          position[x][y] = "KB";
        }
      } else if(piece == "queen"){
        pieces.add(new Queen(this, x, y, side));
        if(side== true){
          position[x][y] = "QW";
        }else {
          position[x][y] = "QB";
        }
      } else if(piece == "pawn"){
        pieces.add(new Pawn(this, x, y, side));
        if(side== true){
          position[x][y] = "PW";
        }else {
          position[x][y] = "PB";
        }
      }
    }
    display();
  }//end addPiece


  public void display(){
    double square = (this.width-this.botLeftX)/8;
    double centerPoint = square/2;
    for(double i = 0; i < 8; i++){
      for(double j = 0; j < 8; j++){
        if((i%2) == 0){
          StdDraw.setPenColor(new Color(238,238,210));
          if((j%2) == 0)
            StdDraw.setPenColor(new Color(118,150,86));
        } else {
          StdDraw.setPenColor(new Color(118,150,86));
          if((j%2) == 0)
            StdDraw.setPenColor(new Color(238,238,210));
        }
        StdDraw.filledSquare(centerPoint+square*i,centerPoint+square*j, centerPoint);
      }
    }
    // System.out.println(pieces);


    if(inCheck(this.side)){
      StdDraw.setPenColor(new Color(255, 0, 0, 220));
      StdDraw.filledSquare(centerPoint+square*kingX(this.side),centerPoint+square*kingY(this.side), centerPoint);
      // findCheckMoves();
    } //else {findLegalMoves(); }
    for(int n = 0; n < pieces.size(); n++){
      pieces.get(n).display();
    }
    findLegalMoves();


    StdDraw.show();
  }//end display

  private void findLegalMoves(){
    // System.out.println("Finding Legal Moves");
    for(int n = 0; n < pieces.size(); n++){
      if(pieces.get(n).getSide() == this.side){
        pieces.get(n).moves();
      }
    }
  }//end find Legal Moves

  public void addPieces(){

    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        //
        // System.out.println("i = " + i+ " j = "+ j);
        // System.out.println("i = " + (i+1)+ " j = "+ (j+1)+" "+position[i][j]);
        boolean col = true;
        if(position[i][j].charAt(1) == 'B'){
          col = false;
        }
        if(position[i][j].charAt(0) == 'R'){
          pieces.add(new Rook(this, i, j, col));
        }else if(position[i][j].charAt(0) == 'N'){
          pieces.add(new Knight(this, i, j, col));
        }else if(position[i][j].charAt(0) == 'B'){
          pieces.add(new Bishop(this, i, j, col));
        }else if(position[i][j].charAt(0) == 'Q'){
          pieces.add(new Queen(this, i, j, col));
        }else if(position[i][j].charAt(0) == 'K'){
          pieces.add(new King(this, i, j, col));
        }else if(position[i][j].charAt(0) == 'P'){
          pieces.add(new Pawn(this, i, j, col));
        }
      }

    }//end for loop i
  }//end add pieces

  public void move(int xi, int yi, int xf, int yf){
    if(pieceAtPos(xi, yi).isLegal(xf, yf)){
      pieceAtPos(xi, yi).setInitial();
      boardChange(xi, yi, xf, yf, "no");
      side = !side;
    }
  }
  //MAKE SURE THAT CASTLING AND EN PASSONT ARE IMPLEMENTED
  public void boardChange(int xi, int yi, int xf, int yf, String spec){
    // if()
    // System.out.println("xi, yi, xf, yf = "+ xi+", "+yi+", "+xf+", "+yf);
    // System.out.println("# of pieces " + pieces.size());
      if(squareIsEmpty(xf, yf) == false)
        removePiece(xf, yf);
      // System.out.println("# of pieces " + pieces.size());
      if(pieceAtPos(xi, yi) instanceof King){
        System.out.println("Piece is instance of King -----------------");
        if((Math.abs(xf-xi) == 2)){
          pieceAtPos(xi, yi).setPos(xf, yf);
          if(pieceAtPos(xf, yf).getSide() == true){
            if(xf == 6){
              pieceAtPos(7, 0).setPos(5, 0);
            } else {
              pieceAtPos(0, 0).setPos(3, 0);
            }
          } else {
            if(xf == 6){
              pieceAtPos(7, 7).setPos(5, 7);
            } else{
              pieceAtPos(0, 7).setPos(3, 7);
            }
          }
        } else {
          pieceAtPos(xi, yi).setPos(xf, yf);
        }
      } else if(pieceAtPos(xi, yi) instanceof Pawn){
        if(yf == 7 || yf == 0){
          this.isPromoting = true;
          System.out.println("xi, yi + xf, yf" + xi +", " + yi +", + " + xf +", " + yf);
          System.out.println("isPromoting" +  isPromoting);
          pieceAtPos(xi, yi).setPromoting();
          pieceAtPos(xi, yi).setPos(xf, yf);
          this.promX = xf;
          this.promY = yf;
        } else{
          pieceAtPos(xi, yi).setPos(xf, yf);
        }
      } else {
        pieceAtPos(xi, yi).setPos(xf, yf);

      }
      // System.out.println("Post Move " +squareIsEmpty(xi, yi));
      // System.out.println("Post Move " +pieceAtPos(xi, yi));
      // printPieces();
      // System.out.println("------------------------------");
      // System.out.println("------------------------------");
      position[xf][yf] = position[xi][yi];
      position[xi][yi] = "00";

  }

  public Piece pieceAtPos(int x, int y){
    for(int i = 0; i < pieces.size(); i++){
      if((pieces.get(i).getLet() == x) && (pieces.get(i).getNum() == y))
        return pieces.get(i);
    }
    return null;
  }//end pieceAtPos

  public boolean squareIsEmpty(int i, int j){
    for(int n = 0; n < pieces.size(); n++){
      if((pieces.get(n).getLet() == i) && (pieces.get(n).getNum()) == j){
        return false;
      }
    }
    return true;
  }//end squareIsEmpty

  public void removePiece(int x, int y){
    // System.out.println("Remove Piece x = "+ x + " y = " + y);
    for(int i = 0; i < pieces.size(); i++){
      // System.out.println("pieces.get(i) x, y = "+pieces.get(i).getLet()+", "+pieces.get(i).getNum());
      // System.out.println("x, y, xf, yf ="+ x+", "+y+", "+pieces.get(i).getLet()+", "+pieces.get(i).getNum());
      if((pieces.get(i).getLet() == x) && (pieces.get(i).getNum() == y)){
        pieces.remove(i);
        break;
      }
    }
  }

  public boolean attacksSquareAll(int x, int y, boolean side){
    for(int i = 0; i < pieces.size(); i++){
     if(pieces.get(i).getSide() != side){
       // System.out.println("side = " + pieces.get(i).getSide());
       if(pieces.get(i).attacksSquare(x, y))
         return true;
       }
    }
    return false;
  }

  public boolean attacksSquare(int x, int y, int a, int b){
    if(pieceAtPos(a,b).attacksSquare(x,y)){
      return true;
    }
    return false;
  }//end attacksSquare

  public boolean pieceisPro(int a, int b, boolean side){

    for(int i = 0; i < pieces.size(); i++){
      if(pieces.get(i).getSide() != side){
        if(pieces.get(i).protectsPiece(a, b))
          return true;
      }
    }
    return false;
  }

  public void printPieces(){
   for(int i = 0; i < pieces.size(); i++)
    System.out.println(pieces.get(i));
    System.out.println("# of pieces "+ pieces.size());
  }

  public boolean inCheck(boolean side){
   int kX = kingX(side);
   int kY = kingY(side);
   // System.out.println("inCheck is Being Called ------------");
   // System.out.println("inCheck Side = " + side);

   for(int i = 0; i < pieces.size(); i++){
     // System.out.println(attacksSquare(kX, kY, pieces.get(i).getLet(),pieces.get(i).getNum()));
     if(attacksSquare(kX, kY, pieces.get(i).getLet(),pieces.get(i).getNum()) == true){
       this.check = true;
       return true;
     }
   }
   return false;
  }

  public void promote(String piece, boolean color){
    // System.out.println("promote (in board is called)");
    // System.out.println("string piece = " + piece);
    removePiece(promX,promY);
    // System.out.println("promoting x, y = "+promX+", "+promY);
    addPiece(piece, promX, promY, color);
    StdDraw.setPenColor(StdDraw.WHITE);
    StdDraw.filledRectangle(this.width+this.width/16, this.width/2, this.width/16, this.width/2);
    StdDraw.show();
    this.isPromoting = false;
  }


  public boolean getTurn(){return this.side;}
  public boolean getCheck(){return this.check;}
  public boolean getPromoting(){return this.isPromoting;}

  public int kingX(boolean side){
    for(int i = 0; i < pieces.size(); i++){
      if((pieces.get(i) instanceof King) && pieces.get(i).getSide() == side){
        return pieces.get(i).getLet();
      }
    }
    return -1;
  }//end kingX
  public int kingY(boolean side){
    for(int i = 0; i < pieces.size(); i++){
      if((pieces.get(i) instanceof King) && pieces.get(i).getSide() == side){
        return pieces.get(i).getNum();
      }
    }
    return -1;
  }//end kingY

  public boolean isCheckMate(boolean side){
  //   int kX = kingX(side);
  //   int kY = kingY(side);
  //
  //   for(int i = 0; i < pieces.size(); i++){
  //     int[][] pL = pieceAtPos(kX, kY).getLegalMoves();
  //     if(!((pL[0][2] == 0) && (pL[0][3] == 0)) || !((pL[1][2] == 0) && pkL[1][3] == 0))){
  //
  //     }else{
  //       return false;
  //     }
  //   }
    return false;
  }//end isCheckMate

}//end class
