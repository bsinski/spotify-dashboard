package pl.edu.pw.mini.ZPOiF.project.Demo;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javafx.application.Application;
import pl.edu.pw.mini.ZPOiF.project.Exceptions.WrongArtistName;
import pl.edu.pw.mini.ZPOiF.project.Functions.ArtistSummary;
import pl.edu.pw.mini.ZPOiF.project.Functions.QueryMaker;
import pl.edu.pw.mini.ZPOiF.project.SpotifyObjects.Album;
import pl.edu.pw.mini.ZPOiF.project.SpotifyObjects.Artist;
import pl.edu.pw.mini.ZPOiF.project.SpotifyObjects.Track;


public class Main {
	

	public static void main(String[] args) throws IOException, InterruptedException, WrongArtistName {


		
		String name = "drake aac,barom";
        Arrays.stream(name.split(",")).forEach(x -> System.out.println(x));
    
	}

}
