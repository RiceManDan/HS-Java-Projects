/**
 * Calculator class
 *
 * A calculator applet
 *
 * @author Danny Cheng
 * @date 12/10/14
 *
 */
import java.applet.*;
import java.awt.*;

public class Calculator extends Applet
{
    // GUI
    Label lMemoryStatus, lBrandName, lNull;
    Button bDecim, bEqual, bAdd, bMin, bMulti, bDiv, bCA, bCE,
	bDEL, bPM, bMemAdd, bMemDel, bMemRec, bMemCls;
    Button[] bNum = new Button [10];
    TextField tDisplay;

    // Variables
    String displayNum = "", operator, lastOp = "";
    double memory, temp = 0, total, n1, n2;
    int operatorCount = 0, EEgg = 0;


    // Color
    Color chillGrey = new Color (150, 160, 160);
    Color basicGrey = new Color (205, 201, 201);
    Color funkyGrey = new Color (120, 120, 120);
    Color notGrey = new Color (190, 230, 240);
    public void init ()
    {
	lBrandName = new Label ("Chengulator");
	lBrandName.setAlignment (1);
	lBrandName.setFont (new Font ("Candara", 3, 24));

	lNull = new Label (" ");

	tDisplay = new TextField (10);
	tDisplay.setFont (new Font ("Consolas", 1, 24));

	lMemoryStatus = new Label (" ");

	// Number buttons
	for (int i = 0 ; i < 10 ; i++)
	{
	    bNum [i] = new Button (String.valueOf (i));
	} // End for

	bDecim = new Button (" . ");
	bEqual = new Button (" = ");
	bAdd = new Button (" + ");
	bMin = new Button (" - ");
	bMulti = new Button (" x ");
	bDiv = new Button (String.valueOf ((char) 247));
	bCA = new Button (" CA ");
	bCE = new Button (" CE ");
	bDEL = new Button (" DEL  ");
	bPM = new Button (String.valueOf ((char) 177));
	bMemAdd = new Button (" M+ ");
	bMemDel = new Button (" M- ");
	bMemRec = new Button (" MR ");
	bMemCls = new Button (" MC ");

	// set canvas layout to BorderLayout
	setLayout (new BorderLayout ());

	// create north panel
	Panel north = new Panel ();

	// layout north panel as BorderLayout
	north.setLayout (new BorderLayout ());

	// add fields to north panel
	north.add ("North", lBrandName);
	north.add ("Center", tDisplay);
	north.add ("West", lNull);
	north.add ("East", lMemoryStatus);

	// create south panel
	Panel south = new Panel ();


	// Layout south panel as GridLayout
	south.setLayout (new GridLayout (6, 4, 5, 5));
	south.setFont (new Font ("Consolas", 1, 24));

	// Add buttons to south panel grid

	// Row 1
	south.add (bMemAdd);
	south.add (bMemDel);
	south.add (bMemRec);
	south.add (bMemCls);

	// Row 2
	south.add (bPM);
	south.add (bDEL);
	south.add (bCE);
	south.add (bCA);

	// Row 3
	south.add (bNum [7]);
	south.add (bNum [8]);
	south.add (bNum [9]);
	south.add (bDiv);

	// Row 4
	south.add (bNum [4]);
	south.add (bNum [5]);
	south.add (bNum [6]);
	south.add (bMulti);

	// Row 5
	south.add (bNum [1]);
	south.add (bNum [2]);
	south.add (bNum [3]);
	south.add (bMin);

	// Row 6
	south.add (bNum [0]);
	south.add (bDecim);
	south.add (bEqual);
	south.add (bAdd);


	// Set colors
	setBackground (chillGrey);
	bAdd.setBackground (basicGrey);
	bMin.setBackground (basicGrey);
	bMulti.setBackground (basicGrey);
	bDiv.setBackground (basicGrey);
	bEqual.setBackground (funkyGrey);
	bMemAdd.setBackground (notGrey);
	bMemDel.setBackground (notGrey);
	bMemRec.setBackground (notGrey);
	bMemCls.setBackground (notGrey);

	// Add north and south panels to canvas
	add ("North", north);
	add ("South", south);
    }


    /**
    * Calls a method based on the button that user clicks
    *
    *
    * @param e The event
    * @param o The object
    */
    public boolean action (Event e, Object o)
    {
	// Numbers
	for (int i = 0 ; i < 10 ; i++)
	{
	    if (e.target == bNum [i] && lastOp.equals (""))
	    {
		displayNum += (i);
	    } // End if
	    else if (e.target == bNum [i] && !(lastOp.equals ("")))
	    {
		displayNum = String.valueOf (i);
		lastOp = ("");
	    } // End else if
	} // End for





	// Memory functions
	if (e.target == bMemAdd && !displayNum.equals (""))
	{
	    memory += (Double.parseDouble (displayNum));
	    lMemoryStatus.setText ("M");
	    EEgg += 50;
	} // End if

	else if (e.target == bMemDel && !displayNum.equals (""))
	{
	    memory -= (Double.parseDouble (displayNum));
	    if (memory == 0)
	    {
		lMemoryStatus.setText (" ");
	    } // End if
	    if (EEgg == 50)
	    {
		EEgg -= 25;
	    } // End if
	} // End else if


	else if (e.target == bMemRec)
	{
	    // Prevents memory from being called if there is no value stored in it
	    if (memory != 0)
	    {
		displayNum += (memory);
		IntConv (memory);
	    } // End if
	    if (EEgg == 25)
	    {
		EEgg *= 50;
	    } // End if
	} // End else if


	else if (e.target == bMemCls)
	{
	    memory = 0;
	    lMemoryStatus.setText (" ");

	    if (EEgg == 1250)
	    {
		for (int i = 0 ; i < 100 ; i += 1)
		{
		    lBrandName.setBackground (new Color ((float) Math.random (), (float) Math.random (), (float) Math.random ()));
		    setBackground (new Color ((float) Math.random (), (float) Math.random (), (float) Math.random ()));
		    bAdd.setBackground (new Color ((float) Math.random (), (float) Math.random (), (float) Math.random ()));
		    bMin.setBackground (new Color ((float) Math.random (), (float) Math.random (), (float) Math.random ()));
		    bMulti.setBackground (new Color ((float) Math.random (), (float) Math.random (), (float) Math.random ()));
		    bDiv.setBackground (new Color ((float) Math.random (), (float) Math.random (), (float) Math.random ()));
		    bEqual.setBackground (new Color ((float) Math.random (), (float) Math.random (), (float) Math.random ()));
		    bMemAdd.setBackground (new Color ((float) Math.random (), (float) Math.random (), (float) Math.random ()));
		    bMemDel.setBackground (new Color ((float) Math.random (), (float) Math.random (), (float) Math.random ()));
		    bMemRec.setBackground (new Color ((float) Math.random (), (float) Math.random (), (float) Math.random ()));
		    bMemCls.setBackground (new Color ((float) Math.random (), (float) Math.random (), (float) Math.random ()));
		    bDecim.setBackground (new Color ((float) Math.random (), (float) Math.random (), (float) Math.random ()));
		    bCA.setBackground (new Color ((float) Math.random (), (float) Math.random (), (float) Math.random ()));
		    bCE.setBackground (new Color ((float) Math.random (), (float) Math.random (), (float) Math.random ()));
		    bDEL.setBackground (new Color ((float) Math.random (), (float) Math.random (), (float) Math.random ()));
		    bPM.setBackground (new Color ((float) Math.random (), (float) Math.random (), (float) Math.random ()));

		    for (int j = 0 ; j < 10 ; j++)
		    {
			bNum [j].setBackground (new Color ((float) Math.random (), (float) Math.random (), (float) Math.random ()));
		    } // End for
		    delay (25);
		} // End for
	    } // End for
	} // End else if


	else if (e.target == bDecim)
	{
	    if (displayNum.indexOf (".") == -1)
	    {
		displayNum += ".";
	    } // End if
	} // End else if


	else if (e.target == bPM)
	{
	    temp = (Double.parseDouble (displayNum));
	    IntConv (temp);
	} // End else if





	// Clearing functions
	else if (e.target == bCA)
	{
	    displayNum = "";
	    temp = 0;
	    operatorCount = 0;
	    ResetColors ();
	} // End else if


	else if (e.target == bCE)
	{
	    displayNum = "";
	} // End else if


	else if (e.target == bDEL)
	{

	    // Prevents the string index from going out of range (-1)
	    if (!displayNum.equals (""))
	    {
		displayNum = displayNum.substring (0, displayNum.length () - 1);
	    } //End if

	} // End else if





	//Operators
	else if (e.target == bAdd)
	{
	    ResetColors ();
	    OpCheck ("+");
	} // End else if


	else if (e.target == bMin)
	{
	    ResetColors ();
	    OpCheck ("-");
	} // End else if


	else if (e.target == bMulti)
	{
	    ResetColors ();
	    OpCheck ("*");
	} // End else if


	else if (e.target == bDiv)
	{
	    ResetColors ();
	    OpCheck (String.valueOf ((char) 247));
	} // End else if


	else if (e.target == bEqual && !(operator.equals ("")))
	{
	    DisplayRes ();
	    operatorCount = 0;
	    operator = "";
	    lastOp = "EasterEgg";
	} // End else if


	tDisplay.setText (displayNum);
	return true;
    } // boolean action (Event, Object)



    /**
    * Performs the calculations
    *
    *
    * @param n1 First number
    * @param n2 Second number
    * @param operator Math operator
    *
    * @return double
    */
    public double Calc (double n1, double n2, String operator)
    {
	double total = 0;
	if (operator.equals ("+"))
	{
	    total = n1 + n2;
	} // End if


	else if (operator.equals ("-"))
	{
	    total = n1 - n2;
	} // End else if


	else if (operator.equals ("*"))
	{
	    total = n1 * n2;
	} // End else if


	else if (operator.equals (String.valueOf ((char) 247)))
	{
	    total = n1 / n2;
	} // End else if


	lastOp = operator;
	return total;
    } //double Calc (double, double, String)


    /**
    * Resets the colors
    */
    public void ResetColors ()
    {
	bAdd.setBackground (basicGrey);
	bMin.setBackground (basicGrey);
	bMulti.setBackground (basicGrey);
	bDiv.setBackground (basicGrey);
    } //void ResetColors (void)


    /**
    * Checks the conditions for operations
    * @param initOp The initial math operator
    */
    public void OpCheck (String initOp)
    {

	// If the last button was an operator then do nothing
	if (!lastOp.equals ("+") && !lastOp.equals ("-") && !lastOp.equals ("*") && !lastOp.equals (String.valueOf ((char) 247)))
	{
	    operatorCount += 1;

	    // If there's no operator
	    if (operatorCount == 1 && !displayNum.equals (""))
	    {
		SetColor (initOp);
		// Set n1 to the number on the display
		n1 = (Double.parseDouble (displayNum));
		displayNum = "";
	    } // End if

	    // If there is an operator
	    else if (operatorCount > 1 && !displayNum.equals (""))
	    {
		SetColor (initOp);
		DisplayRes ();
	    } // End else if

	    // Sets the operator to the initial operator
	    operator = initOp;
	} // void OpCheck (String)
    } // double Calc (double, double, String)


    /**
    * Sets the color for operator buttons
    */
    public void SetColor (String operator)
    {
	if (operator.equals ("+"))
	{
	    bAdd.setBackground (chillGrey);
	} // End if


	else if (operator.equals ("-"))
	{
	    bMin.setBackground (chillGrey);
	} // End else if


	else if (operator.equals ("*"))
	{
	    bMulti.setBackground (chillGrey);
	} // End else if


	else if (operator.equals (String.valueOf ((char) 247)))
	{
	    bDiv.setBackground (chillGrey);
	} // End else if
    } //void SetColor (String)


    /**
    * Displays the results, Calls the mathod to perform the calculatons
    *
    */
    public void DisplayRes ()
    {
	n2 = Double.parseDouble (displayNum);
	n1 = Calc (n1, n2, operator);
	displayNum = String.valueOf (n1);
	IntConv (n1);
    } //void DisplayRes


    /**
    *Converts the number to a integer and removes the decimal if .0
    *
    *@param double The number to convert
    */
    public void IntConv (double number)
    {
	if (number % 1 == 0)
	{
	    displayNum = (String.valueOf ((int) number));
	} // End if
    } //void IntConv (double)


    /**
    *@author Mr.Milardovic
    *Pauses program execution for a specified amount of time.
    *
    *@param pause Length of delay, in 1/1000ths of a second
    */
    public static void delay (int pause)
    {
	try
	{
	    Thread.sleep (pause);
	}
	catch (InterruptedException e)
	{
	    ;
	}
    } // delay (int)
} // Calculator class


