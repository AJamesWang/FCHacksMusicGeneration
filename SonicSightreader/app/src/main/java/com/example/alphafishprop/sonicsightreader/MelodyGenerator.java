/**
 * Created by zhendeveloper on 3/25/17.
 */
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;

public class MelodyGenerator
{
	//C Major, 4/4, four bars of quarter notes
	//length is always 16
	public static List<String> getMelody()
	{
		List<String> chords = getChords();
		List<String> melody = new LinkedList<>();
		for(String chord : chords)
		{
			melody.add(getNoteFromChord(chord));
		}
		assert melody.size() == 16;
		return melody;
	}

	//four bars of quarter notes
	//generates chords backwards
	static List<String> getChords()
	{
		List<String> chords = new LinkedList<>();
		chords.add("I");
		for(int i = 0; i < 14; i++)
		{
			chords.add(0, getPrevChord(chords.get(0)));
		}
		chords.add(0, getFirstChord(chords.get(0)));
		return chords;
	}

	static String getNoteFromChord(String chord)
	{
		double r = Math.random();
		switch(chord)
		{
			case "I":
				if(r<1.0/4)
					return "C4";
				if(r<2.0/4)
					return "E4";
				if(r<3.0/4)
					return "G4";
				//else
					return "C5";
			case "ii":
				if(r<1.0/3)
					return "D4";
				if(r<2.0/3)
					return "F4";
				//else
					return "A4";
			case "IV":
				if(r<1.0/4)
					return "C4";
				if(r<2.0/4)
					return "F4";
				if(r<3.0/4)
					return "A4";
				//else
					return "C5";
			case "V":
				if(r<1.0/4)
					return "D4";
				if(r<2.0/4)
					return "F4";
				if(r<3.0/4)
					return "G4";
				//else
					return "B4";
			case "V(V)":
				if(r<1.0/2)
					return "D4";
				//else
					return "A4";
			case "vi":
				if(r<1.0/4)
					return "C4";
				if(r<2.0/4)
					return "E4";
				if(r<3.0/4)
					return "A4";
				//else
					return "C5";
			case "vii":
				if(r<1.0/3)
					return "D4";
				if(r<2.0/3)
					return "F4";
				//else
					return "B4";
			default:
				throw new InputMismatchException("Bad chord input");
		}
	}

	//works only in Major
	//
	static String getPrevChord(String chord)
	{
		double r = Math.random(); //r is in [0, 1)
		switch(chord)
		{
			case "I":
				if(r < .25)
					return "vii";
				//else
					return "V";
			case "ii":
				if(r < .25)
					return "IV";
				if(r > .5)
					return "I";
				//else
					return "vi";
			case "IV":
				if(r < (1.0/3))
					return "vi";
				//else
					return "I";
			case "V":
				double c1 = .25;
				if(r < c1)
					return "I";
				c1 += 3.0/32;
				if(r < c1)
					return "ii";
				c1 += 9.0/32;
				if(r < c1)
					return "IV";
				c1 += 3.0/16;
				if(r < c1)
					return "V(V)";
				c1 += 1.0/8;
				if(r < c1)
					return "vi";
				//else
					return "vii";
			case "V(V)":
				double c2 = 1.0/3;
				if(r < c2)
					return "I";
				c2 += 1.0/8;
				if(r < c2)
					return "ii";
				c2 += 3.0/8;
				if(r < c2)
					return "IV";
				//else
					return "vi";
			case "vi":
				if(r < 1.0/3)
					return "vii";
				//else
					return "V";
			case "vii":
				double c3 = .25;
				if(r < c3)
					return "I";
				c3 += 3.0/32;
				if (r < c3)
					return "ii";
				c3 += 9.0/32;
				if (r < c3)
					return "IV";
				c3 += 1.0/4;
				if (r < c3)
					return "V(V)";
				//else
					return "vi";
			default:
				throw new InputMismatchException("Bad chord input");
		}
	}

	//works only in Major
	//
	static String getFirstChord(String chord)
	{
		final double r = Math.random(); //r is in [0, 1)
		switch(chord)
		{
			case "I":
				return "V";
			case "ii":
				return "I";
			case "IV":
				return "I";
			case "V":
				return "I";
			case "V(V)":
				return "I";
			case "vi":
				return "V";
			case "vii":
				return "I";
			default:
				throw new InputMismatchException("Bad chord input");
		}
	}
}
