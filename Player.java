import java.awt.Color;

class Player{
  public static int boardSize = 1000;
  public static Board board = new Board(true, 0, 0, boardSize);
  public static double square = (Board.getSWidth()-Board.getSX())/8;

  public static void main(String[] args){
    StdDraw.setCanvasSize((int)(boardSize+square),boardSize);
    StdDraw.setXscale(0,(int)(boardSize+square));
    StdDraw.setYscale(0,boardSize);
    StdDraw.enableDoubleBuffering();
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.line(boardSize, 0, boardSize, boardSize);
    // tests();

    board.display();

    // ArrayTools.println(board.pieceAtPos(6,2).getLegalMoves());
    // System.out.println("");
    // System.out.println("");
    // ArrayTools.println(board.pieceAtPos(7,3).getLegalMoves());
    double clickX = 0;
    double clickY = 0;
    int clickPosX = 0;
    int clickPosY = 0;
    int lastPosX = 0;
    int lastPosY = 0;
    boolean moving = false;

    while(true){
      if(board.getPromoting()){
        promoting(!board.getTurn());
      }

      if(StdDraw.mousePressed()){
        clickPosX = (int)(Math.floor(StdDraw.mouseX()/square));//x position
        clickPosY = (int)(Math.floor(StdDraw.mouseY()/square));//y position
        clickX = clickPosX*square+square/2;
        clickY = clickPosY*square+square/2;

        System.out.println("Mouse Clicked");
        if(board.squareIsEmpty(clickPosX, clickPosY) == false)
          System.out.println(board.pieceAtPos(clickPosX, clickPosY).toString()+"");
        System.out.println("Moving variable = " + moving);
        System.out.println("xpos = "+ clickPosX+ " ypos = "+ clickPosY);
        System.out.println("last xpos = "+ lastPosX+ " last ypos = "+ lastPosY);
        if((board.squareIsEmpty(clickPosX, clickPosY) == true) && ((moving == false))){
          StdDraw.setPenColor(new Color(120,0,0,100));
          StdDraw.filledSquare(clickX, clickY, square/2);
        }else if(moving == true && ((clickPosX != lastPosX) || (clickPosY != lastPosY))/*&& isLegal(lastPosX, lastPosY, ClickPosX, ClickPosY)*/){
          //put move method here
          System.out.println("moving xpos = "+ clickPosX+ " ypos = "+ clickPosY);
          board.move(lastPosX, lastPosY, clickPosX, clickPosY);
          board.display();
          lastPosX = 0;
          lastPosY = 0;
          moving = false;
        } else {
          if(board.getTurn() == board.pieceAtPos(clickPosX, clickPosY).getSide()){
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.circle(clickX, clickY, clickX%square);
            board.pieceAtPos(clickPosX, clickPosY).displayLegal();
            moving = true;
          }
        }
        lastPosX = clickPosX;
        lastPosY = clickPosY;
      }

      StdDraw.pause(200);
      StdDraw.show();


    }//end while
  }//end main

  public static void promoting(boolean side){
    boolean pro = true;
    System.out.println("player Promoting is called");
    System.out.println("side = " + side);
    while(true){
      if(side == true){
        StdDraw.picture(square/2+square*8,square/2+square*7,"images/queenW.png", square, square);
        StdDraw.picture(square/2+square*8,square/2+square*6,"images/rookW.png", square, square);
        StdDraw.picture(square/2+square*8,square/2+square*5,"images/bishopW.png", square, square);
        StdDraw.picture(square/2+square*8,square/2+square*4,"images/knightW.png", square, square);
      } else {
        StdDraw.picture(square/2+square*8,square/2+square*0,"images/queenB.png", square, square);
        StdDraw.picture(square/2+square*8,square/2+square*1,"images/rookB.png", square, square);
        StdDraw.picture(square/2+square*8,square/2+square*2,"images/bishopB.png", square, square);
        StdDraw.picture(square/2+square*8,square/2+square*3,"images/knightB.png", square, square);
      }
      StdDraw.show();
      // System.out.println("in while loop of player promoting");

      if(StdDraw.mousePressed()){
        int clickPosX = (int)(Math.floor(StdDraw.mouseX()/square));//x position
        int clickPosY = (int)(Math.floor(StdDraw.mouseY()/square));//y position
        double clickX = clickPosX*square+square/2;
        double clickY = clickPosY*square+square/2;

        if(clickPosX == 8){
          if(side == true){
            if(clickPosY == 7){
              board.promote("queen", side);
              break;
            } else if(clickPosY == 6){
              board.promote("rook", side);
              break;
            } else if(clickPosY == 5){
              board.promote("bishop", side);
              break;
            } else if(clickPosY == 4){
              board.promote("knight", side);
              break;
            }
          } else {
            if(clickPosY == 0){
              board.promote("queen", side);
              break;
            } else if(clickPosY == 1){
              board.promote("rook", side);
              break;
            } else if(clickPosY == 2){
              board.promote("bishop", side);
              break;
            } else if(clickPosY == 3){
              board.promote("knight", side);
              break;
            }
          }//end color test
        }//end x req
      }

    }//end while
  }

  public static void tests(){
    // board.addPiece("queen", 4, 4, true);
    // board.removePiece(5,6);
    // board.addPiece("pawn", 6, 4, false);
    // board.addPiece("pawn", 4, 3, false);
    // board.addPiece("pawn", 7, 3, false);
    // board.addPiece("pawn", 5, 3, true);
    // board.addPiece("pawn", 6, 2, true);
    // board.addPiece("pawn", 6, 3, true);
    // board.removePiece(4, 6);
    // board.addPiece("knight", 6, 4, true);
    // board.removePiece(4, 0);
    // board.addPiece("king", 3, 3, true);
    // board.addPiece("queen", 7, 3, false);
    // board.addPiece("queen", 5, 5, false);
    // board.addPiece("bishop", 5, 2, true);
    // board.addPiece("rook", 4, 2, true);
    // board.pieceAtPos(5,2).checkMoves(3,3, 7,3);
    // System.out.println("Rook Moves here  ------");
    // board.pieceAtPos(4,2).checkMoves(3,3, 7,3);
    board.removePiece(6, 1);
    board.addPiece("pawn", 6, 1, false);
    // board.removePiece(5, 0);
    // board.removePiece(6, 0);
    board.removePiece(1, 0);
    board.removePiece(2, 0);
    board.removePiece(3, 0);
    board.removePiece(5, 7);
    board.removePiece(6, 7);
    board.removePiece(1, 6);
    board.addPiece("pawn", 1, 6, true);
    // board.removePiece(1, 7);
    // board.removePiece(2, 7);
    // board.removePiece(3, 7);

    // System.out.println("pawn at g3 attacks h4? = "+ board.attacksSquare(7, 3, 6, 2));
    // System.out.println("pawn at g3 attacks f4? = "+ board.attacksSquare(5, 3, 6, 2));
    // System.out.println("pawn at g3 attacks g4? = "+ board.attacksSquare(6, 3, 6, 2));
    // System.out.println("pawn at g3 attacks g3? (self test) = "+ board.attacksSquare(6, 2, 6, 2));
    // System.out.println("bishop at f4 attacks g5? = "+ board.attacksSquare(6,4,5,3));
    // System.out.println("bishop at f4 attacks e4? = "+ board.attacksSquare(4,3,5,3));
    // System.out.println("bishop at f4 attacks g3? = "+ board.attacksSquare(6,2,5,3));
    // System.out.println("bishop at f4 attacks g4? = "+ board.attacksSquare(6,3,5,3));
    // System.out.println("bishop at f4 attacks e3? = "+ board.attacksSquare(4,2,5,3));
    // System.out.println("king at f4 attacks g5? = "+ board.attacksSquare(6,4,5,3));
    // System.out.println("king at f4 attacks e4? = "+ board.attacksSquare(4,3,5,3));
    // System.out.println("king at f4 attacks g3? = "+ board.attacksSquare(6,2,5,3));
    // System.out.println("king at f4 attacks g4? = "+ board.attacksSquare(6,3,5,3));
    // System.out.println("knight at e4 attacks g5? = "+ board.attacksSquare(6,4,4,3));
    // System.out.println("knight at e4 attacks f2? = "+ board.attacksSquare(5,1,4,3));
    // System.out.println("queen at e5 attacks h5? = "+ board.attacksSquare(7,4,4,4));
    // System.out.println("queen at e5 attacks g5? = "+ board.attacksSquare(6,4,4,4));
    // System.out.println("queen at e5 attacks a3? = "+ board.attacksSquare(0,2,4,4));
    // System.out.println("queen at e5 attacks b5? = "+ board.attacksSquare(1,4,4,4));
    // System.out.println("queen at e5 attacks e2? = "+ board.attacksSquare(4,1,4,4));
    // System.out.println("queen at e5 attacks e3? = "+ board.attacksSquare(4,2,4,4));
    // System.out.println("queen at e5 attacks d4? = "+ board.attacksSquare(3,3,4,4));
    // System.out.println("queen at e5 attacks f4? = "+ board.attacksSquare(5,3,4,4));
    // System.out.println("queen at e5 attacks e5? (self test) = "+ board.attacksSquare(4,4,4,4));
  }


}//end player
