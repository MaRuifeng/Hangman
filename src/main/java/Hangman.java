import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Hangman
{
	public static final String[] dictionary = {"happy", "world", "foo", "bar", "gua", "frog", "panda", "duck", "donkey"};
	public static final Character placeholder = '_';

	private static int numOfGuesses;
	private static List<Character> charsInWord;
	private static List<Character> hits;
	private static List<Character> misses;

	public static void init( String wordToGuess )
	{
		numOfGuesses = 0;
		charsInWord = wordToGuess.chars().mapToObj( e -> (char) e ).collect( Collectors.toList() );
		hits = new ArrayList<>( Collections.nCopies( wordToGuess.length(), placeholder ) );
		misses = new ArrayList<>();
	}

	public static void check( Character input )
	{
		if ( charsInWord.contains( input ) )
		{
			int index = charsInWord.indexOf( input );
			hits.set( index, input );
			charsInWord.set( index, placeholder );
		}
		else
		{
			if ( hits.contains( input ) )
			{
				System.out.println( "You have guessed this letter already. Please try another one." );
			}
			else if (misses.contains( input ))
			{
				System.out.println("You have tried this letter, and it's in correct. Please try another one.");
			}
			else
			{
				misses.add( input );
			}
		}
	}

	public static boolean isSuccess()
	{
		return !hits.contains( '_' );
	}

	public static void print()
	{
		System.out.println( "Guess a letter" );
		System.out.println( hits.stream()
				.map( String::valueOf )
				.collect( Collectors.joining( " " ) )
		);
		System.out.println( "Incorrect guesses: " + misses );
		System.out.println( "Total guesses: " + numOfGuesses + "\n" );
	}

	public static void play()
	{
		Scanner scanner = new Scanner( System.in );
		while ( !isSuccess() )
		{
			print();
			String input = scanner.next();
			numOfGuesses++;
			check( input.charAt( 0 ) );
		}
	}

	public static void main( String[] args )
	{
		System.out.println( "Welcome to Hangman!" );
		init( dictionary[( new Random() ).nextInt( dictionary.length )] );
		play();
		System.out.println( "End of game." );
	}
}
