/**
 * Created by zhendeveloper on 3/25/17.
 */
import jm.JMC;
import jm.audio.Instrument;
import jm.music.data.*;
import jm.util.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;

public class AudioFileWriter
{
	/**
	 *
	 * @param melody Use the getMelody() method from MelodyGenerator
	 * @param fileOutputStream FileOutputStream object made from the midi File
	 */
	public static void writeMelodyToFile(List<String> melody, FileOutputStream fileOutputStream)
	{
		Score s = new Score();
		s.setTempo(100.0);
		Part p = new Part(JMC.PIANO);
		Phrase phr = new Phrase();
		for(String noteStr : melody)
		{
			Note n = new Note();
			n.setDuration(JMC.QUARTER_NOTE);
			switch(noteStr)
			{
				case "C4":
					n.setPitch(60);
					break;
				case "D4":
					n.setPitch(62);
					break;
				case "E4":
					n.setPitch(64);
					break;
				case "F4":
					n.setPitch(65);
					break;
				case "G4":
					n.setPitch(67);
					break;
				case "A4":
					n.setPitch(69);
					break;
				case "B4":
					n.setPitch(71);
					break;
				case "C5":
					n.setPitch(72);
					break;
				default:
					throw new InputMismatchException("Bad Note In Melody");
			}
			phr.addNote(n);
		}
		p.addPhrase(phr);
		s.addPart(p);
		Write.midi(s, fileOutputStream);
	}

	public static void main(String[] args)
	{
		File midiFile = new File("melody.mid");
		try
		{
			writeMelodyToFile(MelodyGenerator.getMelody(), new FileOutputStream(midiFile));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
