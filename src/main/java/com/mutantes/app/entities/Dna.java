package com.mutantes.app.entities;

public class Dna {
	
	private String[] dnaSequence;
	private String dna_id;
	private boolean mutant;
	private int height;
	private int width;
	private char[][] dnaMatrix;
	private final int maxSeqMutant = 3;
	private final int maxSeqNucleos = 4;
	
	// Constructors
	public Dna() {
	}
	public Dna (String[] sequence) throws Exception{
		dnaSequence = sequence;
		height = sequence.length;
		if(height<=0) throw new Exception("Invalid height in dna");
		width = sequence[0].length();
		if(width<=0) throw new Exception("Invalid width in dna");
		dnaMatrix = new char[height][width];
		
		String dnaSimple = "";
		for(int seq=0; seq< sequence.length; seq++) {
			for(int nucleoBase=0; nucleoBase<sequence[seq].length();nucleoBase++) {
				char nucleo = sequence[seq].charAt(nucleoBase);
				if(nucleo!='A'&&nucleo!='C'&&nucleo!='G'&&nucleo!='T') throw new Exception("Invalid polynucleotide in dna");
				dnaMatrix[seq][nucleoBase]=nucleo;
				dnaSimple+=nucleo;
			}
		}
		dna_id=dnaSimple;
	}
	public Dna (char[][] matrix) throws Exception{
		height = matrix.length;	
		if(height<=0) throw new Exception("Invalid age");
		width = matrix[0].length;
		if(width<=0) throw new Exception("Invalid width in dna");
		dnaMatrix = matrix;
		
		String dnaSimple = "";
		for(int seq=0; seq< dnaMatrix.length; seq++) {
			for(int nucleoBase=0; nucleoBase<dnaMatrix[seq].length;nucleoBase++) {
				char nucleo = dnaMatrix[seq][nucleoBase];
				if(nucleo!='A'&&nucleo!='C'&&nucleo!='G'&&nucleo!='T') throw new Exception("Invalid polynucleotide in dna");
				dnaSimple+=nucleo;
			}
		}
		dna_id=dnaSimple;
	}
	
	// getters and setters
	public String[] getDnaSequence() {
		return dnaSequence;
	}
	public void setDnaSequence(String[] dnaSequence) {
		this.dnaSequence = dnaSequence;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public char[][] getDnaMatrix() {
		return dnaMatrix;
	}
	public void setDnaMatrix(char[][] dnaMatrix) {
		this.dnaMatrix = dnaMatrix;
	}
	public String getDna_id() {
		return dna_id;
	}
	public void setDna_id(String dna_id) {
		this.dna_id = dna_id;
	}
	public boolean getMutant() {
		return mutant;
	}
	public void setMutant(boolean mutant) {
		this.mutant = mutant;
	}
	
	// help methods
	private char[][] getDiagonalUp(char[][] matrix){
		int widthDiag = ((width>height) ? height: width);
		char[][] diagonals = new char[height+width-1][widthDiag+1];
		//System.out.println(height+width-1+" : "+widthDiag);
		int i,j;
		int diagonalCount = 0;
		//System.out.println("Desde fila");
		for(int k=0; k<height; k++) {
			i=k;
			j=0;
			int colDiag = 0;
			while(i>=0) {
				//System.out.println(diagonalCount+":"+colDiag+" / "+i+":"+j);
				diagonals[diagonalCount][colDiag] = matrix[i][j];
				if(i==0 || j==width-1) {
					diagonalCount++;
					colDiag=0;
				} else {
					colDiag++;
				}
				i=i-1;
				j=j+1;
			}
		}
		//System.out.println("Desde columna");
		for(int k=1; k<width; k++) {
			i=height-1;
			j=k;
			int colDiag = 0;
			while(j<=width-1) {
				//System.out.println(diagonalCount+":"+colDiag+" / "+i+":"+j);
				diagonals[diagonalCount][colDiag] = matrix[i][j];
				if(i==0  || j==width-1) {
					diagonalCount++;
					colDiag=0;
				} else {
					colDiag++;
				}
				i=i-1;
				j=j+1;
			}
		}
		//printMarix(diagonals);
		
		return diagonals;
	}
	
	private char[][] getDiagonalDown( char[][] matrix){
		char[][] rotate = rotateMatrix(matrix);
		//printMarix(rotate);
		char[][] diagonals = getDiagonalUp(rotate);
		return diagonals;
	}
	
	private char[][] rotateMatrix(char[][] matrix) {
		
		char[][] rotate = new char[matrix[0].length][matrix.length];
		
		for(int i=0; i<matrix.length;i++) {
			for(int j=0; j<matrix[0].length;j++) {
				rotate[j][matrix.length-i-1]=matrix[i][j];
			}
		}
		return rotate;
	}
	
	private void printMarix(char[][] diagonal) {
		String str = "";
		for(int i=0; i< diagonal.length; i++) {
			for(int j=0; j<diagonal[i].length;j++) {
				str += ","+diagonal[i][j];
			}
			str += "\n";
		}
		System.out.println(str);
	}
	
	@Override
    public String toString()
    {
		String str = "";
		for(int seq=0; seq< dnaSequence.length; seq++) {
			for(int nucleoBase=0; nucleoBase<dnaSequence[seq].length();nucleoBase++) {
				str += ","+dnaSequence[seq].charAt(nucleoBase);
			}
			str += "\n";
		}
		return str;
    }
	
	// principal method
	public boolean isMutant() {
		mutant = true;
		char lastNucleo = 'x';  
		int countNucleos = 1;
		int mutations = 0; 
		
		// check horizontal
		for(int seq=0; seq< dnaMatrix.length; seq++) {
			for(int nucleoBase=0; nucleoBase<dnaMatrix[seq].length;nucleoBase++) {
				char actualNucleo = dnaMatrix[seq][nucleoBase];
				if(lastNucleo==actualNucleo) {
					countNucleos++;
				}else {
					countNucleos = 1;
				}
				if(countNucleos==maxSeqNucleos) {
					mutations++;
					//System.out.println("Mutacion Encotrada Horizontal "+mutations+" : "+actualNucleo);
					if(mutations>=maxSeqMutant) return mutant;
				}
				lastNucleo = actualNucleo;
			}
		}
		
		// check vertical
		for(int nucleoBase=0; nucleoBase<dnaMatrix[0].length;nucleoBase++) {
			for(int seq=0; seq< dnaMatrix.length; seq++) {
				char actualNucleo = dnaMatrix[seq][nucleoBase];
				if(lastNucleo==actualNucleo) {
					countNucleos++;
				}else {
					countNucleos = 1;
				}
				if(countNucleos==maxSeqNucleos) {
					mutations++;
					//System.out.println("Mutacion Encotrada Vertical "+mutations+" : "+actualNucleo);
					if(mutations>=maxSeqMutant) return mutant;
				}
				lastNucleo = actualNucleo;
			}
		}
		
		// check diagonal up
		char[][] diagUp = getDiagonalUp(dnaMatrix);
		for(int seq=0; seq< diagUp.length; seq++) {
			for(int nucleoBase=0; nucleoBase<diagUp[seq].length;nucleoBase++) {
				char actualNucleo = diagUp[seq][nucleoBase];
				if(lastNucleo==actualNucleo) {
					countNucleos++;
				}else {
					countNucleos = 1;
				}
				if(countNucleos==maxSeqNucleos && actualNucleo!='\u0000') {
					mutations++;
					//System.out.println("Mutacion Encotrada Diagonal Arriba "+mutations+" : "+actualNucleo);
					if(mutations>=maxSeqMutant) return mutant;
				}
				lastNucleo = actualNucleo;
			}
		}
		
		// check diagonal down
		char[][] diagDown = getDiagonalDown(dnaMatrix);
		for(int seq=0; seq< diagDown.length; seq++) {
			for(int nucleoBase=0; nucleoBase<diagDown[seq].length;nucleoBase++) {
				char actualNucleo = diagDown[seq][nucleoBase];
				if(lastNucleo==actualNucleo) {
					countNucleos++;
				}else {
					countNucleos = 1;
				}
				if(countNucleos==maxSeqNucleos && actualNucleo!='\u0000') {
					mutations++;
					//System.out.println("Mutacion Encotrada Diagonal Abajo "+mutations+" : "+actualNucleo);
					if(mutations>=maxSeqMutant) return mutant;
				}
				lastNucleo = actualNucleo;
			}
		}		
		
		mutant=false;
		return mutant;
	}
	
}
