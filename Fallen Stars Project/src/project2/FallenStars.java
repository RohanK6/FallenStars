/**
 * This class is the program performing a query through a provided Meteorite dataset.
 * When the program is executed the name of the input file containing the list of all the recorded
 * Meteorites is provided as the program's single command line argument.
 * In the interactive part, the user enters either a year, mass, or location to search for Meteorite objects matching
 * the data. The program then prints out all Meteorite objects that match the query.
 * @author Rohan Khanderia
 */
package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FallenStars {
    /**
     * The main() method for this program
     * @param args array of Strings provided on the command line when the program is started.
     *             The first string should be the name of the input file containing the list of named colors.
     */
    public static void main(String[] args) {
//         If there is no argument passed in
        if (args.length == 0) {
            System.err.println("Usage Error: the program expects file name as an argument.");
            System.exit(1);
        }

        File meteorfile = new File(args[0]);

        // exists() method checks if the file denoted by the pathname exists
        if (!meteorfile.exists()) {
            System.err.println("Error: the file " + meteorfile.getAbsolutePath() + " does not exist.");
            System.exit(1);
        }
        // canRead() method checks if the file denoted by the pathname can be read
        if (!meteorfile.canRead()) {
            System.err.println("Error: the file " + meteorfile.getAbsolutePath() + " cannot be read.");
        }

        // Initializes scanner
        Scanner scan = null;

        // Tries to instantiate it, but if the file cannot be read, throw a FileNotFoundException
        try {
            scan = new Scanner(meteorfile);
        } catch (FileNotFoundException e) {
            System.err.println("Error: the file " + meteorfile.getAbsolutePath() + " cannot be read.");
            System.exit(1);
        }

        // If none of the previous errors are reached, then the program continues from here
        MeteoriteList meteorites = new MeteoriteList();
        String meteor_line = null;
        String meteor_name = null;
        int meteor_id = 0;
        ArrayList<String> splitData;
        boolean isAMeteorite;
        scan.nextLine();

        while (scan.hasNextLine()) {
            isAMeteorite = true;

            try {
                  meteor_line = scan.nextLine();
                  splitData = splitCSVLine(meteor_line);
              } catch (NoSuchElementException e) {
                  System.err.println(meteor_line);
                  continue;
              }
              try {
                  meteor_name = splitData.get(0);
                  meteor_id = Integer.parseInt(splitData.get(1));
              } catch (NullPointerException e) {
                  isAMeteorite = false;
              } catch (NumberFormatException e) {
                  isAMeteorite = false;
              } catch (IllegalArgumentException e) {
                  isAMeteorite = false;
              }
              if (meteor_name.trim().equals("")) {
                  isAMeteorite = false;
              }
              if (isAMeteorite) {
                  Meteorite meteorite = new Meteorite(meteor_name, meteor_id);
                  meteorites.add(meteorite);

                  if (!splitData.get(6).equals("")) {
                      try {
                          String[] date = splitData.get(6).split("/");
                          meteorite.setYear(Integer.parseInt(date[2].substring(0, date[2].indexOf(" "))));
                      } catch (NumberFormatException e) {
                          continue;
                      } catch (IllegalArgumentException e) {
                          continue;
                      }
                  }
                  else {
                      continue;
                  }
                  if (!splitData.get(4).equals("")) {
                      try {
                          meteorite.setMass(Integer.parseInt(splitData.get(4)));
                      } catch (NumberFormatException e) {
                          continue;
                      } catch (IllegalArgumentException e) {
                          continue;
                      }
                  }
                  else {
                      continue;
                  }
                  if (!splitData.get(7).equals("") && !splitData.get(8).equals("")) {
                      meteorite.setLocation(new Location(Double.parseDouble(splitData.get(7)), Double.parseDouble(splitData.get(8))));
                  }
                  else {
                      continue;
                  }
              }
        }

        // Interactive Mode
        Scanner input = new Scanner(System.in);
        String userChoice = "";

        System.out.println("Search the database by using one of the following queries.");
        System.out.println("To search for meteorite nearest to a given goe-location, enter");
        System.out.println("\t location LATITUDE LONGITUDE");
        System.out.println("To search for meteorites that fell in a given year, enter");
        System.out.println("\t year YEAR");
        System.out.println("To search for meteorites with weights MASS +/- 10 grams, enter");
        System.out.println("\t mass MASS");
        System.out.println("To finish the program, enter");
        System.out.println("\t QUIT");
        System.out.println();
        System.out.println();

        do {
            boolean isValid = true;
            System.out.println("Enter your search query: ");
            userChoice = input.nextLine();
            System.out.println();

            if (!userChoice.toUpperCase().equals("QUIT")) {
                String[] choices = userChoice.split(" ");

                if (!choices[0].equals("location") &&
                        !choices[0].equals("year") &&
                        !choices[0].equals("mass")) {
                    System.out.println("This is not a valid query. Try again.");
                }


                if (choices[0].equals("location")) {
                    if (choices.length != 3) {
                        System.out.println("This is not a valid geolocation. Try again.");
                        isValid = false;
                    }

                    if (isValid) {
                        try {
                            Double.parseDouble(choices[1]);
                            Double.parseDouble(choices[2]);
                        } catch (NumberFormatException e) {
                            System.out.println("This is not a valid geolocation. Try again.");
                            isValid = false;
                        }
                    }
                    if (isValid) {
                        if (Double.parseDouble(choices[1]) >= -90 && Double.parseDouble(choices[1]) <= 90 &&
                                Double.parseDouble(choices[2]) >= -180 && Double.parseDouble(choices[2]) <= 180) {
                            try {
                                Meteorite validLoc = meteorites.getByLocation(new Location
                                        (Double.parseDouble(choices[1]), Double.parseDouble(choices[2])));
                                if (validLoc == null) {
                                    System.out.println("No matches found. Try again.");
                                }
                                else {
                                    System.out.println(validLoc);
                                }
                            } catch (NullPointerException e) {
                                System.out.println("No matches found. Try again.");
                            }
                        } else {
                            System.out.println("This is not a valid geolocation. Try again");
                        }
                    }
                }


                if (choices[0].equals("year")) {
                    if (choices.length != 2) {
                        System.out.println("This is not a valid year. Try again.");
                        isValid = false;
                    }

                    if (isValid) {
                        try {
                            Integer.parseInt(choices[1]);
                        } catch (NumberFormatException e) {
                            System.out.println("This is not a valid year. Try again.");
                            isValid = false;
                        }
                    }


                    if (isValid) {
                        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                        if (Integer.parseInt(choices[1]) > 0 && Integer.parseInt(choices[1]) < currentYear) {
                            try {
                                MeteoriteLinkedList validYear = meteorites.getByYear(Integer.parseInt(choices[1]));
                                if (validYear == null) {
                                    System.out.println("No matches found. Try again.");
                                }
                                else {
                                    System.out.println(validYear);
                                }
                            } catch (NullPointerException e) {
                                System.out.println("No matches found. Try again.");
                            }

                        } else {
                            System.out.println("This is not a valid year. Try again.");
                        }
                    }
                }


                if (choices[0].equals("mass")) {
                    if (choices.length != 2) {
                        System.out.println("This is not a valid mass. Try again.");
                        isValid = false;
                    }

                    if (isValid) {
                        try {
                            Integer.parseInt(choices[1]);
                        } catch (NumberFormatException e) {
                            System.out.println("This is not a valid mass. Try again.");
                            isValid = false;
                        }
                    }


                    if (isValid) {
                        if (Integer.parseInt(choices[1]) > 0) {
                            try {
                                MeteoriteLinkedList validMass = meteorites.getByMass(Integer.parseInt(choices[1]), 10);
                                if (validMass == null) {
                                    System.out.println("No matches found. Try again.");
                                }
                                else {
                                    System.out.println(validMass);
                                }
                            } catch (NullPointerException e) {
                                System.out.println("No matches found. Try again.");
                            }
                        } else {
                            System.out.println("This is not a valid mass. Try again.");
                        }
                    }

                }

                System.out.println();
            }
        } while (!userChoice.toUpperCase().equals(("QUIT")));
        input.close();
    }

    /**
     * Splits the given line of a CSV file according to commas and double quotes
     * (double quotes are used to surround multi-word entries so that they may contain commas)
     * @author Joanna Klukowska
     * @param textLine  a line of text from a CSV file to be parsed
     * @return an ArrayList object containing all individual entries found on that line;
     *         any missing entries are indicated as empty strings; null is returned if the textline
     *         passed to this function is null itself.
     */
    public static ArrayList<String> splitCSVLine(String textLine){

        if (textLine == null ) return null;

        ArrayList<String> entries = new ArrayList<String>();
        int lineLength = textLine.length();
        StringBuffer nextWord = new StringBuffer();
        char nextChar;
        boolean insideQuotes = false;
        boolean insideEntry= false;

        // iterate over all characters in the textLine
        for (int i = 0; i < lineLength; i++) {
            nextChar = textLine.charAt(i);

            // handle smart quotes as well as regular quotes
            if (nextChar == '"' || nextChar == '\u201C' || nextChar =='\u201D') {

                // change insideQuotes flag when nextChar is a quote
                if (insideQuotes) {
                    insideQuotes = false;
                    insideEntry = false;
                }
                else {
                    insideQuotes = true;
                    insideEntry = true;
                }
            }
            else if (Character.isWhitespace(nextChar)) {
                if ( insideQuotes || insideEntry ) {
                    // add it to the current entry
                    nextWord.append( nextChar );
                }
                else { // skip all spaces between entries
                    continue;
                }
            }
            else if ( nextChar == ',') {
                if (insideQuotes){ // comma inside an entry
                    nextWord.append(nextChar);
                }
                else { // end of entry found
                    insideEntry = false;
                    entries.add(nextWord.toString());
                    nextWord = new StringBuffer();
                }
            }
            else {
                // add all other characters to the nextWord
                nextWord.append(nextChar);
                insideEntry = true;
            }

        }
        // add the last word ( assuming not empty )
        // trim the white space before adding to the list
        if (!nextWord.toString().equals("")) {
            entries.add(nextWord.toString().trim());
        }

        return entries;
    }



}