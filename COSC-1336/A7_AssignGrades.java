import java.util.Scanner;

public class A7_AssignGrades {
	/** Get the highest score from an array of scores */
	public static int getHighestScore(int[] scores) {
		int highestscore = 0;
		for (int num: scores) {
			if (num > highestscore)
				highestscore = num;
		}
		return highestscore;
	}
	
	public static char getGrade(int score, int bestScore) {
		char grade = 'F';
		
		if (score >= bestScore - 10)
			grade = 'A';
		else if (score >= bestScore - 20)
			grade = 'B';
		else if (score >= bestScore - 30)
			grade = 'C';
		else if (score >= bestScore - 40)
			grade = 'D';
		
		return grade;
	}
	
	public static void main(String[] args) {
		//Initialize the input
		Scanner input = new Scanner(System.in);
		
		//Prompt the user
		System.out.print("Enter the number of students: ");
		
		//Get number of students
		int numStudents = input.nextInt();
		
		//Prompt again
		System.out.print("Enter " + numStudents + " scores: ");
		
		//Initialize scores array
		int[] scores = new int[numStudents];
		for (int i = 0; i < numStudents; i++) {
			scores[i] = input.nextInt();
		}
		
		//Get the highest score
		int highestScore = getHighestScore(scores);
		
		//Print the student scores
		for (int i = 0; i < numStudents; i++) {
			System.out.println(
				"Student " + i + " score is " + scores[i] + " and grade is "
				+ getGrade(scores[i], highestScore)
			);
		}
	}
}
