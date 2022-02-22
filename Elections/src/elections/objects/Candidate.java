package elections.objects;

import java.util.Scanner;

public class Candidate extends Citizen{
	
	private String politicalPartyName;
	private int placement;

	
	public Candidate(String name, int id, int year, String wichBallot, int placement, String politicalPartyName) {
		super(name,id,year,wichBallot);
		this.placement=placement;
		this.politicalPartyName=politicalPartyName;
	}
	
	public Candidate(Scanner scan) {
		super(scan);
		this.placement = scan.nextInt();
		this.politicalPartyName = scan.next();
	}
	
	//μϊχο
	public static void insertionSort(Candidate[] arr) {
		for (int i=1 ; i < arr.length ; i++) {
			for (int j=i ; j > 0 && arr[j-1].placement > arr[j].placement ; j--) {
				Candidate temp = arr[j];
				arr[j] = arr[j-1];
				arr[j-1] = temp;
				}
			}
		}
	public String getPoliticalPartyName() {
		return this.politicalPartyName;
	}
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof Citizen))
			return false;
		
		if(!(super.equals(other)))
			return false;
		
		Candidate temp = (Candidate)other;
		return (temp.placement == placement) && (temp.politicalPartyName == politicalPartyName);
	}
	@Override
	public String toString() {
		return super.toString()+ ",the citizen is candidate for " + politicalPartyName + ", he is placed at " + placement;
	}

}