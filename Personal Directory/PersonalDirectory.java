/**
 * PersonalDirectory class
 *
 * A personal directory that allows user to manipulate
 * directory information stored in a file
 *
 * @author Danny Cheng
 * @date 10/31/14
 *
 */
import java.awt.*;
import hsa.Console;
import java.io.*;

public class PersonalDirectory
{

    ////////////////////////////////Global Variables////////////////////////////////

    static Console c;           // The output console
    static Person[] p = new Person [100]; //Makes array global
    static int personCount = 0;
    ////////////////////////////////Methods////////////////////////////////
    /**
    * Capitalizes the first letter of a word
    *
    * @param word The word to capitalize
    * @return Returns word with a capital
    */
    public static String Capitalize (String word)
    {
	return (word.substring (0, 1).toUpperCase ()) + word.substring (1);
    } //String Capitalize (String)


    /**
    * An error trap to make sure that the user enters y or n
    *
    * @param YoN The user's input
    * @return A char, either y or n
    */
    public static char YoNmeth (char YoN)
    {
	while (true)
	{
	    YoN = c.getChar ();
	    if (YoN == 'y' || YoN == 'n')
		break;
	    c.println ("Please enter y or n");
	} //End while
	return YoN;
    } //char YoNmeth (char)


    /**
    * Searches the array for the name of the person
    * that the user is searching for
    *
    * @param display A flag that determines whether or not
    * to display the person's information
    *
    * @return The location of the person that user is searching for
    */
    public static int Search (boolean display, boolean header)
    {
	String search = "";
	int option = 0;
	char YoN = 'y';
	//The amount of matches found
	int matchesFound = 0;

	//Contains location of matches
	int[] personLoc = new int [100];


	if (personCount == 0)
	    return -2;

	while (true)
	{
	    if (header == true)
		c.println ("Search for person\n");
	    c.println ("Would you like to view the database? (y or n)");
	    YoN = YoNmeth (YoN);
	    if (YoN == 'n')
		break;
	    else
		DisplayAll ();
	    c.clear ();
	} //End while

	c.println ("Enter the first name of the person");
	search = c.readLine ();

	//Converts name to lower case
	search = search.toLowerCase ();
	//Changes the first letter to a capital
	search = Capitalize (search);

	c.println ("");

	//Checks each person's name and searches for a match
	for (int i = 0 ; i < personCount ; i++)
	{
	    //If the first name matches with the name the user is searching for
	    //obtain each match's number
	    if (p [i].getfName ().equals (search))
	    {
		//Sets each match's location to a temp storage
		personLoc [matchesFound] = (i);
		//Increase match counter by 1
		matchesFound++;
	    } //End if
	} //End for

	//If there are no matches return an error message and -1
	//-1 is then passed into another method that asks if user
	//would like to search again
	if (matchesFound == 0)
	{
	    c.println ("Person not found");
	    c.println ("\nPress any key to continue");
	    c.getChar ();
	    return -1;
	} //End if

	//If there are multiple matches allow user to choose who they are searching for
	else if (matchesFound > 1)
	{
	    for (int i = 0 ; i < matchesFound ; i++)
	    {
		//Display numbers and names for each person, and allows user to choose
		c.print ((i + 1) + ". ");
		c.println (search + " " + p [personLoc [i]].getlName ());

	    } //End for
	    c.print ("\nPlease choose your person: ");
	    option = c.readInt ();
	    while (option < 0 || option > matchesFound)
	    {
		c.print ("Please enter a value from 1 to " + matchesFound + ": ");
		option = c.readInt ();
	    } //End while

	    option--;
	    c.clear ();

	    if (display == true)
	    {
		//Display person's information
		c.print ("First name: ", 15);
		c.println (p [personLoc [option]].getfName ());
		c.print ("Last name: ", 15);
		c.println (p [personLoc [option]].getlName ());
		c.print ("Address: ", 15);
		c.println (p [personLoc [option]].getAddress ());
		c.print ("City: ", 15);
		c.println (p [personLoc [option]].getCity ());
		c.print ("Province: ", 15);
		c.println (p [personLoc [option]].getProvince ());
		c.print ("Country: ", 15);
		c.println (p [personLoc [option]].getCountry ());
		c.print ("Cell: ", 15);
		c.println (p [personLoc [option]].getCell ());
		c.println ("\nPress any key to continue");
		c.getChar ();

	    } //End if
	} //End else if

	else if (display == true)
	{
	    c.clear ();
	    //Display person's information
	    c.print ("First name: ", 15);
	    c.println (p [personLoc [0]].getfName ());
	    c.print ("Last name: ", 15);
	    c.println (p [personLoc [0]].getlName ());
	    c.print ("Address: ", 15);
	    c.println (p [personLoc [0]].getAddress ());
	    c.print ("City: ", 15);
	    c.println (p [personLoc [0]].getCity ());
	    c.print ("Province: ", 15);
	    c.println (p [personLoc [0]].getProvince ());
	    c.print ("Country: ", 15);
	    c.println (p [personLoc [0]].getCountry ());
	    c.print ("Cell: ", 15);
	    c.println (p [personLoc [0]].getCell ());
	    c.println ("\nPress any key to continue");
	    c.getChar ();

	} //End else if

	c.println ();
	// c.clear ();

	return personLoc [option];

    } //int Search (boolean)


    /**
    * This method is called upon to check if the user found the person
    * If personLocation is -1 then a person has not been found
    * and user wishes to continue searching for a person
    * If personLocation is -2 then a person has not been found
    * and user no longer wishes to search for a person
    *
    * @param personLocation The location of the person
    * @return The location of the person or a number that triggers
    * different events
    */
    public static int CheckPerson (int personLocation, boolean display)
    {
	char YoN = 'y';


	//Allows the user to search again for the user
	while (personLocation == -1)
	{
	    c.println ("Would you like to search again?(y or n)");
	    YoN = YoNmeth (YoN);

	    if (YoN == 'n')
	    {
		personLocation = -2;
		break;
	    } //End if

	    c.clear ();
	    personLocation = Search (display, false);
	} //End while

	return personLocation;
    } //void CheckPerson (int)


    /**
    * Allows the user to add a person to the array
    */
    public static void Add ()
    {
	char YoN = 'y';

	while (true)
	{
	    c.println ("Add a person\n");
	    if (personCount > 99)
	    {
		c.println ("Error! Cannot add anymore persons. Database full.");
		c.getChar ();
		break;
	    } //End if
	    else
	    {
		//Creates a blank constructor for the person and asks user
		//for their parameters
		p [personCount] = new Person ();
		c.print ("First name: ");
		p [personCount].setfName (c.readLine ());
		c.print ("Last name: ");
		p [personCount].setlName (c.readLine ());
		c.print ("Address: ");
		p [personCount].setAddress (c.readLine ());
		c.print ("City: ");
		p [personCount].setCity (c.readLine ());
		c.print ("Province: ");
		p [personCount].setProvince (c.readLine ());
		c.print ("Country: ");
		p [personCount].setCountry (c.readLine ());
		c.print ("Cell: ");
		p [personCount].setCell (c.readLine ());

		//Confirmation message
		c.println ("Are you sure you want to add " + p [personCount].getfName () + " " + p [personCount].getlName () + "?(y or n)");
		YoN = YoNmeth (YoN);
		if (YoN == 'n')
		    break;

		//Changes the first letter of each name to a capital
		p [personCount].setfName (Capitalize (p [personCount].getfName ()));
		p [personCount].setlName (Capitalize (p [personCount].getlName ()));
		p [personCount].setCity (Capitalize (p [personCount].getCity ()));
		p [personCount].setProvince (Capitalize (p [personCount].getProvince ()));
		p [personCount].setCountry (Capitalize (p [personCount].getCountry ()));

		personCount++;
		c.println ("Would you like to add another person?(y or n)");
		YoN = YoNmeth (YoN);
		c.clear ();
		if (YoN == 'n')
		    break;
	    } //End else
	} //End while
    } //void Add ()


    /*
    * Allows the user to delete a person from the array
    */
    public static void Delete ()
    {
	char YoN = 'y';
	int personLocation;

	c.println ("Delete a person\n");

	while (true)
	{
	    //Search for person to modify
	    personLocation = Search (true, false);
	    //Checks to see if person exists
	    personLocation = CheckPerson (personLocation, true);

	    //If no person was found and user no longer wishes to search for person
	    if (personLocation == -2)
	    {
		break;
	    } //End if

	    //Confirmation message
	    c.println ("Are you sure you want to delete " + p [personLocation].getfName () + " " + p [personLocation].getlName () + "?(y or n)");
	    YoN = YoNmeth (YoN);
	    if (YoN == 'n')
		break;

	    //Removes the person that is being deleted
	    //and replaces the information with the next person's
	    c.println (p [personLocation].getfName () + " " + p [personLocation].getlName () + " has been deleted\n");

	    for (int i = personLocation ; i < personCount - 1 ; i++)
	    {
		p [i] = p [i + 1];
	    } //End for
	    personCount--;

	    c.println ("Would you like to delete another person?(y or n)");
	    YoN = YoNmeth (YoN);

	    c.clear ();
	    if (YoN == 'n')
		break;
	} //End while
    } //void Delete ()


    /**
    * Allows the user to modify a person's parameters
    */
    public static void Modify ()
    {
	int personLocation, option = 0;
	String line;
	char YoN = 'y';

	c.println ("Modify a person\n");

	while (true)
	{
	    //Search for person to modify
	    personLocation = Search (false, false);
	    //Checks to see if person exists
	    personLocation = CheckPerson (personLocation, false);

	    //If no person was found and user no longer wishes to search for person
	    if (personLocation == -2)
	    {
		break;
	    } //End if

	    while (true)
	    {
		c.clear ();
		c.print ("1. First name: ", 15);
		c.println (p [personLocation].getfName ());
		c.print ("2. Last name: ", 15);
		c.println (p [personLocation].getlName ());
		c.print ("3. Address: ", 15);
		c.println (p [personLocation].getAddress ());
		c.print ("4. City: ", 15);
		c.println (p [personLocation].getCity ());
		c.print ("5. Province: ", 15);
		c.println (p [personLocation].getProvince ());
		c.print ("6. Country: ", 15);
		c.println (p [personLocation].getCountry ());
		c.print ("7. Cell: ", 15);
		c.println (p [personLocation].getCell () + "\n");
		c.print ("What would you like to modify (0 to exit): ");

		//Ask user what they would like to modify
		option = c.readInt ();
		while (option < -1 || option > 7)
		{
		    c.print ("Please enter a value from 1 to 7 (0 to exit): ");
		    option = c.readInt ();
		} //End while

		if (option == 0)
		    break;

		switch (option)
		{
		    case 1:
			c.print ("New first name: ", 15);
			p [personLocation].setfName (c.readLine ());
			p [personLocation].setfName (Capitalize (p [personLocation].getfName ()));

			break;
		    case 2:
			c.print ("New last name: ", 15);
			p [personLocation].setlName (c.readLine ());
			p [personLocation].setlName (Capitalize (p [personLocation].getlName ()));

			break;
		    case 3:
			c.print ("New address: ", 15);
			p [personLocation].setAddress (c.readLine ());
			break;
		    case 4:
			c.print ("New city: ", 15);
			p [personLocation].setCity (c.readLine ());
			p [personLocation].setCity (Capitalize (p [personLocation].getCity ()));
			break;
		    case 5:
			c.print ("New province: ", 15);
			p [personLocation].setProvince (c.readLine ());
			p [personLocation].setProvince (Capitalize (p [personLocation].getProvince ()));

			break;
		    case 6:
			c.print ("New country: ", 15);
			p [personLocation].setCountry (c.readLine ());
			p [personLocation].setCountry (Capitalize (p [personLocation].getCountry ()));
			break;
		    case 7:
			c.print ("New cell: ", 15);
			p [personLocation].setCell (c.readLine ());
			break;
		} //End switch
		c.println ("Would you like to modify another item?(y or n)");
		YoN = YoNmeth (YoN);
		if (YoN == 'n')
		    break;

	    } //End while

	    c.println ("Would you like to modify another person?(y or n)");
	    YoN = YoNmeth (YoN);
	    c.clear ();

	    if (YoN == 'n')
		break;
	} //End while
    } //void Modify ()


    /**
    * Displays each person's information in a nice formatted manner
    */
    public static void DisplayAll ()
    {
	int screenCap = 0, i = 0;
	char next;

	c.clear ();

	c.println ("View database\n");
	while (true)
	{
	    for (int y = 1 ; i < personCount ; y += 45)
	    {
		for (int x = 0 ; x < 4 ; x++)
		{
		    c.setCursor ((x * 8) + 3 + x, y);
		    c.print (i + 1);
		    c.setCursor ((x * 8) + 4 + x, y);
		    c.print ("First name: ");
		    c.setCursor ((x * 8) + 4 + x, y + 16);
		    c.print (p [i].getfName ());
		    c.setCursor ((x * 8) + 5 + x, y);
		    c.print ("Last name: ");
		    c.setCursor ((x * 8) + 5 + x, y + 16);
		    c.print (p [i].getlName ());
		    c.setCursor ((x * 8) + 6 + x, y);
		    c.print ("Address: ");
		    c.setCursor ((x * 8) + 6 + x, y + 16);
		    c.print (p [i].getAddress ());
		    c.setCursor ((x * 8) + 7 + x, y);
		    c.print ("City: ");
		    c.setCursor ((x * 8) + 7 + x, y + 16);
		    c.print (p [i].getCity ());
		    c.setCursor ((x * 8) + 8 + x, y);
		    c.print ("Province: ");
		    c.setCursor ((x * 8) + 8 + x, y + 16);
		    c.print (p [i].getProvince ());
		    c.setCursor ((x * 8) + 9 + x, y);
		    c.print ("Country: ");
		    c.setCursor ((x * 8) + 9 + x, y + 16);
		    c.print (p [i].getCountry ());
		    c.setCursor ((x * 8) + 10 + x, y);
		    c.print ("Cell: ");
		    c.setCursor ((x * 8) + 10 + x, y + 16);
		    c.print (p [i].getCell ());
		    i++;
		    if (i == (personCount))
		    {
			break;
		    } //End if

		} //End for

		//Allows the user to scroll back a page
		if (i % 8 == 0)
		{
		    if (i > 8)
		    {
			c.setCursor (39, 10);
			c.print ("Press 'a' to go back a page                     " + (personCount - i) + " person(s) left");
		    } //End if
		    else
		    {
			c.setCursor (39, 35);
			c.print ((personCount - i) + " person(s) left");
		    } //End else

		    next = c.getChar ();
		    if (next == 'a' && i > 8)
		    {
			i -= 16;
			c.clear ();
			c.println ("View database\n");
			break;
		    } //End if
		    c.clear ();
		    c.println ("View database\n");
		    y -= 90;
		} //End if
	    } //End for
	    if (i == personCount)
	    {
		c.setCursor (39, 10);

		c.print ("Press 'a' to go back a page");

		next = c.getChar ();

		//Allows the user to scroll back a page when they reach the last page
		if (next == 'a' && i > 8 && i == personCount)
		{
		    //Subtracts 8 and the amount on the last page
		    i = i - (8 + (personCount % 8));
		    c.clear ();
		    c.println ("View database\n");
		} //End if
		else
		    break;

	    } //End if
	} //End while
    } //void DisplayAll ()


    /**
    * Saves the modified information to the directory
    */
    public static void Save () throws IOException
    {
	PrintWriter out;
	out = new PrintWriter (new FileWriter ("directory.txt"));

	//Writes each person and its parameters to the directory.txt file
	for (int i = 0 ; i < personCount ; i++)
	{
	    out.println (p [i].getfName ());
	    out.println (p [i].getlName ());
	    out.println (p [i].getAddress ());
	    out.println (p [i].getCity ());
	    out.println (p [i].getProvince ());
	    out.println (p [i].getCountry ());
	    out.println (p [i].getCell ());
	} //End for


	out.close ();
    } //void Save ()


    /**
    * Word wrap method that makes the text look nicer
    */
    public static void WordWrap (Console x, String line)
    {
	int lastSpace = 0, maxx = c.getMaxColumns ();

	while (line.length () > maxx)
	{
	    //Finds the last space from 0 to 10
	    lastSpace = line.substring (0, maxx).lastIndexOf (" ");
	    //Display the text from 0 to the space
	    x.println (line.substring (0, lastSpace));
	    //Save the remaining text to the paragraph variable
	    line = line.substring (lastSpace + 1);

	} //End while


	x.println (line);
    } //void WordWrap (String)


    /**
    * A help file that explains what each option does
    */
    public static void Help ()
    {
	Console d = new Console (40, 90, "Personal Directory Help File");
	int option = 0;

	//Sets the color
	d.setTextColor (Color.white);
	d.setTextBackgroundColor (Color.blue);
	d.clear ();

	while (option != 9)
	{
	    d.println ("PERSONAL DIRECTORY HELP FILE\n");
	    d.println ("1 - Search");
	    d.println ("2 - Add");
	    d.println ("3 - Delete");
	    d.println ("4 - Modify");
	    d.println ("5 - Generate");
	    d.println ("6 - View database");
	    d.println ("7 - Help file");
	    d.println ("8 - Save and exit");
	    d.println ("9 - Exit help file\n");
	    //Capitalize all

	    d.print ("Please select your process: ");
	    option = d.readInt ();
	    while (option < 1 || option > 9)
	    {
		d.print ("Please enter a value from 1 to 9: ");
		option = d.readInt ();
	    } //End while

	    d.clear ();

	    switch (option)
	    {
		case 1:
		    d.println ("Search for person\n");
		    String help1 = ("This method allows the user to search for a person in the array. First, it will ask the user if they would like to")
			+ ("display the database.If not then you can simply enter the person 's first name, ")
			+ ("case-insensitive, and if they exist, their information will be displayed on screen. If the person can't be found, the ")
			+ ("program will ask the user if they would like to search again. If there are multiple people that share the person's ")
			+ ("first name, then the program will ask the user to choose from a list that includes last names and then display their information.");
		    WordWrap (d, help1);
		    d.getChar ();
		    break;
		case 2:
		    d.println ("Add a person\n");
		    String help2 = ("This method allows the user to add a person to the array. Simply enter the specified parameters and their ")
			+ ("information will be then saved to the array with proper capitalization. The program will then ask the user to confirm their ")
			+ ("addition and ask if they would like to add another person.");
		    WordWrap (d, help2);
		    d.getChar ();
		    break;
		case 3:
		    d.println ("Delete a person\n");
		    String help3 = ("This method will allow the user to delete a person from the array. First the user must search for the person. ")
			+ ("The steps are identical to using the search method so if there are any issues please refer to that. The program will then ask ")
			+ ("the user to confirm their deletion and ask if they would like to delete another person.");
		    WordWrap (d, help3);
		    d.getChar ();
		    break;
		case 4:
		    d.println ("Modify a person\n");
		    String help4 = ("This method will allow the user to modify a person from the array. First the user must search for the person. ")
			+ ("The steps are identical to using the search method so if there are any issues please refer to that. The program will then ask ")
			+ ("which parameter the user would like to modify. If the user decides to not modify anything, they can enter 0 to exit. The program ")
			+ ("will then ask the user to confirm their modification and ask if they would like to modify another parameter. It will then ask the ")
			+ ("user if they would like to modify another person.");
		    WordWrap (d, help4);
		    d.getChar ();
		    break;
		case 5:
		    d.println ("Generate people\n");
		    String help5 = ("This method will allow the user to generate people. The method will ask how many people they would like to generate ")
			+ ("and then generate them with random parameters.");
		    WordWrap (d, help5);
		    d.getChar ();
		    break;
		case 6:
		    d.println ("View database\n");
		    String help6 = ("This method will allow the user to view each person currently existing in the array in a nice format. To go back a page, ")
			+ ("just hit a.");
		    WordWrap (d, help6);
		    d.getChar ();
		    break;
		case 7:
		    d.println ("Personal Directory Help File\n");
		    d.println ("This method will open up a help file in a new window.");
		    d.getChar ();
		    break;
		case 8:
		    d.println ("Save and exit\n");
		    String help8 = ("This method will write each person currently in the array to the specified output file (directory.txt by default)")
			+ ("and then exit the program completely.");
		    WordWrap (d, help8);
		    d.getChar ();
		    break;

	    } //End switch
	    d.clear ();
	} //End while


	d.close ();
    } //void Help ()


    /**
    * Counts how many lines are in a text file and randomly chooses
    * a string to return
    *
    * @param fileName The name of the file to parse
    * @return A random parameter used for to create a person
    */
    public static String RandomSelection (String fileName) throws IOException
    {
	BufferedReader in;
	int num, numOfLines = 0;
	String line;

	in = new BufferedReader (new FileReader (fileName));

	//Counts how many lines are in the file
	line = in.readLine ();
	while (line != null)
	{
	    numOfLines++;
	    line = in.readLine ();
	} //End while


	in.close ();

	in = new BufferedReader (new FileReader (fileName));

	//Generates a random number from 1 to the number of lines in the file
	num = (int) (Math.random () * numOfLines) + 1;

	//Finds the randomly selected string
	for (int i = 0 ; i < num ; i++)
	{
	    line = in.readLine ();
	} //End for


	in.close ();

	//Returns the parameter
	return line;

    } //String RandomSelection (String)


    /**
    * Generate a random 10 digit cell phone number
    *
    * @return A cell phone number
    */
    public static String GenerateCell ()
    {
	String cell = "";
	int num;

	for (int j = 0 ; j < 3 ; j++)
	{
	    for (int i = 0 ; i < 3 ; i++)
	    {
		num = (int) (Math.random () * 10);
		cell += num;
		if (j == 2 && i == 2)
		{
		    num = (int) (Math.random () * 10);
		    cell += num;
		} //End if
	    } //End for
	    if (j != 2)
		cell += ("-");
	} //End for


	return cell;
    } //String GenerateCell ()


    /**
    * Generates a person with random parameters
    */
    public static void Generate () throws IOException
    {

	String fName, lName, address, city, province, country, cell;
	int houseNum, amount;
	c.println ("Generate People\n");

	if (personCount > 99)
	{
	    c.println ("Error! Cannot add anymore persons. Database full.");
	    c.getChar ();
	} //End if


	else
	{
	    c.println ("How many people would you like to generate (0 to exit)? " + (100 - (personCount)) + " spots available");
	    amount = c.readInt ();

	    while (amount > 100 - personCount)
	    {
		c.println ("Please choose a number smaller than " + (100 - personCount));
		amount = c.readInt ();
	    } //End while

	    for (int i = 0 ; i < amount ; i++)
	    {

		//Opens specified files that include the required parameter
		fName = RandomSelection ("firstnames.txt");
		lName = RandomSelection ("lastnames.txt");

		//Generates the house number and converts it to a string
		houseNum = ((int) (Math.random () * 1000) + 1);
		address = Integer.toString (houseNum);

		//Opens specified files that include the required parameter
		address = houseNum + " " + RandomSelection ("addresses.txt");
		city = RandomSelection ("cities.txt");
		province = RandomSelection ("provinces.txt");
		country = RandomSelection ("countries.txt");
		cell = GenerateCell ();

		//Creates a person with random parameters
		p [personCount] = new Person ();
		p [personCount].setfName (fName);
		p [personCount].setlName (lName);
		p [personCount].setAddress (address);
		p [personCount].setCity (city);
		p [personCount].setProvince (province);
		p [personCount].setCountry (country);
		p [personCount].setCell (cell);
		personCount++;
	    } //End for
	} //End else
    } //void Generate ()


    ////////////////////////////////Main////////////////////////////////

    public static void main (String[] args) throws IOException
    {

	c = new Console (40, 90, "Personal Directory");
	//Sets the color
	c.setTextColor (Color.white);
	c.setTextBackgroundColor (Color.black);
	c.clear ();


	////////////////////////////////Variables////////////////////////////////
	int option = 0, personLocation;
	String line;

	//Reads the directory
	BufferedReader in;
	in = new BufferedReader (new FileReader ("directory.txt"));
	line = in.readLine ();

	while (line != null)
	{
	    //Instantiate a blank person
	    p [personCount] = new Person ();
	    //Read first name
	    p [personCount].setfName (line);
	    line = in.readLine ();
	    //Read last name
	    p [personCount].setlName (line);
	    line = in.readLine ();
	    //Read address
	    p [personCount].setAddress (line);
	    line = in.readLine ();
	    //Read city
	    p [personCount].setCity (line);
	    line = in.readLine ();
	    //Read province
	    p [personCount].setProvince (line);
	    line = in.readLine ();
	    //Read country
	    p [personCount].setCountry (line);
	    line = in.readLine ();
	    //Read cell
	    p [personCount].setCell (line);
	    line = in.readLine ();
	    //Increases the person count by 1
	    personCount++;
	} //End while


	while (true)
	{
	    c.println ("PERSONAL DIRECTORY (" + personCount + "/100)\n");
	    c.println ("1 - Search");
	    c.println ("2 - Add");
	    c.println ("3 - Delete");
	    c.println ("4 - Modify");
	    c.println ("5 - Generate");
	    c.println ("6 - View database");
	    c.println ("7 - Help file");
	    c.println ("8 - Save and exit\n");
	    //Capitalize all

	    c.print ("Please select your process: ");
	    option = c.readInt ();

	    //Checks to see if directory is empty
	    if (personCount != 0)
	    {
		while (option < 1 || option > 8)
		{
		    c.print ("Please enter a value from 1 to 8: ");
		    option = c.readInt ();
		} //End while
	    } //End if
	    else
	    {
		while (option != 2 && option != 5 && option != 7 && option != 8)
		{
		    c.println ("The directory is empty, please add a person to continue.");
		    option = c.readInt ();
		} //End while
	    } //End else

	    c.clear ();

	    switch (option)
	    {
		case 1:
		    personLocation = Search (true, true);
		    personLocation = CheckPerson (personLocation, true);
		    break;
		case 2:
		    Add ();
		    break;
		case 3:
		    Delete ();
		    break;
		case 4:
		    Modify ();
		    break;
		case 5:
		    Generate ();
		    break;
		case 6:
		    DisplayAll ();
		    break;
		case 7:
		    Help ();
		    break;
		case 8:
		    Save ();
		    System.exit (0);

	    } //End switch
	    c.clear ();
	} //End while
    } // main method
} // PersonalDirectory class

class Person
{
    protected String fName = "";
    protected String lName = "";
    protected String address = "";
    protected String city = "";
    protected String province = "";
    protected String country = "";
    protected String cell = "";

    /**
    *Constructor for basic information
    *
    *@param fName First name
    *@param lName Last name
    */
    public Person (String fName, String lName)
    {
	this.fName = fName;
	this.lName = lName;
    } //Constructor


    /**
    *Alternate constructor including contact information
    *
    *
    *@param fName First name
    *@param lName Last name
    *@param cell Cell phone number
    */
    public Person (String fName, String lName, String cell)
    {
	this.fName = fName;
	this.lName = lName;
	this.cell = cell;
    } //Alternate Person constructor



    /**
    *Alternate constructor including all information
    *
    *
    *@param fName First name
    *@param lName Last name
    *@param address Address
    *@param city City
    *@param province Province
    *@param country Country
    *@param cell Cell
    */
    public Person (String fName, String lName, String address, String city, String province,
	    String country, String cell)
    {
	this.fName = fName;
	this.lName = lName;
	this.address = address;
	this.city = city;
	this.province = province;
	this.country = country;
	this.cell = cell;
    } //Alternate Person constructor


    /**
    *Blank constructor
    */
    public Person ()
    {
    } //Blank person constructor


    //Other methods of Person class

    //Setters/////////////////////////////////

    /**
    *Set first name
    *
    *@param fName First name
    */
    public void setfName (String fName)
    {
	this.fName = fName;
    } //void setfName (String)


    /**
    *Set last name
    *
    *@param lName Last name
    */
    public void setlName (String lName)
    {
	this.lName = lName;
    } //void setfName (String)


    /**
    *Set address
    *
    *@param address Address
    */
    public void setAddress (String address)
    {
	this.address = address;
    } //void setAddress (String)


    /**
    *Set city
    *
    *@param city City
    */
    public void setCity (String city)
    {
	this.city = city;
    } //void setCity (String)


    /**
    *Set province
    *
    *@param prov Province
    */
    public void setProvince (String province)
    {
	this.province = province;
    } //void setfName (String)


    /**
    *Set country
    *
    *@param country Country
    */
    public void setCountry (String country)
    {
	this.country = country;
    } //void setfName (String)


    /**
    *Set cell
    *
    *@param cell Cell
    */
    public void setCell (String cell)
    {
	this.cell = cell;
    } //void setfName (String)




    //Getters/////////////////////////////////

    /**
    *Get first name
    *
    *@return String first name
    */
    public String getfName ()
    {
	return fName;
    } //String getfName ()


    /**
    *Get last name
    *
    *@return String last name
    */
    public String getlName ()
    {
	return lName;
    } //String getlName ()


    /**
    *Get address
    *
    *@return String address
    */
    public String getAddress ()
    {
	return address;
    } //String address ()


    /**
    *Get city
    *
    *@return String city
    */
    public String getCity ()
    {
	return city;
    } //String getCity ()


    /**
    *Get province
    *
    *@return String province
    */
    public String getProvince ()
    {
	return province;
    } //String getProv ()


    /**
    *Get country
    *
    *@return String country
    */
    public String getCountry ()
    {
	return country;
    } //String getCountry ()


    /**
    *Get cell
    *
    *@return String cell
    */
    public String getCell ()
    {
	return cell;
    } //String getCell ()


    public String toString ()
    {
	return "First name: " + fName + ", Last name: " + lName + ", Address: " + address + ", City: " + city + ", Province: " + province + ", Country: " + country + ", Cell: " + cell;
    }
} // Person class

