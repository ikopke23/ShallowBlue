class ArrayTools{

  public static void main(String[] args){
    double[] s = {0.0, 0.5, 1.0, 1.5, 2.0, 2.5};
    double[] p = {1.0, 0.5, 1.0, 1.5, 2.0, 2.5};
    double[] q = {1.0, 2.0, 3.0};
    double[] r = {5.0, 6.0, 0.0};
  double[] mixed = {0.0, -0.5, 1.0, -1.5, 1.0, 867.0, -2.5};
  double[] test = {0.5, 1.0, 1.5, 2.0, 2.5, 3.0};
  double[] positives = {0.5, 1.0, 1.5, 2.0, 2.5, 3.0};
  double[] negatives = {-0.5,-1.0, -1.5, -2.0, -2.5, -3.0};

    //The testing area for in file testing
    // ------testing print -------
    System.out.println("------testing print------");
    print(mixed);
    print(negatives);
    System.out.println("");
    // ------------testing toString
    System.out.println("------testing toString------");
    System.out.println("toString version of 'mixed'");
    System.out.println(toString(mixed));
    // ------------testing equals----------
    System.out.println("------testing Equals------");
    System.out.println("does 'test' equal 'positives'?");
    System.out.println(equals(test, positives));
    System.out.println("does 'negatives' equal 'positives'?");
    System.out.println(equals(negatives, positives));
    // ------------testing reverse----------
    System.out.println("------testing reverse------");
    println(negatives);
    System.out.println("------reversal of negatives------");
    println(reverse(negatives));
    System.out.println("------reversal of mixed------");
    println(mixed);
    println(reverse(mixed));
    // ------------testing max--------
    System.out.println("the max value of 'mixed' is "+max(mixed));
    System.out.println("the max value of 'negatives' is "+max(negatives));
    // ------------testing min ------------
    System.out.println("the min value of 'mixed' is "+min(mixed));
    System.out.println("the min value of 'negatives' is "+min(negatives));
    // -------------testing Scale ----------
    System.out.println("------testing scale------");
    println(positives);
    System.out.println("mulitplying positives(above) by negative 1 (below)");
    println(scale(positives, -1));
    println(negatives);
    System.out.println("mulitplying negatives(above) by 5 (below)");
    println(scale(negatives, 5));
    // // -------- testing Add ----------
    System.out.println("------testing add------");
    System.out.println("The first case when the lengths are equal");
    System.out.println("Adding 'positives' length of 6 and 'negatives' length of 6");
    println(positives);
    println(negatives);
    println(add(positives, negatives));
    System.out.println("The second case when the first array is longer");
    System.out.println("Adding 'mixed' length of 7 and 'negatives' length of 6 ");
    println(mixed);
    println(negatives);
    println(add(mixed, negatives));
    System.out.println("The third case when the second array is longer");
    System.out.println("Adding 'negatives' length of 6 and 'mixed' length of 7");
    println(negatives);
    println(mixed);
    println(add(negatives, mixed));
    // // -------- testing Weighted Add----------
    System.out.println("------Testing Weighted add------");
    System.out.println("returns weighted array of S and P with weights 36 and 64");
    println(s);
    println(p);
    println(weightedAdd(s,p,.36,.64));
    System.out.println("returns weighted array of Q and R with weights 75 and 25");
    println(q);
    println(r);
    println(weightedAdd(q,r,0.75,0.25));
    // // -------- testing Copy----------
    System.out.println("------Testing Copy------");
    System.out.println("Array coppied from index 2 to 5 (inclusive exclusive)");
    println(s);
    println(copy(s,2,2));
    // // -------- testing Cut----------
    System.out.println("--------Testing Cut-------");
    System.out.println("Array cut from index 2 to 4 (inclusive exclusive)");
    println(p);
    println(cut(p,2,2));
    // // -------- testing Concat----------
    System.out.println("------Testing Concat------");
    print(positives);
    System.out.print(" + ");
    print(negatives);
    println(concat(positives,negatives));
    //-------- testing Splice----------
    //first test case for when the index is within the initial arrays length
    System.out.println("--------Testing Splice----------");
    System.out.println("first test case for when the index is within the initial arrays length");
    println(mixed);
    println(positives);
    System.out.println("index = 5");
    println(splice(mixed,positives, 5));
    //second test case for when the index is out of the array length
    System.out.println("second test case for when the index is out of the array length");
    println(mixed);
    println(positives);
    System.out.println("index = 10");
    println(splice(mixed,positives, 10)); // empty values are automatically asigned 0.0 by computer
  }//end main
  /**
      *print prints the values of the array to the console
      *
      *
      *@param args, array that is printed to the console without an line break
      *@author Kopke_Ian
  */
  public static void print(double[] args){
    for(int i = 0; i<args.length ; i++){
      if(i != args.length-1){
        System.out.print(args[i]);
        System.out.print(", ");
      } else{
        System.out.print(args[i]);
      }
    }
  }//end print
  /**
    *println prints the values of the array to the console
    *
    *
    *@param args, array tha is printed to the console with an ln at the back
  */
  public static void println(double[] args){
    for(int i = 0; i<args.length ; i++){
      if(i != args.length-1){
        System.out.print(args[i]);
        System.out.print(", ");
      } else{
        System.out.print(args[i]);
        System.out.println("");
      }
    }
  }//end println

  public static void println(int[][] args){
    for(int i = 0; i<args.length ; i++){
      if(i != args.length-1){
        for(int n = 0; n < args[i].length; n++){
          System.out.print(args[i][n]);
          System.out.print(", ");
          if(n == args[i].length-1){
            System.out.println("");
          }
        }
      } else{
        for(int n = 0; n < args[i].length; n++){
          System.out.print(args[i][n]);
          System.out.print("");
        }
      }
    }
  }//end println
  /**
      * returns a string version of the array with the elements on the same line
      *
      *@param args, array that is going to be put in a string
      *@return string, the string is all of the values of the array seperated by a comma and a space
  */
  public static String toString(double[] args){
    String chain = "";
    for(int i = 0; i<args.length ; i++){
      if(i != args.length-1){
        chain+= args[i]+", ";
      } else{
        chain += args[i];
      }
    }
    return chain;
  }//end toString
  /**
      *equals checks if two arrays are comletely equal
      *
      *@param arg, first array
      *@param ar2, second array
      *@return true/false a boolean that describes if the arrays are equal
  */
  public static boolean equals(double[] arg, double[] arg2){
      if(arg.length != arg2.length){
        return false;
      } else {
        for(int i = 0; i< arg.length; i++){
          if(arg[i] != arg2[i]){
            return false;
          }
        }
      }
    return true;
  }//end equals
  /**
      *reverse returns an array with the indexes of the original array reversed
      *
      *
      *@param array that will be reversed in the return array
      *@return array, with the values in reverse
  */
  public static double[] reverse(double[] args){
    double[] rev = new double[args.length];
    for(int i = args.length; i > 0; i--){
      rev[i-1] = args[((args.length)-i)];
    }
    return rev;
  }//end reverse
  /**
      *Returns the maximum value of an array
      *
      *
      *@param array of doubles, will be compared to be
      *@return double, finds the max that is one of the values of the array
  */
  public static double max(double[] args){
    double mx = Double.NEGATIVE_INFINITY;
    for(int i = 0; i < args.length; i++){
      if(mx < args[i]){
        mx = args[i];
      }
    }
    return mx;
  }//end max
  /**
      *Returns the minimum value of an array
      *
      *
      *@param array of doubles, will be compared to be
      *@return double, finds the min that is one of the values of the array
  */
  public static double min(double[] args){
    double mn = Double.POSITIVE_INFINITY;
    for(int i = 0; i < args.length; i++){
      if(mn > args[i]){
        mn = args[i];
      }
    }
    return mn;
  }//end min
  /**
      *Scale returns new array with all of the values scaled by a common multiplier
      *
      *
      *@param args, the array is going to be multiplied by the second param
      *@param mult, a double that will be applied to all of the values of the array
      *@return an array with the multiple applied
  */
  public static double[] scale(double[] args, double mult){
    double[] sld = new double[args.length];
    for(int i = 0; i < args.length;i++){
      sld[i] = (args[i] * mult);
    }
    return sld;
  }//end scale
  /*
    Assuming that arg and arg2 have the same length
  */
  // public static int[][] doubleAdd(int[][] arg, int[][] arg2){
  //   int[][] ret = new int[arg.length][arg[0].length];
  //   for(int i = 0; i < arg.length; i++){
  //     for(int j = 0; j < arg[0].length; j++){
  //       ret = arg[i][j]+arg2[i][j];
  //     }
  //   }
  //   return ret;
  // }


  /**
    *add returns an array where the two parameters had their elements added together
    *If the arrays are a different length the extra indexes are made to be 0.0
    *
    *
    *@param arg, initial array
    *@param arg2,
  */

  public static double[] add(double[] arg, double[] arg2){
    if(arg.length > arg2.length){
      double[] arg3 = new double[arg.length];
      double[] argRet = new double[arg.length];
      for(int i = 0; i < arg.length;i++){
        if(i < arg2.length){
          arg3[i] = arg2[i];
        } else{
          arg3[i] = 0.0;
        }
      }//end forloop
      for(int i = 0; i < arg.length;i++){
        argRet[i] = arg3[i] + arg[i];
      }//end forloop
      return argRet;
    }else if(arg.length < arg2.length){
      double[] arg3 = new double[arg2.length];
      double[] argRet = new double[arg2.length];
      for(int i = 0; i < arg2.length;i++){
        if(i < arg.length){
          arg3[i] = arg[i];
        } else{
          arg3[i] = 0.0;
        }
      }//end forloop
      for(int i = 0; i < arg2.length;i++){
        argRet[i] = arg3[i] + arg2[i];
      }//end forloop
      return argRet;
    }//end elseif
    double[] argRet = new double[arg2.length]; //if the lengths are neither < or > then they must be equal
    for(int i = 0; i < arg.length; i++){
      argRet[i] = arg[i] + arg2[i];
    }
    return argRet;//this can not be reached
  }
  /**
      *WeightedAdd returns new array with the result of a weighted addition of two arrays
      *
      *
      *@param arg, first array to be weighted
      *@param arg2, the second array to be weighted
      *@param wgt, the first weight that will be applied to the first array
      *@param wgt2, the second weight that will be applied to the second array
      *@return array of doubles after the weighted addition is done
  */
  public static double[] weightedAdd(double[] arg, double[] arg2, double wgt, double wgt2){
    double[] weighted = new double[0];
    if(wgt+wgt2 != 1.0){
      return weighted;
     }
    return add(scale(arg,wgt), scale(arg2,wgt2));
  }//end weightAdd
  /**
      *Copy returns new array that is a smaller portion of the original array
      *
      *
      *@param args, an array tht will be copied
      *@param ind, the first index (inclusive)
      *@param ind2, the second index (exclusive)
      *@return
  */
  public static double[] copy(double[] args, int ind, int ind2){
    int dif = ind2-ind;
    if((dif <= 0 || ind < 0) || (ind2 < 0 || ind > ind2)){
      double[] err = new double[0];
      return err;
    }
    double[] copd = new double[dif];
    for(int i = 0; i < dif;i++){
      copd[i] = args[i+ind];
    }
    return copd;
  }//end copy
  /**
      *cut returns new array that removes specific portion of the original array
      *
      *
      *@param args, initial array to be cut
      *@param ind,  first index to be cut (inclusive)
      *@param ind2,  second index to be cut (exclusive)
      *@return returns smaller array with the indexes between the two params taken out
  */
  public static double[] cut(double[] args, int ind, int ind2){//end cut
    int dif = ind2-ind;
    if((dif <= 0 || ind < 0) || (ind2 < 0 || ind > ind2)){
      double[] err = new double[0];
      return err;
    }

    double[] cut = new double[(args.length-(dif))];
    for(int i = 0; i < args.length;i++){
      if(i< ind){
        cut[i] = args[i];
      } else if(i >= ind && i< ind2){
      } else{
        cut[i-dif] = args[i];
      }
    }
    return cut;
  }
  /**
      *Concat returns new array with the second array added to the back of the first array
      *
      *
      *@param arg, the first aray
      *@param arg2, the second array
      *@return
  */
  public static double[] concat(double[] arg, double[] arg2){
    double[] ccat = new double[arg.length+arg2.length];
    for(int i = 0; i < arg.length; i++){
      ccat[i] = arg[i];
    }
    for(int n = arg.length; n < (arg.length+arg2.length); n++){
      ccat[n] = arg2[n-arg.length];
    }
    return ccat;
  }//end concat
  /**
      *Splice returns new array that is one array inserted into a new index
      *
      *
      *
      *@param arg, initial array
      *@param arg2, array that is being inserted
      *@param ind, index for where the array will be inserted
      *@return array of double[]
  */
  public static double[] splice(double[] arg, double[] arg2, int ind){
    int newlength = arg.length+arg2.length;
    if(ind > arg.length){
      newlength = ind+arg2.length;
    }
    // System.out.println("ind = " +ind);
    // System.out.println("newlength = " +newlength);
    double[] spliced = new double[newlength];
    for(int i = 0; i< newlength; i++){
      if(i < arg.length && i < ind){
        spliced[i] = arg[i];
      }else if(i >= ind && i < ind+arg2.length){
        spliced[i] = arg2[i-ind];
      }else if(arg.length-ind > 0){
        spliced[i] = arg[i-arg2.length];
      }
      // System.out.println(spliced[i]);
    }

    return spliced;
  }//end splice

}//end class
