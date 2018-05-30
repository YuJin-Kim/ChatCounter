package edu.handong.csee.java.chatcounter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * 
 * This class defines Cli object.</br>
 * The Cli class has path, file and help members.</br>
 * The Cli class has run(), parseOptions(), createOptions() and printHelp() methods.</br>
 * 
 * @author yujin
 *
 */

public class Cli {
	String path;
	String file;
	boolean help;

	/**
	 * 
	 * This method is for running(cli).</br>
	 * 
	 * @param args
	 */

	public void run(String[] args) {
		Options options = createOptions();

		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				return;
			}

		}
	}

	/**
	 * 
	 * This method is for parsingOptions.</br>
	 * 
	 * @param options
	 * @param args
	 * @return
	 */

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			path = cmd.getOptionValue("i");
			file = cmd.getOptionValue("o");
			help = cmd.hasOption("h");

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	/**
	 * 
	 * This method is for creating options.</br>
	 * 
	 * @return
	 */

	// Definition Stage
	private Options createOptions() {
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("i").longOpt("path")
				.desc("Set a path of a directory containing message file")
				.hasArg()
				.argName("Path name")
				.required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("o").longOpt("file")
				.desc("Set a file name to store the result")
				.hasArg()
				.argName("Result file name")
				.required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Help")
				.build());

		return options;
	}

	/**
	 * 
	 * This method is printing help messages.</br>
	 * 
	 * @param options
	 */

	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "Chat Counter";
		String footer ="\nPlease report issues at https://github.com/YuJin-Kim/ChatCounter/issues";
		formatter.printHelp("ChatCounter", header, options, footer, true);
	}

}
