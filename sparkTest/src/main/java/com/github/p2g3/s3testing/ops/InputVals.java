package com.github.p2g3.s3testing.ops;

public class InputVals {
    int catg;
    int col1;
    int col2;
    int opChoice;
    int subCatChoice;

    public InputVals(String[] args) {
        this.catg = Integer.parseInt(args[0]);
        this.col1 = Integer.parseInt(args[1]);
        this.col2 = Integer.parseInt(args[2]);
        this.opChoice = Integer.parseInt(args[3]);
        this.subCatChoice = Integer.parseInt(args[4]);
    }
    
    public int getCat() {
		return this.catg;
	}
    public int getInd() {
		return this.col1;
	}
    public int getDep() {
		return this.col2;
    }
    public int getOps() {
		return this.opChoice;
    }
    public int getSub() {
		return this.subCatChoice;
	}
}	

	